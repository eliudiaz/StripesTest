/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas.handlers;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractRequestHandler;
import gt.org.ms.api.C;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.jpa.SingularAttrSpecificationBased;
import static gt.org.ms.api.utils.EntitiesHelper.formatDate;
import gt.org.ms.controller.dto.EstudioSaludDto;
import gt.org.ms.controller.dto.IdiomaDto;
import gt.org.ms.controller.dto.LugarResidenciaDto;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.dto.RefAreaGeograficaDto;
import gt.org.ms.controller.dto.RefClasificacionServiciDto;
import gt.org.ms.controller.dto.RefUnidadNotificadoraDto;
import gt.org.ms.controller.dto.RegistroAcademicoDto;
import gt.org.ms.controller.dto.RegistroLaboralDto;
import gt.org.ms.controller.dto.RegistroLaboralPuestoDto;
import gt.org.ms.controller.dto.RequestGetPersonaDto;
import gt.org.ms.converters.DpiDtoConverter;
import gt.org.ms.converters.EstudiosSaludConverter;
import gt.org.ms.converters.GetPersonaDtoConverter;
import gt.org.ms.converters.IdiomaDtoConverter;
import gt.org.ms.converters.LugarResidenciaDtoConverter;
import gt.org.ms.converters.RegistroAcademicoConverter;
import gt.org.ms.converters.RegistroLaboralConverter;
import gt.org.ms.model.AreaGeografica;
import gt.org.ms.model.Catalogos;
import gt.org.isis.model.Catalogos_;
import gt.org.ms.model.Dpi;
import gt.org.ms.model.Persona;
import gt.org.ms.model.Puestos;
import gt.org.ms.model.UnidadEjecutora;
import gt.org.ms.model.UnidadNotificadora;
import gt.org.ms.model.enums.EstadoCivil;
import gt.org.ms.model.enums.EstadoVariable;
import gt.org.ms.repository.AreasGeografRepository;
import gt.org.ms.repository.CatalogosRepository;
import gt.org.ms.repository.IdiomaRepository;
import gt.org.ms.repository.PersonasRepository;
import gt.org.ms.repository.PuestosRepository;
import gt.org.ms.repository.UnidadEjecutoraRepository;
import gt.org.ms.repository.UnidadNotificadoraRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    Map<String, EstadoCivil> mapFemToMas = new HashMap<String, EstadoCivil>();

    {
        mapFemToMas.put(EstadoCivil.CASADA.toString(), EstadoCivil.CASADO);
        mapFemToMas.put(EstadoCivil.SOLTERA.toString(), EstadoCivil.SOLTERO);
        mapFemToMas.put(EstadoCivil.VIUDA.toString(), EstadoCivil.VIUDO);
    }

    public EstadoCivil convertEstadoCivil(EstadoCivil estado) {
        if (mapFemToMas.containsKey(estado.toString())) {
            return mapFemToMas.get(estado.toString());
        }
        return estado;
    }

    @Override
    public RequestGetPersonaDto execute(PersonaDto request) {
        Persona persona = repo.findOne(request.getCui());
        RequestGetPersonaDto personaDto = new GetPersonaDtoConverter().toDTO(persona);
        setDpiDto(persona, personaDto);
        setDatosGenerales(persona, personaDto);
        setRegistroLaboral(persona, personaDto);
        setRegistroAcademico(persona, personaDto);
        setIdiomas(persona, personaDto);
        setEstudiosSalud(persona, personaDto);
        setLugarResidencia(persona, personaDto);

        return personaDto;
    }

    private void setDpiDto(Persona p, RequestGetPersonaDto dto) {
        if (p.getDpiCollection() != null && !p.getDpiCollection().isEmpty()) {

            dto.setDpi(new DpiDtoConverter().toDTO(p.getDpiCollection().size() > 1 ? Collections2.filter(p.getDpiCollection(), new Predicate<Dpi>() {
                @Override
                public boolean apply(Dpi t) {
                    return t.getEstado().equals(EstadoVariable.ACTUAL);
                }
            }).iterator().next() : p.getDpiCollection().iterator().next()));
        }
    }

    public void setDatosGenerales(Persona p, RequestGetPersonaDto dto) {
        dto.setRefCedula(!isNull(dto.getFkMunicipioCedula())
                ? buildByMunicipio(dto.getFkMunicipioCedula(), null) : null);
        dto.setRefNacimiento(!isNull(dto.getFkMunicipioNacimiento())
                ? buildByMunicipio(dto.getFkMunicipioNacimiento(), null) : null);
        dto.setRefVecindad(!isNull(dto.getFkMunicipioNacimiento())
                ? buildByMunicipio(dto.getFkMunicipioVecindad(), null) : null);
        fillComunidadLinguistica(dto);
        fillNacionalidad(dto);
        dto.setFechaNacimientoTexto(formatDate(dto.getFechaNacimiento()));
        dto.setEstadoCivil(convertEstadoCivil(p.getEstadoCivil()));
    }

    private Catalogos findById(Integer id) {
        Catalogos c = (Catalogos) catalogosRepo
                .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, id));
        return c;
    }

    private void fillNacionalidad(PersonaDto p) {
        p.setFkNacionalidadNombre(findById(p.getFkNacionalidad()).getValor());
    }

    private void fillComunidadLinguistica(PersonaDto p) {
        Catalogos c = findById(p.getFkComunidadLinguistica());
        p.setFkComunidadLinguisticaNombre(isNull(c) ? "No tiene" : c.getValor());
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
            if (currentRL.isComisionado()) {
                currentRL.setRefUnidadNotificadoraComisionado(getUnidadNotificadora(currentRL.getFkComunidadComisionado()));
            }
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
        dto.getLugarResidencia().setRefLugarResidencia(buildByMunicipio(dto.getLugarResidencia().getFkMunicipio(), null));
    }

    private RefUnidadNotificadoraDto getUnidadNotificadora(Integer id) {
        return getUnidadNotificadora(id, null);
    }

    private RefUnidadNotificadoraDto getUnidadNotificadora(Integer id, RefUnidadNotificadoraDto ref) {
        if (isNull(ref)) {
            ref = new RefUnidadNotificadoraDto();
        }
        UnidadNotificadora un = unidadNotificadora.findOne(id);
        if (isNull(un)) {
            return ref;
        }
        if (un.getTipo().equalsIgnoreCase(C.CAT_UN_COMUNIDAD2)) {
            ref.setFkComunidad2(id);
            ref.setNombreComunidad2(un.getValor());
        }
        if (un.getTipo().equalsIgnoreCase(C.CAT_UN_COMUNIDAD)) {
            ref.setFkComunidad(id);
            ref.setNombreComunidad(un.getValor());
        }
        if (un.getTipo().equalsIgnoreCase(C.CAT_UN_DISTRITO)) {
            ref.setFkDistrito(id);
            ref.setNombreDistrito(un.getValor());
            UnidadEjecutora ue = ueRepo.findOne(un.getCodigoPadre());
            ref.setFkUnidadEjecutora(isNull(ue) ? null : ue.getId());
            ref.setFkUnidadEjecutoraNombre(isNull(ue) ? null : ue.getNombre());
        }
        if (un.getTipo().equalsIgnoreCase(C.CAT_UN_LUGAR_ESPEC)) {
            ref.setFkLugarEspecifico(id);
            ref.setNombreLugarEspecifico(un.getValor());
        }
        if (!isNull(un.getCodigoPadre()) && !un.getTipo().equalsIgnoreCase(C.CAT_UN_DISTRITO)) {
            return getUnidadNotificadora(un.getCodigoPadre(), ref);
        }

        return ref;
    }

    private RefAreaGeograficaDto buildByMunicipio(Integer id, RefAreaGeograficaDto refArea) {
        if (isNull(id)) {
            return null;
        }
        if (isNull(refArea)) {
            refArea = new RefAreaGeograficaDto();
        }
        AreaGeografica ag = areasRepo.findOne(id);
        if (!isNull(ag)) {
            if (ag.getTipo().equalsIgnoreCase(C.CAT_AG_TIPO_MUNICIPIOS)) {
                refArea.setFkMunicio(ag.getId());
                refArea.setFkMunicioNombre(ag.getValor());
            }
            if (ag.getTipo().equalsIgnoreCase(C.CAT_AG_TIPO_DEPTOS)) {
                refArea.setFkDepartamento(ag.getId());
                refArea.setFkDepartamentoNombre(ag.getValor());
            }
            if (ag.getTipo().equalsIgnoreCase(C.CAT_AG_TIPO_PAISES)) {
                refArea.setFkPais(ag.getId());
                refArea.setFkPaisNombre(ag.getValor());
            }
            if (!isNull(ag.getCodigoPadre())) {
                buildByMunicipio(ag.getCodigoPadre(), refArea);
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
        reg.setRefUnidadNotificadora(getUnidadNotificadora(reg.getFkComunidad()));

        Puestos catPuestos = puestosRepo.findOne(reg.getFkPuestoNominal());
        reg.setNombrePuestoNominal(catPuestos.getValor());

        catPuestos = puestosRepo.findOne(catPuestos.getCodigoPadre());
        reg.setFkPuestoNominalRenglon(catPuestos.getId());
        reg.setNombrePuestoNominalRenglon(catPuestos.getValor());

        Catalogos cat = catalogosRepo.findOne(reg.getFkPuestoFuncional());
        reg.setNombrePuestoFuncional(cat.getValor());
    }

    private RegistroAcademicoDto fillUltimoGrado(RegistroAcademicoDto reg, Integer idCatalogo) {
        Catalogos c = (Catalogos) catalogosRepo
                .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, idCatalogo));
        if (c.getTipo().equals(C.CAT_GEN_NIV_EDUCATIVO_CARRERA)) {
            reg.setCarreraUltimoGradoNombre(c.getValor());
            reg.setCarreraUltimoGrado(c.getId());
        }
        if (c.getTipo().equals(C.CAT_GEN_NIV_EDUCATIVO_GRADO)) {
            reg.setUltimoGrado(c.getId());
            reg.setNombreUltimoGrado(c.getValor());
        }
        if (c.getTipo().equals(C.CAT_GEN_NIV_EDUCATIVO)) {
            reg.setNivelUltimoGrado(c.getId());
            reg.setNivelUltimoGradoNombre(c.getValor());
        }
        if (!isNull(c.getCodigoPadre())) {
            fillUltimoGrado(reg, c.getCodigoPadre());
        }
        return reg;
    }

    private RegistroAcademicoDto fillGradoActual(RegistroAcademicoDto reg, Integer idCatalogo) {
        Catalogos c = (Catalogos) catalogosRepo
                .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, idCatalogo));
        if (c.getTipo().equals(C.CAT_GEN_NIV_EDUCATIVO_CARRERA)) {
            reg.setCarreraGradoActualNombre(c.getValor());
            reg.setCarreraGradoActual(c.getId());
        }
        if (c.getTipo().equals(C.CAT_GEN_NIV_EDUCATIVO_GRADO)) {
            reg.setGradoActual(c.getId());
            reg.setNombreGradoActual(c.getValor());
        }
        if (c.getTipo().equals(C.CAT_GEN_NIV_EDUCATIVO)) {
            reg.setNivelGradoActual(c.getId());
            reg.setNivelGradoActualNombre(c.getValor());
        }
        if (!isNull(c.getCodigoPadre())) {
            fillGradoActual(reg, c.getCodigoPadre());
        }
        return reg;
    }

    private void fillRegistroRA(RegistroAcademicoDto reg) {
        fillUltimoGrado(reg, reg.getUltimoGrado());
        if (reg.isEstudiaActualmente()) {
            fillGradoActual(reg, reg.getGradoActual());
        }
    }

}
