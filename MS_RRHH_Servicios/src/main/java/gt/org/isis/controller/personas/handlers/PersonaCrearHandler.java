/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractValidationsRequestHandler;
import gt.org.isis.api.C;
import gt.org.isis.controller.dto.EstudioSaludDto;
import gt.org.isis.controller.dto.IdiomaDto;
import gt.org.isis.controller.dto.RegistroLaboralPuestoDto;
import gt.org.isis.controller.dto.ReqNuevaPersonaDto;
import gt.org.isis.converters.DpiDtoConverter;
import gt.org.isis.converters.EstudiosSaludConverter;
import gt.org.isis.converters.IdiomaDtoConverter;
import gt.org.isis.converters.LugarResidenciaDtoConverter;
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.converters.RegistroAcademicoConverter;
import gt.org.isis.converters.RegistroLaboralConverter;
import gt.org.isis.converters.RegistroLaboralPuestosConverter;
import gt.org.isis.model.AreaGeografica;
import gt.org.isis.model.AreaGeografica_;
import gt.org.isis.model.Dpi;
import gt.org.isis.model.EstudioSalud;
import gt.org.isis.model.Idioma;
import gt.org.isis.model.LugarResidencia;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.RegistroAcademico;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.model.enums.Estado;
import gt.org.isis.model.enums.EstadoVariable;
import gt.org.isis.model.utils.EntitiesHelper;
import gt.org.isis.repository.AreasGeografRepository;
import gt.org.isis.repository.DpiRepository;
import gt.org.isis.repository.EstudiosSaludRepository;
import gt.org.isis.repository.IdiomaRepository;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.PuestoRepository;
import gt.org.isis.repository.RegistroAcademicoRepository;
import gt.org.isis.repository.RegistroLaboralRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edcracken
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonaCrearHandler extends AbstractValidationsRequestHandler<ReqNuevaPersonaDto, Boolean> {

    @Autowired
    PersonasRepository repo;
    @Autowired
    PersonaDtoConverter converter;
    @Autowired
    AreasGeografRepository areasRepo;
    private Persona currentPersona;

    private AreaGeografica getAreaByNombreAndTipo(final String nombre, final String tipo) {
        List<AreaGeografica> all = areasRepo.findAll(new Specification<AreaGeografica>() {
            @Override
            public Predicate toPredicate(Root<AreaGeografica> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(AreaGeografica_.tipo), tipo);
            }
        });
        for (AreaGeografica ag : all) {
            if (ag.getValor().contains(nombre) || ag.getValor().startsWith(nombre) || ag.getValor().endsWith(nombre)) {
                return ag;
            }
        }
        return all.get(0);
    }

    private Date parseFechaNacTexto(String text) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("ddMMMyyyy");
            return sd.parse(text);
        } catch (ParseException ex) {
            ex.printStackTrace(System.err);
            return new DateTime().plusYears(-18).toDate();
        }
    }

    private PersonaCrearHandler savePersona(ReqNuevaPersonaDto r) {
        currentPersona = converter.toEntity(r);

        currentPersona.setEstado(Estado.ACTIVO);
        currentPersona.setCui(r.getCui());
        currentPersona.setCreadoPor("admin");
        if (r.getFechaNacimientoTexto() != null && !r.getFechaNacimientoTexto().isEmpty()) {
            currentPersona.setFechaNacimiento(parseFechaNacTexto(r.getFechaNacimientoTexto()));
        }
        currentPersona.setEdad(Years.yearsBetween(LocalDate.fromDateFields(currentPersona.getFechaNacimiento()),
                LocalDate.fromDateFields(Calendar.getInstance().getTime())).getYears());
        EntitiesHelper.setDateCreateRef(currentPersona);

        if (r.getFkMunicipioCedulaNombre() != null && !r.getFkMunicipioCedulaNombre().isEmpty()) {
            currentPersona.setFkMunicipioCedula(getAreaByNombreAndTipo(r.getFkMunicipioCedulaNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        }
        if (r.getFkMunicipioNacimientoNombre() != null && !r.getFkMunicipioNacimientoNombre().isEmpty()) {
            currentPersona.setFkMunicipioNacimiento(getAreaByNombreAndTipo(r.getFkMunicipioNacimientoNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        }
        if (r.getFkMunicipioVecindadNombre() != null && !r.getFkMunicipioVecindadNombre().isEmpty()) {
            currentPersona.setFkMunicipioVecindad(getAreaByNombreAndTipo(r.getFkMunicipioVecindadNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        }
        currentPersona = repo.save(currentPersona);
        return this;
    }

    @Autowired
    IdiomaRepository idiomasRepo;

    private PersonaCrearHandler saveIdiomas(ReqNuevaPersonaDto r) {
        idiomasRepo.save(
                Collections2.transform(r.getIdiomas(),
                        new Function<IdiomaDto, Idioma>() {
                    @Override
                    public Idioma apply(IdiomaDto f) {
                        Idioma i = new IdiomaDtoConverter().toEntity(f);
                        i.setFkPersona(currentPersona);
                        i.setCreadoPor(currentPersona.getCreadoPor());
                        EntitiesHelper.setDateCreateRef(i);
                        return i;
                    }
                }));

        return this;
    }
    @Autowired
    RegistroAcademicoRepository regAcadRepo;

    private PersonaCrearHandler saveRegAcademico(ReqNuevaPersonaDto r) {
        RegistroAcademico ra = new RegistroAcademicoConverter().toEntity(r.getRegistroAcademico());
        ra.setFkPersona(currentPersona);
        ra.setCreadoPor(currentPersona.getCreadoPor());
        ra.setEstado(EstadoVariable.ACTUAL);
        EntitiesHelper.setDateCreateRef(ra);
        regAcadRepo.save(ra);

        return this;
    }
    @Autowired
    RegistroLaboralRepository regLaboralRepo;
    @Autowired
    PuestoRepository puestoRepo;

    private PersonaCrearHandler saveRegLaboral(ReqNuevaPersonaDto r) {
        RegistroLaboral rl = new RegistroLaboralConverter()
                .toEntity(r.getRegistroLaboral());
        rl.setEstado(EstadoVariable.ACTUAL);
        rl.setFkPersona(currentPersona);
        rl.setCreadoPor(currentPersona.getCreadoPor());
        final RegistroLaboral rl2 = regLaboralRepo.save(rl);

        puestoRepo.save(
                Collections2.transform(r.getRegistroLaboral().getPuestos(),
                        new Function<RegistroLaboralPuestoDto, Puesto>() {
                    @Override
                    public Puesto apply(RegistroLaboralPuestoDto f) {
                        Puesto ps = new RegistroLaboralPuestosConverter().toEntity(f);
                        ps.setFkRegistroLaboral(rl2);
                        EntitiesHelper.setDateCreateRef(ps);
                        ps.setCreadoPor(rl2.getCreadoPor());
                        return ps;
                    }
                }));
        EntitiesHelper.setDateCreateRef(rl);
        return this;
    }

    @Autowired
    EstudiosSaludRepository estudiosRepo;

    private PersonaCrearHandler saveEstudiosSalud(ReqNuevaPersonaDto r) {
        estudiosRepo.save(
                Collections2.transform(r.getEstudiosSalud(),
                        new Function<EstudioSaludDto, EstudioSalud>() {
                    @Override
                    public EstudioSalud apply(EstudioSaludDto f) {
                        EstudioSalud es = new EstudiosSaludConverter()
                                .toEntity(f);
                        es.setFkPersona(currentPersona);
                        es.setCreadoPor(currentPersona.getCreadoPor());
                        return es;
                    }
                }));

        return this;
    }

    @Autowired
    DpiRepository dpiRepo;

    private PersonaCrearHandler saveDpi(ReqNuevaPersonaDto r) {
        Dpi dpi = new DpiDtoConverter().toEntity(r.getDpi());
        dpi.setFkPersona(currentPersona);
        dpi.setEstado(EstadoVariable.ACTUAL);
        dpi.setCreadoPor(currentPersona.getCreadoPor());
        EntitiesHelper.setDateCreateRef(dpi);

        dpiRepo.save(dpi);
        return this;
    }

    private PersonaCrearHandler saveLugarResidencia(ReqNuevaPersonaDto r) {
        LugarResidencia lr = new LugarResidenciaDtoConverter().toEntity(r.getLugarResidencia());
        lr.setFkPersona(currentPersona);
        lr.setEstado(EstadoVariable.ACTUAL);
        lr.setCreadoPor(currentPersona.getCreadoPor());
        EntitiesHelper.setDateCreateRef(lr);
        return this;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean execute(ReqNuevaPersonaDto r) {
        this
                .savePersona(r)
                .saveIdiomas(r)
                .saveRegAcademico(r)
                .saveRegLaboral(r)
                .saveEstudiosSalud(r)
                .saveDpi(r)
                .saveLugarResidencia(r);

        return true;
    }

}
