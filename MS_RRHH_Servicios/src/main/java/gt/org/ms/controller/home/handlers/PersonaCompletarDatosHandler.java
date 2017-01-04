/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractRequestHandler;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.controller.dto.EstudioSaludDto;
import gt.org.ms.controller.dto.IdiomaDto;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.dto.PersonaRowsFileDto;
import gt.org.ms.controller.dto.RefAreaGeograficaDto;
import gt.org.ms.controller.dto.RegistroAcademicoDto;
import gt.org.ms.controller.dto.RegistroLaboralDto;
import gt.org.ms.controller.dto.RegistroLaboralPuestoDto;
import gt.org.ms.controller.dto.RequestGetPersonaDto;
import gt.org.ms.controller.personas.handlers.PersonaBusquedaSimpleHandler;
import gt.org.ms.model.enums.TipoPuesto;
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
    PersonaBusquedaSimpleHandler findPersonaHandler;

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
        RequestGetPersonaDto persona = (RequestGetPersonaDto) findPersonaHandler.handle(request);

        RegistroLaboralDto rl = persona.getRegistroLaboral();
        RegistroAcademicoDto ra = persona.getRegistroAcademico();
        RegistroLaboralPuestoDto puestoPrincipal = getPuesto(TipoPuesto.PRINCIPAL, rl);
        RefAreaGeograficaDto refResidencia = persona.getLugarResidencia().getRefLugarResidencia();

        PersonaRowsFileDto.Builder builder = PersonaRowsFileDto.builder()
                .withPrimerNombre(persona.getPrimerNombre())
                .withSegundoNombre(persona.getSegundoNombre())
                .withPrimerApellido(persona.getPrimerApellido())
                .withSegundoApellido(persona.getSegundoApellido())
                .withApellidoCasada(persona.getApellidoCasada())
                .withCui(persona.getCui())
                .withSexo(persona.getSexo())
                .withEstadoCivil(persona.getEstadoCivil())
                .withFkNacionalidadNombre(persona.getFkNacionalidadNombre())
                .withLimitacionesFisicas(persona.getLimitacionesFisicas())
                .withSabeLeer(translateBool(persona.isSabeLeer()))
                .withSabeEscribir(translateBool(persona.isSabeLeer()))
                .withFechaNacimientoTexto(persona.getFechaNacimientoTexto())
                .withEdad(persona.getEdad())
                .withIdiomas(!isNull(persona.getIdiomas())
                        ? Arrays.toString(persona.getIdiomas().toArray(new IdiomaDto[persona.getIdiomas().size()]))
                        : "");

        if (!isNull(refResidencia)) {
            builder.withPaisResidencia(refResidencia.getFkPaisNombre())
                    .withDepartamentoResidencia(refResidencia.getFkDepartamentoNombre())
                    .withMunicipioResidencia(refResidencia.getFkMunicioNombre())
                    .withDireccion(persona.getLugarResidencia().getDireccion())
                    .withPueblo(persona.getFkPueblo().toString())
                    .withComunidadLinguistica(persona.getFkComunidadLinguisticaNombre());
        }

        if (!isNull(ra)) {
            builder.withUltimoGrado(ra.getNombreUltimoGrado())
                    .withNivelUltimoGrado(ra.getNivelUltimoGradoNombre())
                    .withEstudiaActualmente(translateBool(ra.isEstudiaActualmente()))
                    .withGradoActual(ra.getCarreraGradoActualNombre())
                    .withNivelGradoActual(ra.getNivelGradoActualNombre())
                    .withOtrosEstudios(!isNull(persona.getEstudiosSalud())
                            ? Arrays.toString(persona.getEstudiosSalud().toArray(new EstudioSaludDto[persona.getEstudiosSalud().size()]))
                            : "");
        }
        if (!isNull(rl) && !isNull(puestoPrincipal)) {
            builder.withAnioIngreso(rl.getAnioIngreso().toString())
                    .withPuestoFunciones(puestoPrincipal.getNombrePuestoFuncional())
                    .withRenglon(puestoPrincipal.getNombrePuestoNominalRenglon())
                    .withUnidadEjecutora(puestoPrincipal.getRefUnidadNotificadora().getFkUnidadEjecutoraNombre())
                    .withClasificacionServicio(puestoPrincipal.getRefClasificacionServicio().getFkClasificacionServicioNombre())
                    .withComisionado(translateBool(rl.isComisionado()))
                    .withPuestoAdicional(translateBool(rl.getPuestos().size() > 1))
                    .withUnidadEjecutora(puestoPrincipal
                            .getRefUnidadNotificadora()
                            .getFkUnidadEjecutoraNombre().concat("-")
                            .concat(puestoPrincipal
                                    .getRefUnidadNotificadora()
                                    .getNombreDistrito().concat("-"))
                            .concat(!isNull(puestoPrincipal
                                    .getRefUnidadNotificadora()
                                    .getNombreLugarEspecifico())
                                            ? puestoPrincipal
                                                    .getRefUnidadNotificadora()
                                                    .getNombreLugarEspecifico() : "")
                            .concat(!isNull(puestoPrincipal
                                    .getRefUnidadNotificadora()
                                    .getNombreComunidad())
                                            ? puestoPrincipal
                                                    .getRefUnidadNotificadora()
                                                    .getNombreComunidad() : "")
                            .concat(!isNull(puestoPrincipal
                                    .getRefUnidadNotificadora()
                                    .getNombreComunidad2()) ? puestoPrincipal
                                                    .getRefUnidadNotificadora()
                                                    .getNombreComunidad2() : "")
                    );
        }
        return builder.build();
    }

}
