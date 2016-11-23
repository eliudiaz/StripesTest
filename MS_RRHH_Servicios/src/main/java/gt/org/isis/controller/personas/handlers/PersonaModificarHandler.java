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
import static gt.org.isis.api.ValidationsHelper.containsAny;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.DpiDto;
import gt.org.isis.controller.dto.EstudioSaludDto;
import gt.org.isis.controller.dto.IdiomaDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.RegistroLaboralPuestoDto;
import gt.org.isis.controller.dto.ReqModPersonaDto;
import gt.org.isis.converters.DpiDtoConverter;
import gt.org.isis.converters.EstudiosSaludConverter;
import gt.org.isis.converters.IdiomaDtoConverter;
import gt.org.isis.converters.LugarResidenciaDtoConverter;
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.converters.PersonaHistorialConverter;
import gt.org.isis.converters.RegistroAcademicoConverter;
import gt.org.isis.converters.RegistroLaboralConverter;
import gt.org.isis.converters.RegistroLaboralPuestosConverter;
import gt.org.isis.model.AreaGeografica;
import gt.org.isis.model.AreaGeografica_;
import gt.org.isis.model.Catalogos;
import gt.org.isis.model.Catalogos_;
import gt.org.isis.model.Dpi;
import gt.org.isis.model.EstudioSalud;
import gt.org.isis.model.HistoricoPersona;
import gt.org.isis.model.Idioma;
import gt.org.isis.model.LugarResidencia;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.RegistroAcademico;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.model.enums.EstadoVariable;
import gt.org.isis.model.utils.EntitiesHelper;
import static gt.org.isis.model.utils.EntitiesHelper.parseFechaDPI;
import gt.org.isis.repository.AreasGeografRepository;
import gt.org.isis.repository.CatalogosRepository;
import gt.org.isis.repository.DpiRepository;
import gt.org.isis.repository.EstudiosSaludRepository;
import gt.org.isis.repository.HIstoricoPersonasRepository;
import gt.org.isis.repository.IdiomaRepository;
import gt.org.isis.repository.LugarResidenciaRepository;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.PuestoRepository;
import gt.org.isis.repository.RegistroAcademicoRepository;
import gt.org.isis.repository.RegistroLaboralRepository;
import java.util.Calendar;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.BeanUtils;
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
public class PersonaModificarHandler extends AbstractValidationsRequestHandler<ReqModPersonaDto, Boolean> {

    @Autowired
    PersonasRepository repo;
    @Autowired
    HIstoricoPersonasRepository historicoRepo;
    @Autowired
    IdiomaRepository idiomasRepo;
    @Autowired
    EstudiosSaludRepository estudiosRepo;
    @Autowired
    PersonaDtoConverter converter;
    @Autowired
    RegistroAcademicoRepository rAcaRepository;
    @Autowired
    RegistroLaboralRepository rLabRepository;
    @Autowired
    DpiRepository dpiRepository;
    @Autowired
    LugarResidenciaRepository lrRepository;
    @Autowired
    AreasGeografRepository areasRepo;
    @Autowired
    CatalogosRepository catalogosRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean execute(ReqModPersonaDto r) {
        Persona p = repo.findOne(r.getCui());
        p = guardaPersonaDatos(p, r);
        crearHistorico(p);
        guardaIdiomas(p, r);
        guardaEstudiosSalud(p, r);
        actualizaRegistroAcademico(p, r);
        actualizaRegistroLaboral(p, r);
        actualizaDpi(p, r);
        actualizaLugarResidencia(p, r);

        return true;
    }

    private Persona guardaPersonaDatos(Persona currentPersona, ReqModPersonaDto r) {
        BeanUtils.copyProperties(r, currentPersona);

        currentPersona.setUltimoCambioPor("admin");
        currentPersona.setFechaUltimoCambio(Calendar.getInstance().getTime());
        if (r.getFechaNacimientoTexto() != null && !r.getFechaNacimientoTexto().isEmpty()) {
            currentPersona.setFechaNacimiento(parseFechaDPI(r.getFechaNacimientoTexto()));
        }
        currentPersona.setEdad(Years.yearsBetween(LocalDate.fromDateFields(currentPersona.getFechaNacimiento()),
                LocalDate.fromDateFields(Calendar.getInstance().getTime())).getYears());

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
        if (r.getFkNacionalidadNombre() != null && !r.getFkNacionalidadNombre().isEmpty()) {
            currentPersona.setFkNacionalidad(getCatalogoByNombreAndTipo(r.getFkNacionalidadNombre(),
                    C.CAT_GEN_NACIONALIDAD).getId());
        }
        return repo.save(currentPersona);
    }

    private Catalogos getCatalogoByNombreAndTipo(final String nombre, final String tipo) {
        List<Catalogos> all = catalogosRepo.findAll(new Specification<Catalogos>() {
            @Override
            public Predicate toPredicate(Root<Catalogos> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(Catalogos_.tipo), tipo);
            }
        });
        for (Catalogos ag : all) {
            if (containsAny(ag.getValor(), nombre)) {
                return ag;
            }
        }
        return all.get(0);
    }

    private AreaGeografica getAreaByNombreAndTipo(final String nombre, final String tipo) {
        List<AreaGeografica> all = areasRepo.findAll(new Specification<AreaGeografica>() {
            @Override
            public Predicate toPredicate(Root<AreaGeografica> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(AreaGeografica_.tipo), tipo);
            }
        });
        for (AreaGeografica ag : all) {
            if (containsAny(ag.getValor(), nombre)) {
                return ag;
            }
        }
        return all.get(0);
    }

    private void guardaEstudiosSalud(Persona p, PersonaDto r) {
        estudiosRepo.deleteInBatch(p.getEstudioSaludCollection());

        for (EstudioSaludDto t : r.getEstudiosSalud()) {
            EstudioSalud i = new EstudiosSaludConverter().toEntity(t);
            i.setFkPersona(p);
            EntitiesHelper.setDateCreateRef(i);
            estudiosRepo.save(i);
        }
    }

    private void guardaIdiomas(Persona p, PersonaDto r) {
        idiomasRepo.deleteInBatch(p.getIdiomaCollection());
        for (IdiomaDto t : r.getIdiomas()) {
            Idioma i = new IdiomaDtoConverter().toEntity(t);
            i.setFkPersona(p);
            EntitiesHelper.setDateCreateRef(i);
            idiomasRepo.save(i);
        }
    }

    private void crearHistorico(Persona p) {
        HistoricoPersona hp = new PersonaHistorialConverter()
                .toEntity(new PersonaDtoConverter().toDTO(p));
        hp.setFkPersona(p);
        hp.setEdad(p.getEdad());
        EntitiesHelper.setDateCreateRef(hp);
        hp.setCreadoPor(p.getUltimoCambioPor());
        historicoRepo.save(hp);
    }

    private void actualizaRegistroAcademico(Persona p, PersonaDto r) {
        rAcaRepository.archivarRegitro(p.getCui());
        RegistroAcademico ra = new RegistroAcademicoConverter()
                .toEntity(r.getRegistroAcademico());
        ra.setEstado(EstadoVariable.ACTUAL);
        ra.setFkPersona(p);
        ra.setCreadoPor(p.getUltimoCambioPor());
        EntitiesHelper.setDateCreateRef(ra);
        rAcaRepository.save(ra);
    }

    @Autowired
    PuestoRepository puestoRepo;

    private void actualizaRegistroLaboral(final Persona p, PersonaDto r) {
        rLabRepository.archivarRegitro(p.getCui());
        final RegistroLaboral rl = new RegistroLaboralConverter()
                .toEntity(r.getRegistroLaboral());
        rl.setEstado(EstadoVariable.ACTUAL);
        rl.setFkPersona(p);
        rl.setCreadoPor(p.getUltimoCambioPor());
        EntitiesHelper.setDateCreateRef(rl);
        rLabRepository.save(rl);

        puestoRepo.save(Collections2
                .transform(r.getRegistroLaboral().getPuestos(),
                        new Function<RegistroLaboralPuestoDto, Puesto>() {
                    @Override
                    public Puesto apply(RegistroLaboralPuestoDto f) {
                        Puesto ps = new RegistroLaboralPuestosConverter().toEntity(f);
                        ps.setFkRegistroLaboral(rl);
                        EntitiesHelper.setDateCreateRef(ps);
                        ps.setCreadoPor(p.getUltimoCambioPor());
                        return ps;
                    }
                }));

    }

    private void actualizaDpi(Persona p, PersonaDto r) {
        if (!p.getDpiCollection().isEmpty()) {
            for (Dpi d : p.getDpiCollection()) {
                if (r.getDpi().getNoSerie().equals(d.getNoSerie())) {
                    throw ExceptionsManager.newValidationException("invalid_dpi",
                            new String[]{"no_serie,Ya tiene un DPI con ese numero de serie!"});
                }
            }
        }
        DpiDto dpiDto = r.getDpi();
        if (!isNull(dpiDto.getFechaEmisionTexto()) && !isNull(dpiDto.getFechaVencimientoTexto())) {
            dpiDto.setFechaEmision(parseFechaDPI(dpiDto.getFechaEmisionTexto()));
            dpiDto.setFechaVencimiento(parseFechaDPI(dpiDto.getFechaVencimientoTexto()));
        } else if (isNull(dpiDto.getFechaVencimiento()) || isNull(dpiDto.getFechaEmision())) {
            throw ExceptionsManager.newValidationException("dpi_datos",
                    new String[]{"fechas_dpi,Debe ingresar datos de fecha vencimiento y emision del DPI"});
        }
        dpiRepository.archivarRegitro(p.getCui());
        Dpi ra = new DpiDtoConverter()
                .toEntity(dpiDto);
        ra.setEstado(EstadoVariable.ACTUAL);
        ra.setFkPersona(p);
        ra.setCreadoPor(p.getUltimoCambioPor());
        EntitiesHelper.setDateCreateRef(ra);
        dpiRepository.save(ra);
    }

    private void actualizaLugarResidencia(Persona p, PersonaDto r) {
        lrRepository.archivarRegitro(p.getCui());
        LugarResidencia ra = new LugarResidenciaDtoConverter()
                .toEntity(r.getLugarResidencia());
        ra.setEstado(EstadoVariable.ACTUAL);
        ra.setFkPersona(p);
        ra.setCreadoPor(p.getUltimoCambioPor());
        EntitiesHelper.setDateCreateRef(ra);
        lrRepository.save(ra);
    }

}
