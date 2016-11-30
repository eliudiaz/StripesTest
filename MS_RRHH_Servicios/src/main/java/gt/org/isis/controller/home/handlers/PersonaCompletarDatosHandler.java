/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractRequestHandler;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.controller.dto.EstudioSaludDto;
import gt.org.isis.controller.dto.IdiomaDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.PersonaRowsFileDto;
import gt.org.isis.controller.dto.RefAreaGeograficaDto;
import gt.org.isis.controller.dto.RegistroAcademicoDto;
import gt.org.isis.controller.dto.RegistroLaboralDto;
import gt.org.isis.controller.dto.RegistroLaboralPuestoDto;
import gt.org.isis.controller.dto.RequestGetPersonaDto;
import gt.org.isis.controller.personas.handlers.PersonaBusquedaSimpleHandler;
import gt.org.isis.converters.PersonaRowsFileDtoConverter;
import gt.org.isis.model.enums.TipoPuesto;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ecracken
 */
@Service
public class PersonaCompletarDatosHandler extends AbstractRequestHandler<PersonaDto, PersonaRowsFileDto> {

    @Autowired
    PersonaBusquedaSimpleHandler handler;

    private RegistroLaboralPuestoDto getPuesto(TipoPuesto tipo, RegistroLaboralDto rl) {
        final TipoPuesto tipoF = tipo;
        Collection<RegistroLaboralPuestoDto> puestos;
        return isNull(puestos = Collections2.filter(rl.getPuestos(),
                new Predicate<RegistroLaboralPuestoDto>() {
            @Override
            public boolean apply(RegistroLaboralPuestoDto t) {
                return t.getTipo().equals(tipoF);
            }
        })) ? null : puestos.iterator().next();
    }

    private String translateBool(boolean v) {
        return v ? "SI" : "NO";
    }

    @Override
    public PersonaRowsFileDto execute(PersonaDto request) {
        RequestGetPersonaDto persona = (RequestGetPersonaDto) handler.handle(request);
        if (!isNull(persona.getRegistroLaboral())) {

            RegistroLaboralDto rl = persona.getRegistroLaboral();
            RegistroAcademicoDto ra = persona.getRegistroAcademico();
            RegistroLaboralPuestoDto puestoPrincipal = getPuesto(TipoPuesto.PRINCIPAL, rl);
            RefAreaGeograficaDto refResidencia;
            PersonaRowsFileDto.builder()
                    .withPrimerNombre(persona.getPrimerNombre())
                    .withSegundoNombre(persona.getSegundoNombre())
                    .withPrimerApellido(persona.getPrimerApellido())
                    .withSegundoApellido(persona.getSegundoApellido())
                    .withApellidoCasada(persona.getApellidoCasada())
                    .withCui(persona.getCui())
                    .withSexo(persona.getSexo())
                    .withEstadoCivil(persona.getEstadoCivil())
                    .withFkNacionalidadNombre(persona.getFkNacionalidadNombre()) //check
                    .withLimitacionesFisicas(persona.getLimitacionesFisicas())
                    .withSabeLeer(translateBool(persona.isSabeLeer()))
                    .withSabeEscribir(translateBool(persona.isSabeLeer()))
                    .withFechaNacimientoTexto(persona.getFechaNacimientoTexto()) //check
                    .withEdad(persona.getEdad())
                    .withPaisResidencia((refResidencia = persona.getLugarResidencia().getRefLugarResidencia()).getFkPaisNombre())
                    .withDepartamentoResidencia(refResidencia.getFkDepartamentoNombre())
                    .withMunicipioResidencia(refResidencia.getFkMunicioNombre())
                    .withDireccion(persona.getLugarResidencia().getDireccion())
                    .withPueblo(persona.getFkPueblo().toString())
                    .withComunidadLinguistica(persona.getFkComunidadLinguisticaNombre()) //check
                    .withIdiomas(!isNull(persona.getIdiomas())
                            ? Arrays.toString(persona.getIdiomas().toArray(new IdiomaDto[persona.getIdiomas().size()]))
                            : "")
                    .withUltimoGrado(persona.getRegistroAcademico().getNombreUltimoGrado())
                    .withNivelUltimoGrado(persona.getRegistroAcademico().getNivelUltimoGradoNombre())
                    .withEstudiaActualmente(translateBool(persona.getRegistroAcademico().isEstudiaActualmente()))
                    .withGradoActual(ra.getCarreraGradoActualNombre())
                    .withNivelGradoActual(ra.getNivelGradoActualNombre())
                    .withOtrosEstudios(!isNull(persona.getEstudiosSalud())
                            ? Arrays.toString(persona.getEstudiosSalud().toArray(new EstudioSaludDto[persona.getEstudiosSalud().size()]))
                            : "")
                    .withAnioIngreso(rl.getAnioIngreso().toString())
                    .withPuestoFunciones(puestoPrincipal.getNombrePuestoFuncional())
                    .withRenglon(puestoPrincipal.getNombrePuestoNominalRenglon())
                    .withUnidadEjecutora(puestoPrincipal.getRefUnidadNotificadora().getFkUnidadEjecutoraNombre())
                    .withClasificacionServicio(puestoPrincipal.getRefClasificacionServicio().getFkClasificacionServicioNombre())
                    .withComisionado(translateBool(rl.isComisionado()))
                    .withPuestoAdicional(translateBool(rl.getPuestos().size() > 1))
                    .build();

        }
        return new PersonaRowsFileDtoConverter().toEntity(handler.handle(request));
    }

}
