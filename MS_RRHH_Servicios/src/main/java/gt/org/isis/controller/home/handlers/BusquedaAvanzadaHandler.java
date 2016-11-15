/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractValidationsRequestHandler;
import gt.org.isis.api.jpa.ManySpecificationHandler;
import gt.org.isis.controller.dto.BusquedaAvanzadaDto;
import gt.org.isis.controller.dto.FiltroAvanzadoDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.model.Persona;
import gt.org.isis.model.PersonaChildEntity;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.Puesto_;
import gt.org.isis.model.Puestos;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.model.enums.CampoBusquedaAvanzada;
import gt.org.isis.model.enums.TipoPuestosCatalogo;
import gt.org.isis.model.utils.EntitiesHelper;
import gt.org.isis.repository.LugarResidenciaRepository;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.PuestoRepository;
import gt.org.isis.repository.PuestosRepository;
import gt.org.isis.repository.RegistroLaboralRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class BusquedaAvanzadaHandler
        extends AbstractValidationsRequestHandler<BusquedaAvanzadaDto, List<PersonaDto>> {

    @Autowired
    PersonasRepository personasRepo;

    @Autowired
    RegistroLaboralRepository rLaboralRepo;

    @Autowired
    PuestoRepository puestoRepo;

    @Autowired
    PuestosRepository puestosRepo;

    @Autowired
    LugarResidenciaRepository lugarRepo;

    @Override
    public List<PersonaDto> execute(final BusquedaAvanzadaDto request) {
        List<Persona> personas = new ArrayList();
        List<Specification<Puesto>> puestoSpecs = new ArrayList();
        for (final FiltroAvanzadoDto filtro : request.getFiltros()) {

            if (filtro.getCampo().equals(CampoBusquedaAvanzada.CLASIFICACION_SERVICIO)) {
                puestoSpecs.add(new PuestoPorFiltroAvanzadoQSpec(filtro,
                        Puesto_.fkClasificacionServicio));
            }
            if (filtro.getCampo().equals(CampoBusquedaAvanzada.PUESTO_FUNCIONAL)) {
                puestoSpecs.add(new PuestoPorFiltroAvanzadoQSpec(filtro,
                        Puesto_.fkPuestoFuncional));

            }
            if (filtro.getCampo().equals(CampoBusquedaAvanzada.PUESTO_NOMINAL)) {
                puestoSpecs.add(new PuestoPorFiltroAvanzadoQSpec(filtro,
                        Puesto_.fkPuestoNominal));
            }
            personas.addAll(EntitiesHelper
                    .getPersonas(new ArrayList<PersonaChildEntity>(Collections2.transform(
                            puestosRepo
                                    .findAll(new ManySpecificationHandler<Puesto>(puestoSpecs)),
                            new Function<Puesto, RegistroLaboral>() {
                        @Override
                        public RegistroLaboral apply(Puesto f) {
                            return f.getFkRegistroLaboral();
                        }
                    }))));

            if (filtro.getCampo().equals(CampoBusquedaAvanzada.ANIO_INGRESO)) {
                personas.addAll(EntitiesHelper
                        .getPersonas(rLaboralRepo
                                .findAll(new RegistroLaboralQSpec(filtro))));
            }
//            if (filtro.getCampo().equals(CampoBusquedaAvanzada.REGLON)) {
//                personas.addAll(EntitiesHelper.getPersonas(puestosRepo
//                        .findAll(new PuestoQSpec(
//                                (List<Integer>) Collections2
//                                        .transform(puestosRepo.findAll(
//                                                new PuestosPorPadreQSpec(Integer.valueOf(filtro.getValor1()),
//                                                        TipoPuestosCatalogo.PUESTO_NOMINAL)),
//                                                new Function<Puestos, Integer>() {
//                                            @Override
//                                            public Integer apply(Puestos f) {
//                                                return f.getId();
//                                            }
//                                        }), filtro))));
//            }

        }
        return new PersonaDtoConverter().toDTO(personas);
    }

}
