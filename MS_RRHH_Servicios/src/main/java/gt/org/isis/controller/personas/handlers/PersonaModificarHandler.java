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
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.converters.RegistroLaboralPuestosConverter;
import gt.org.isis.model.AreaGeografica;
import gt.org.isis.model.AreaGeografica_;
import gt.org.isis.model.Catalogos;
import gt.org.isis.model.Catalogos_;
import gt.org.isis.model.Dpi;
import gt.org.isis.model.EstudioSalud;
import gt.org.isis.model.HistoricoIdioma;
import gt.org.isis.model.HistoricoLugarResidencia;
import gt.org.isis.model.HistoricoPersona;
import gt.org.isis.model.HistoricoRegistroAcademico;
import gt.org.isis.model.HistoricoRegistroLaboral;
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
import gt.org.isis.repository.IdiomaHistoricoRepository;
import gt.org.isis.repository.IdiomaRepository;
import gt.org.isis.repository.LugarResidenciaHistoricoRepository;
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
import gt.org.isis.repository.PersonasHistoricoRepository;
import gt.org.isis.repository.RegistroAcademicoHistoricoRepository;
import gt.org.isis.repository.RegistroLaboralHistoricoRepository;
import java.util.Collection;

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
    PersonasHistoricoRepository historicoRepo;
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
    LugarResidenciaRepository registroLaboralRepo;
    @Autowired
    RegistroLaboralHistoricoRepository registroLabHistoricoRepo;
    @Autowired
    AreasGeografRepository areasRepo;
    @Autowired
    CatalogosRepository catalogosRepo;
    @Autowired
    PuestoRepository puestoRepo;
    @Autowired
    LugarResidenciaHistoricoRepository lugarResidenciaHis;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean execute(ReqModPersonaDto r) {
        Persona p = repo.findOne(r.getCui());
        actualizarDatosGenerales(p, r)
                .actualizaDpi(p, r)
                .actualizarIdiomas(p, r)
                .actualizaLugarResidencia(p, r)
                .actualizaRegistroAcademico(p, r)
                .actualizarEstudiosSalud(p, r)
                .actualizaRegistroLaboral(p, r);

        return true;
    }

    private void crearHistoricoPersona(Persona persona) {
        HistoricoPersona historicoPersona = new HistoricoPersona();
        BeanUtils.copyProperties(persona, historicoPersona);
        historicoPersona.setFkPersona(persona);
        setUpdateInfo(historicoPersona);

        historicoRepo.save(historicoPersona);
    }

    private PersonaModificarHandler actualizaLugarResidencia(Persona p, PersonaDto r) {
        LugarResidencia ra;
        crearHistoricoLugarResidencia(ra = p.getLugarResidenciaCollection().iterator().next());
        BeanUtils.copyProperties(r.getLugarResidencia(), ra);
        setUpdateInfo(ra);

        registroLaboralRepo.save(ra);
        return this;
    }

    private void crearHistoricoLugarResidencia(LugarResidencia original) {
        HistoricoLugarResidencia historico = new HistoricoLugarResidencia();
        BeanUtils.copyProperties(original, historico);
        setCreateInfo(historico);

        lugarResidenciaHis.save(historico);
    }

    private PersonaModificarHandler actualizarDatosGenerales(Persona currentPersona, ReqModPersonaDto updateRequest) {
        if (updateRequest.isLector()) {
            setDatosGeneralesByLector(currentPersona, updateRequest);
        }
        crearHistoricoPersona(currentPersona);
        BeanUtils.copyProperties(updateRequest, currentPersona);
        setUpdateInfo(currentPersona);

        repo.save(currentPersona);

        return this;
    }

    private void setDatosGeneralesByLector(Persona currentPersona, ReqModPersonaDto r) {
        if (!isNull(r.getFechaNacimientoTexto()) && !r.getFechaNacimientoTexto().isEmpty()) {
            currentPersona.setFechaNacimiento(parseFechaDPI(r.getFechaNacimientoTexto()));
        } else {
            throw ExceptionsManager.newValidationException("fecha_nacimiento", "fecha de nacimiento es requerida!");
        }

        currentPersona.setEdad(Years.yearsBetween(LocalDate.fromDateFields(currentPersona.getFechaNacimiento()),
                LocalDate.fromDateFields(Calendar.getInstance().getTime())).getYears());

        if (!isNull(r.getFkMunicipioNacimientoNombre()) && !r.getFkMunicipioNacimientoNombre().isEmpty()) {
            currentPersona.setFkMunicipioNacimiento(getAreaByNombreAndTipo(r.getFkMunicipioNacimientoNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        } else {
            throw ExceptionsManager.newValidationException("municipio_nacimiento", "municipio nacimiento es requerido!");
        }

        if (!isNull(r.getFkMunicipioCedulaNombre()) && !r.getFkMunicipioCedulaNombre().isEmpty()) {
            currentPersona.setFkMunicipioCedula(getAreaByNombreAndTipo(r.getFkMunicipioCedulaNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        }

        if (!isNull(r.getFkMunicipioVecindadNombre()) && !r.getFkMunicipioVecindadNombre().isEmpty()) {
            currentPersona.setFkMunicipioVecindad(getAreaByNombreAndTipo(r.getFkMunicipioVecindadNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        } else {
            throw ExceptionsManager.newValidationException("municipio_vecindad", "Municipio vecindad es requerido!");
        }
        if (!isNull(r.getFkNacionalidadNombre()) && !r.getFkNacionalidadNombre().isEmpty()) {
            currentPersona.setFkNacionalidad(getCatalogoByNombreAndTipo(r.getFkNacionalidadNombre(),
                    C.CAT_GEN_NACIONALIDAD).getId());
        } else {
            throw ExceptionsManager.newValidationException("municipio_nacimiento", "Nacionalidad es requerido!");
        }
        DpiDto dpiDto;
        if ((dpiDto = r.getDpi()) != null) {
            if (!isNull(dpiDto.getFechaEmisionTexto()) && !isNull(dpiDto.getFechaVencimientoTexto())) {
                dpiDto.setFechaEmision(parseFechaDPI(dpiDto.getFechaEmisionTexto()));
                dpiDto.setFechaVencimiento(parseFechaDPI(dpiDto.getFechaVencimientoTexto()));
            } else if (isNull(dpiDto.getFechaVencimiento()) || isNull(dpiDto.getFechaEmision())) {
                throw ExceptionsManager.newValidationException("dpi_datos",
                        new String[]{"fechas_dpi,Debe ingresar datos de fecha vencimiento y emision del DPI"});
            }
        } else {
            throw ExceptionsManager.newValidationException("dpi",
                    new String[]{"dpi,Lectura de DPI es invalida, datos del DPI son requeridos!"});
        }
    }

    private PersonaModificarHandler actualizarEstudiosSalud(Persona p, PersonaDto r) {
        estudiosRepo.deleteInBatch(p.getEstudioSaludCollection());
        for (EstudioSaludDto t : r.getEstudiosSalud()) {
            EstudioSalud i = new EstudiosSaludConverter().toEntity(t);
            i.setFkPersona(p);
            EntitiesHelper.setDateCreatedInfo(i);
        }
        return this;
    }

    private PersonaModificarHandler actualizarIdiomas(Persona p, PersonaDto r) {
        crearHistoricoIdiomas((List) p.getIdiomaCollection());
        for (IdiomaDto t : r.getIdiomas()) {
            Idioma i = new IdiomaDtoConverter().toEntity(t);
            i.setFkPersona(p);
            setCreateInfo(i);
            idiomasRepo.save(i);
        }
        return this;
    }

    @Autowired
    IdiomaHistoricoRepository idiomasHisRepo;

    public void crearHistoricoIdiomas(List<Idioma> idiomas) {
        idiomasHisRepo.save((Collection) Collections2.transform(idiomas,
                new Function<Idioma, HistoricoIdioma>() {
            @Override
            public HistoricoIdioma apply(Idioma f) {
                HistoricoIdioma hId = new HistoricoIdioma();
                BeanUtils.copyProperties(f, hId);
                setCreateInfo(hId);
                return hId;
            }
        }));
    }

    private PersonaModificarHandler actualizaRegistroAcademico(Persona p, PersonaDto r) {
        RegistroAcademico ra;
        crearHistoricoRegistroAcademico(ra = p.getRegistroAcademicoCollection().iterator().next());
        BeanUtils.copyProperties(r.getRegistroAcademico(), ra);
        setUpdateInfo(ra);
        rAcaRepository.save(ra);

        return this;
    }

    @Autowired
    RegistroAcademicoHistoricoRepository raHisRepo;

    private void crearHistoricoRegistroAcademico(RegistroAcademico ra) {
        HistoricoRegistroAcademico hRa = new HistoricoRegistroAcademico();
        BeanUtils.copyProperties(ra, hRa);
        setCreateInfo(hRa);
        raHisRepo.save(hRa);
    }

    private PersonaModificarHandler actualizaRegistroLaboral(final Persona persona,
            ReqModPersonaDto requestModPersona) {
        final RegistroLaboral registroLaboral;
        crearHistoricoRegistroLaboral(registroLaboral = persona.getRegistroLaboralCollection().iterator().next());
        BeanUtils.copyProperties(requestModPersona.getRegistroLaboral(), registroLaboral);
        setUpdateInfo(registroLaboral);

        registroLaboral.getPuestoCollection().clear();
        rLabRepository.save(registroLaboral);

        puestoRepo.delete((List) registroLaboral.getPuestoCollection());
        puestoRepo.save((Collection) Collections2
                .transform(requestModPersona.getRegistroLaboral().getPuestos(),
                        new Function<RegistroLaboralPuestoDto, Puesto>() {
                    @Override
                    public Puesto apply(RegistroLaboralPuestoDto f) {
                        Puesto puesto = new RegistroLaboralPuestosConverter().toEntity(f);
                        puesto.setFkRegistroLaboral(registroLaboral);
                        setCreateInfo(puesto);
                        return puesto;
                    }
                }));
        return this;
    }

    private void crearHistoricoRegistroLaboral(RegistroLaboral actual) {
        HistoricoRegistroLaboral historico = new HistoricoRegistroLaboral();
        BeanUtils.copyProperties(actual, historico);
        setCreateInfo(historico);
        registroLabHistoricoRepo.save(historico);
    }

    private PersonaModificarHandler actualizaDpi(Persona p, final ReqModPersonaDto r) {
        Collection<Dpi> ls = Collections2.filter(p.getDpiCollection(), new com.google.common.base.Predicate<Dpi>() {
            @Override
            public boolean apply(Dpi t) {
                return t.getNoSerie().equals(r.getDpi().getNoSerie());
            }
        });
        if (ls.isEmpty()) {
            Dpi ra = new DpiDtoConverter()
                    .toEntity(r.getDpi());
            ra.setEstado(EstadoVariable.ACTUAL);
            ra.setFkPersona(p);
            setCreateInfo(ra);

            dpiRepository.archivarRegitro(p.getCui());
            dpiRepository.save(ra);
        }
        return this;
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

}
