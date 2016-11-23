/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.api.C;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.jpa.SingularAttrSpecificationBased;
import gt.org.isis.controller.dto.EstudioSaludDto;
import gt.org.isis.controller.dto.GetPersonaDto;
import gt.org.isis.controller.dto.IdiomaDto;
import gt.org.isis.controller.dto.LugarResidenciaDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.RefAreaGeograficaDto;
import gt.org.isis.controller.dto.RefClasificacionServiciDto;
import gt.org.isis.controller.dto.RefUnidadNotificadoraDto;
import gt.org.isis.controller.dto.RegistroAcademicoDto;
import gt.org.isis.controller.dto.RegistroLaboralDto;
import gt.org.isis.controller.dto.RegistroLaboralPuestoDto;
import gt.org.isis.converters.DpiDtoConverter;
import gt.org.isis.converters.EstudiosSaludConverter;
import gt.org.isis.converters.GetPersonaDtoConverter;
import gt.org.isis.converters.IdiomaDtoConverter;
import gt.org.isis.converters.LugarResidenciaDtoConverter;
import gt.org.isis.converters.RegistroAcademicoConverter;
import gt.org.isis.converters.RegistroLaboralConverter;
import gt.org.isis.model.AreaGeografica;
import gt.org.isis.model.Catalogos;
import gt.org.isis.model.Catalogos_;
import gt.org.isis.model.Dpi;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Puestos;
import gt.org.isis.model.RegistroAcademico;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.model.UnidadEjecutora;
import gt.org.isis.model.UnidadNotificadora;
import gt.org.isis.model.enums.EstadoVariable;
import gt.org.isis.repository.AreasGeografRepository;
import gt.org.isis.repository.CatalogosRepository;
import gt.org.isis.repository.IdiomaRepository;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.PuestosRepository;
import gt.org.isis.repository.UnidadEjecutoraRepository;
import gt.org.isis.repository.UnidadNotificadoraRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonaBusquedaSimpleHandler extends AbstractRequestHandler<PersonaDto, PersonaDto> {

    @Autowired
    PersonasRepository repo;
    @Autowired
    AreasGeografRepository areasRepo;
    @Autowired
    UnidadNotificadoraRepository unidadNotificadora;
    @Autowired
    CatalogosRepository catalogosRepo;
    @Autowired
    UnidadEjecutoraRepository ueRepo;
    @Autowired
    IdiomaRepository idiomasRepo;
    @Autowired
    PuestosRepository puestosRepo;

    @Override
    public GetPersonaDto execute(PersonaDto request) {
        Persona p = repo.findOne(request.getCui());
        final GetPersonaDto dto = new GetPersonaDtoConverter().toDTO(p);
        if (p.getDpiCollection() != null && !p.getDpiCollection().isEmpty()) {
            dto.setDpi(new DpiDtoConverter().toDTO(Collections2.filter(p.getDpiCollection(), new Predicate<Dpi>() {
                @Override
                public boolean apply(Dpi t) {
                    return t.getEstado().equals(EstadoVariable.ACTUAL);
                }
            }).iterator().next()));
        }

        dto.setRefCedula(dto.getFkMunicipioCedula() != null
                ? buildByMunicipio(dto.getFkMunicipioCedula()) : null);
        dto.setRefNacimiento(dto.getFkMunicipioNacimiento() != null
                ? buildByMunicipio(dto.getFkMunicipioNacimiento()) : null);
        dto.setRefVecindad(dto.getFkMunicipioNacimiento() != null
                ? buildByMunicipio(dto.getFkMunicipioVecindad()) : null);

        //registro laboral
        RegistroLaboralDto currentRL = null;
        List<RegistroLaboralDto> histRL = new ArrayList<RegistroLaboralDto>();
        for (RegistroLaboral rl : p.getRegistroLaboralCollection()) {
            if (rl.getEstado().equals(EstadoVariable.ACTUAL)) {
                currentRL = new RegistroLaboralConverter().toDTO(rl);
                for (RegistroLaboralPuestoDto rp : currentRL.getPuestos()) {
                    fillRegistroPuesto(rp);
                }
                currentRL.setHistorial(histRL);
            } else {
                histRL.add(new RegistroLaboralConverter().toDTO(rl));
            }
        }

        if (isNull(currentRL)) {
            currentRL = !histRL.isEmpty() ? histRL.get(0) : null;
        }
        dto.setRegistroLaboral(currentRL);

        //registro academico
        List<RegistroAcademicoDto> hisRA = new ArrayList<RegistroAcademicoDto>();
        RegistroAcademicoDto currentRA = null;
        for (RegistroAcademico ra : p.getRegistroAcademicoCollection()) {
            if (ra.getEstado().equals(EstadoVariable.ACTUAL)) {
                currentRA = new RegistroAcademicoConverter().toDTO(ra);
                fillRegistroRA(currentRA);
                currentRA.setHistorial(hisRA);
            } else {
                hisRA.add(new RegistroAcademicoConverter().toDTO(ra));
            }
        }
        if (isNull(currentRA)) {
            currentRA = !hisRA.isEmpty() ? hisRA.get(0) : null;
        }
        dto.setRegistroAcademico(currentRA);

        dto.setIdiomas(new IdiomaDtoConverter().toDTO((List) p.getIdiomaCollection()));
        fillIdiomas(dto.getIdiomas());

        dto.setEstudiosSalud(new EstudiosSaludConverter().toDTO((List) p.getEstudioSaludCollection()));
        fillEstudiosSalud(dto.getEstudiosSalud());

        if (p.getLugarResidenciaCollection() != null && !p.getLugarResidenciaCollection().isEmpty()) {
            dto.setLugarResidencia((LugarResidenciaDto) Collections2.filter(new LugarResidenciaDtoConverter().toDTO((List) p.getLugarResidenciaCollection()),
                    new Predicate<LugarResidenciaDto>() {
                @Override
                public boolean apply(LugarResidenciaDto t) {
                    return t.getEstado().equals(EstadoVariable.ACTUAL);
                }
            }).iterator().next());
        }
        dto.getLugarResidencia().setRefLugarResidencia(buildByMunicipio(dto.getLugarResidencia().getFkMunicipio()));

        return dto;
    }

    private RefUnidadNotificadoraDto buildByComunidad(Integer fkComunidadId) {
        RefUnidadNotificadoraDto ref = new RefUnidadNotificadoraDto();
        UnidadNotificadora un = unidadNotificadora.findOne(fkComunidadId);

        if (un.getTipo().equals(C.CAT_UN_COMUNIDAD2)) {
            ref.setFkComunidad2(un.getId());
            ref.setNombreComunidad2(un.getValor());
            if (!isNull(un.getCodigoPadre())) {
                un = unidadNotificadora.findOne(un.getCodigoPadre());
            } else {
                return ref;
            }
        }

        ref.setFkComunidad(fkComunidadId);
        ref.setNombreComunidad(un.getValor());

        un = unidadNotificadora.findOne(un.getCodigoPadre());
        if (!isNull(un)) {
            ref.setFkLugarEspecifico(un.getId());
            ref.setNombreLugarEspecifico(un.getValor());

            un = unidadNotificadora.findOne(un.getCodigoPadre());
            if (!isNull(un)) {
                ref.setFkDistrito(un.getId());
                ref.setNombreDistrito(un.getValor());

                UnidadEjecutora ue = ueRepo.findOne(un.getCodigoPadre());
                ref.setFkUnidadEjecutora(ue.getId());
                ref.setFkUnidadEjecutoraNombre(ue.getNombre());
            }
        }
        return ref;
    }

    private RefAreaGeograficaDto buildByMunicipio(Integer fkMunicipio) {
        RefAreaGeograficaDto refArea = new RefAreaGeograficaDto();
        AreaGeografica ag = areasRepo.findOne(fkMunicipio);
        refArea.setFkMunicio(ag.getId());
        refArea.setFkMunicioNombre(ag.getValor());

        ag = areasRepo.findOne(ag.getCodigoPadre());
        if (!isNull(ag)) {

            refArea.setFkDepartamento(ag.getId());
            refArea.setFkDepartamentoNombre(ag.getValor());

            ag = areasRepo.findOne(ag.getCodigoPadre());
            if (!isNull(ag)) {
                refArea.setFkPais(ag.getId());
                refArea.setFkPaisNombre(ag.getValor());
            }

        }
        return refArea;
    }

    private void fillIdiomas(List<IdiomaDto> idiomas) {
        for (IdiomaDto i : idiomas) {
            i.setNombre(catalogosRepo.findOne(i.getFkIdioma()).getValor());
        }
    }

    private void fillEstudiosSalud(List<EstudioSaludDto> estudios) {
        for (EstudioSaludDto i : estudios) {
            if (i.getFkEstudioSalud() != null) {
                i.setNombre(catalogosRepo.findOne(i.getFkEstudioSalud()).getValor());
            }
        }
    }

    private RefClasificacionServiciDto buildByClasificacionServicio(Integer fkClasificacionServicio) {
        RefClasificacionServiciDto refClasificacion = new RefClasificacionServiciDto();
        Catalogos c = catalogosRepo.findOne(fkClasificacionServicio);
        refClasificacion.setFkAreaServicio(c.getId());
        refClasificacion.setFkAreaServicioNombre(c.getValor());

        if (c.getCodigoPadre() != null) {
            c = catalogosRepo.findOne(c.getCodigoPadre());
            if (c != null) {
                refClasificacion.setFkClasificacionServicio(c.getId());
                refClasificacion.setFkClasificacionServicioNombre(c.getValor());
            }
        }
        return refClasificacion;
    }

    private void fillRegistroPuesto(RegistroLaboralPuestoDto reg) {
        reg.setRefClasificacionServicio(buildByClasificacionServicio(reg.getFkClasificacionServicio()));
        reg.setRefUnidadNotificadora(buildByComunidad(reg.getFkComunidad()));

        Puestos catPuestos = puestosRepo.findOne(reg.getFkPuestoNominal());
        reg.setNombrePuestoNominal(catPuestos.getValor());

        catPuestos = puestosRepo.findOne(catPuestos.getCodigoPadre());
        reg.setFkPuestoNominalRenglon(catPuestos.getId());
        reg.setNombrePuestoNominalRenglon(catPuestos.getValor());

        Catalogos cat = catalogosRepo.findOne(reg.getFkPuestoFuncional());
        reg.setNombrePuestoFuncional(cat.getValor());

    }

    private void fillRegistroRA(RegistroAcademicoDto reg) {
        Catalogos c = (Catalogos) catalogosRepo
                .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, reg.getUltimoGrado()));
        reg.setCarreraUltimoGradoNombre(c.getValor());
        reg.setCarreraUltimoGrado(c.getId());

        c = (Catalogos) catalogosRepo
                .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, c.getCodigoPadre()));
        if (c != null) {
            reg.setUltimoGrado(c.getId());
            reg.setNombreUltimoGrado(c.getValor());

            c = (Catalogos) catalogosRepo
                    .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, c.getCodigoPadre()));
            if (c != null) {
                reg.setNivelUltimoGrado(c.getId());
                reg.setNivelUltimoGradoNombre(c.getValor());
            }
        }

        if (reg.isEstudiaActualmente()) {
            c = (Catalogos) catalogosRepo
                    .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, reg.getGradoActual()));
            reg.setCarreraGradoActual(c.getId());
            reg.setCarreraGradoActualNombre(c.getValor());

            c = (Catalogos) catalogosRepo
                    .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, c.getCodigoPadre()));
            if (c != null) {
                reg.setNivelGradoActual(c.getId());
                reg.setNivelGradoActualNombre(c.getValor());

                c = (Catalogos) catalogosRepo
                        .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, c.getCodigoPadre()));
                if (c != null) {
                    reg.setNivelGradoActual(c.getId());
                    reg.setNivelGradoActualNombre(c.getValor());
                }
            }
        }
    }

}
