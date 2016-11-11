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
import gt.org.isis.repository.PersonasRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class PersonaCrearHandler extends AbstractValidationsRequestHandler<ReqNuevaPersonaDto, Boolean> {

    @Autowired
    PersonasRepository repo;
    @Autowired
    PersonaDtoConverter converter;
    @Autowired
    AreasGeografRepository areasRepo;

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

    @Override
    public Boolean execute(ReqNuevaPersonaDto r) {
        final Persona p = converter.toEntity(r);

        p.setEstado(Estado.ACTIVO);
        p.setCui(r.getCui());
        p.setCreadoPor("admin");
        if (r.getFechaNacimientoTexto() != null && !r.getFechaNacimientoTexto().isEmpty()) {
            p.setFechaNacimiento(parseFechaNacTexto(r.getFechaNacimientoTexto()));
        }
        p.setEdad(Years.yearsBetween(LocalDate.fromDateFields(p.getFechaNacimiento()),
                LocalDate.fromDateFields(Calendar.getInstance().getTime())).getYears());
        EntitiesHelper.setDateCreateRef(p);

        if (r.getFkMunicipioCedulaNombre() != null && !r.getFkMunicipioCedulaNombre().isEmpty()) {
            p.setFkMunicipioCedula(getAreaByNombreAndTipo(r.getFkMunicipioCedulaNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        }
        if (r.getFkMunicipioNacimientoNombre() != null && !r.getFkMunicipioNacimientoNombre().isEmpty()) {
            p.setFkMunicipioNacimiento(getAreaByNombreAndTipo(r.getFkMunicipioNacimientoNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        }
        if (r.getFkMunicipioVecindadNombre() != null && !r.getFkMunicipioVecindadNombre().isEmpty()) {
            p.setFkMunicipioVecindad(getAreaByNombreAndTipo(r.getFkMunicipioVecindadNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        }

        final Persona pp = repo.saveAndFlush(p);

        p.setIdiomaCollection(new ArrayList<Idioma>());
        p.getIdiomaCollection().addAll(Collections2.transform(r.getIdiomas(),
                new Function<IdiomaDto, Idioma>() {
            @Override
            public Idioma apply(IdiomaDto f) {
                Idioma i = new IdiomaDtoConverter().toEntity(f);
                i.setFkPersona(pp);
                i.setCreadoPor(p.getCreadoPor());
                EntitiesHelper.setDateCreateRef(i);
                return i;
            }
        }));

        RegistroAcademico ra;
        p.setRegistroAcademicoCollection(
                Arrays.asList(
                        ra = new RegistroAcademicoConverter().toEntity(r.getRegistroAcademico())));
        ra.setFkPersona(pp);
        ra.setEstado(EstadoVariable.ACTUAL);
        ra.setCreadoPor(p.getCreadoPor());
        EntitiesHelper.setDateCreateRef(ra);

        final RegistroLaboral rl = new RegistroLaboralConverter()
                .toEntity(r.getRegistroLaboral());
        p.setRegistroLaboralCollection(
                Arrays.asList(rl)
        );
        rl.setEstado(EstadoVariable.ACTUAL);
        rl.setFkPersona(pp);
        rl.setCreadoPor(p.getCreadoPor());
        rl.setPuestoCollection(new ArrayList<Puesto>(Collections2.transform(r.getRegistroLaboral().getPuestos(),
                new Function<RegistroLaboralPuestoDto, Puesto>() {
            @Override
            public Puesto apply(RegistroLaboralPuestoDto f) {
                Puesto ps = new RegistroLaboralPuestosConverter().toEntity(f);
                ps.setFkRegistroLaboral(rl);
                EntitiesHelper.setDateCreateRef(ps);
                ps.setCreadoPor("admin");
                return ps;
            }
        })));
        EntitiesHelper.setDateCreateRef(rl);

        p.setEstudioSaludCollection(new ArrayList());
        p.getEstudioSaludCollection().addAll(
                Collections2.transform(r.getEstudiosSalud(),
                        new Function<EstudioSaludDto, EstudioSalud>() {
                    @Override
                    public EstudioSalud apply(EstudioSaludDto f) {
                        EstudioSalud es = new EstudiosSaludConverter()
                                .toEntity(f);
                        es.setFkPersona(pp);
                        es.setCreadoPor(p.getCreadoPor());
                        return es;
                    }
                }));
        Dpi dpi;
        p.setDpiCollection(Arrays.asList(dpi = new DpiDtoConverter().toEntity(r.getDpi())));
        dpi.setFkPersona(pp);
        dpi.setEstado(EstadoVariable.ACTUAL);
        dpi.setCreadoPor(p.getCreadoPor());
        EntitiesHelper.setDateCreateRef(dpi);

        LugarResidencia lr;
        p.setLugarResidenciaCollection(Arrays.asList(
                lr = new LugarResidenciaDtoConverter().toEntity(r.getLugarResidencia())
        ));
        lr.setFkPersona(pp);
        lr.setEstado(EstadoVariable.ACTUAL);
        lr.setCreadoPor(p.getCreadoPor());
        EntitiesHelper.setDateCreateRef(lr);

        repo.saveAndFlush(pp);
        return true;
    }

}
