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
import gt.org.isis.controller.dto.IdiomaDto;
import gt.org.isis.controller.dto.LugarResidenciaDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.RefAreaGeograficaDto;
import gt.org.isis.controller.dto.RefClasificacionServiciDto;
import gt.org.isis.controller.dto.RefUnidadNotificadoraDto;
import gt.org.isis.controller.dto.RegistroAcademicoDto;
import gt.org.isis.controller.dto.RegistroLaboralDto;
import gt.org.isis.controller.dto.RegistroLaboralPuestoDto;
import gt.org.isis.controller.dto.RequestGetPersonaDto;
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
    public RequestGetPersonaDto execute(PersonaDto request) {
        Persona p = repo.findOne(request.getCui());
        RequestGetPersonaDto dto = new GetPersonaDtoConverter().toDTO(p);
        setDpiDto(p, dto);
        setDatosGenerales(p, dto);
        setRegistroLaboral(p, dto);
        setRegistroAcademico(p, dto);
        setIdiomas(p, dto);
        setEstudiosSalud(p, dto);
        setLugarResidencia(p, dto);

        return dto;
    }

    private void setDpiDto(Persona p, RequestGetPersonaDto dto) {
        if (p.getDpiCollection() != null && !p.getDpiCollection().isEmpty()) {
            dto.setDpi(new DpiDtoConverter().toDTO(Collections2.filter(p.getDpiCollection(), new Predicate<Dpi>() {
                @Override
                public boolean apply(Dpi t) {
                    return t.getEstado().equals(EstadoVariable.ACTUAL);
                }
            }).iterator().next()));
        }
    }

    public void setDatosGenerales(Persona p, RequestGetPersonaDto dto) {
        dto.setRefCedula(!isNull(dto.getFkMunicipioCedula())
                ? buildByMunicipio(dto.getFkMunicipioCedula()) : null);
        dto.setRefNacimiento(!isNull(dto.getFkMunicipioNacimiento())
                ? buildByMunicipio(dto.getFkMunicipioNacimiento()) : null);
        dto.setRefVecindad(!isNull(dto.getFkMunicipioNacimiento())
                ? buildByMunicipio(dto.getFkMunicipioVecindad()) : null);
    }

    private void fillExpectativa(RegistroLaboralDto rl) {
        Catalogos c = (Catalogos) catalogosRepo
                .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, rl.getFkExpectativa()));
        rl.setFkExpectativaNombre(c.getValor());
    }

    public void setRegistroLaboral(Persona p, RequestGetPersonaDto dto) {
        if (!isNull(p.getRegistroLaboralCollection()) && !p.getRegistroLaboralCollection().isEmpty()) {
            //registro laboral
            RegistroLaboralDto currentRL = new RegistroLaboralConverter().toDTO(p.getRegistroLaboralCollection().iterator().next());
            fillExpectativa(currentRL);
            for (RegistroLaboralPuestoDto rp : currentRL.getPuestos()) {
                fillRegistroPuesto(rp);
            }

            dto.setRegistroLaboral(currentRL);
        }
    }

    private void setRegistroAcademico(Persona p, RequestGetPersonaDto dto) {
        if (!isNull(p.getRegistroAcademicoCollection()) && !p.getRegistroAcademicoCollection().isEmpty()) {
            //registro academico
            RegistroAcademicoDto currentRA = new RegistroAcademicoConverter().toDTO(p.getRegistroAcademicoCollection().iterator().next());
            fillRegistroRA(currentRA);
            dto.setRegistroAcademico(currentRA);
        }
    }

    private void setIdiomas(Persona p, RequestGetPersonaDto dto) {
        dto.setIdiomas(new IdiomaDtoConverter().toDTO((List) p.getIdiomaCollection()));
        fillIdiomas(dto.getIdiomas());
    }

    private void setEstudiosSalud(Persona p, RequestGetPersonaDto dto) {
        dto.setEstudiosSalud(new EstudiosSaludConverter().toDTO((List) p.getEstudioSaludCollection()));
        fillEstudiosSalud(dto.getEstudiosSalud());
    }

    private void setLugarResidencia(Persona p, RequestGetPersonaDto dto) {
        if (!isNull(p.getLugarResidenciaCollection()) && !p.getLugarResidenciaCollection().isEmpty()) {
            dto.setLugarResidencia((LugarResidenciaDto) new LugarResidenciaDtoConverter()
                    .toDTO((List) p.getLugarResidenciaCollection()).iterator().next());
        }
        dto.getLugarResidencia().setRefLugarResidencia(buildByMunicipio(dto.getLugarResidencia().getFkMunicipio()));
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
            if (!isNull(c)) {
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
        System.out.println(">> carrera >> " + c);
        c = (Catalogos) catalogosRepo
                .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, c.getCodigoPadre()));
        System.out.println(">> grado >> " + c);
        if (!isNull(c)) {
            reg.setUltimoGrado(c.getId());
            reg.setNombreUltimoGrado(c.getValor());

            c = (Catalogos) catalogosRepo
                    .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, c.getCodigoPadre()));
            System.out.println(">> nivel >> " + c);
            if (!isNull(c)) {
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
            if (!isNull(c)) {
                reg.setGradoActual(c.getId());
                reg.setNombreGradoActual(c.getValor());

                c = (Catalogos) catalogosRepo
                        .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, c.getCodigoPadre()));
                if (!isNull(c)) {
                    reg.setNivelGradoActual(c.getId());
                    reg.setNivelGradoActualNombre(c.getValor());
                }
            }
        }
    }

}
