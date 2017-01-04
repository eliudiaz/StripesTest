/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.jpa.ManySpecificationANDHandler;
import gt.org.ms.api.requesting.AbstractValidationsRequestHandler;
import gt.org.ms.api.utils.EntitiesHelper;
import gt.org.ms.controller.dto.BusquedaAvanzadaDto;
import gt.org.ms.controller.dto.FiltroAvanzadoDto;
import gt.org.ms.controller.home.handlers.specifications.PuestoPorFiltroAvanzadoQSpec;
import gt.org.ms.controller.home.handlers.specifications.RegistroLaboralQSpec;
import gt.org.ms.model.Persona;
import gt.org.ms.model.PersonaChildEntity;
import gt.org.ms.model.Puesto;
import gt.org.ms.model.Puesto_;
import gt.org.ms.model.RegistroLaboral;
import gt.org.ms.model.enums.CampoBusquedaAvanzada;
import gt.org.ms.repository.PuestoRepository;
import gt.org.ms.repository.RegistroLaboralRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 *
 * @author edcracken
 */
@Component
public class BusqPersonasByPuestoParamsHandler extends AbstractValidationsRequestHandler<BusquedaAvanzadaDto, List<Persona>> {

    @Autowired
    RegistroLaboralRepository rLaboralRepo;
    @Autowired
    PuestoRepository puestoRepo;

    @Override
    public List<Persona> execute(BusquedaAvanzadaDto request) {
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
            if (filtro.getCampo().equals(CampoBusquedaAvanzada.ANIO_INGRESO)) {
                personas.addAll(EntitiesHelper
                        .getPersonas(rLaboralRepo
                                .findAll(new RegistroLaboralQSpec(filtro))));
            }
        }
        if (!puestoSpecs.isEmpty()) {
            personas.addAll(EntitiesHelper
                    .getPersonas(new ArrayList<PersonaChildEntity>(Collections2.transform(puestoRepo
                            .findAll(new ManySpecificationANDHandler<Puesto>(puestoSpecs)),
                            new Function<Puesto, RegistroLaboral>() {
                        @Override
                        public RegistroLaboral apply(Puesto f) {
                            return f.getFkRegistroLaboral();
                        }
                    }))));
        }
        return personas;
    }

}
