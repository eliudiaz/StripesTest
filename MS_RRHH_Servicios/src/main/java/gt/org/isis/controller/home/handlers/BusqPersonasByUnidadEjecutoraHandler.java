/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import gt.org.isis.api.C;
import gt.org.isis.api.jpa.SingleFieldSpecification;
import gt.org.isis.api.requesting.AbstractValidationsRequestHandler;
import gt.org.isis.controller.dto.BusquedaAvanzadaDto;
import gt.org.isis.controller.dto.FiltroAvanzadoDto;
import gt.org.isis.controller.home.handlers.specifications.UnidadEjectoraQSpec;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.Puesto_;
import gt.org.isis.model.UnidadEjecutora;
import gt.org.isis.model.UnidadNotificadora;
import gt.org.isis.model.UnidadNotificadora_;
import gt.org.isis.model.enums.CampoBusquedaAvanzada;
import gt.org.isis.repository.PuestoRepository;
import gt.org.isis.repository.UnidadEjecutoraRepository;
import gt.org.isis.repository.UnidadNotificadoraRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 *
 * @author eliud
 */
@Component
public class BusqPersonasByUnidadEjecutoraHandler
        extends AbstractValidationsRequestHandler<BusquedaAvanzadaDto, List<Persona>> {

    @Autowired
    UnidadNotificadoraRepository unidadNotificadoraRepo;
    @Autowired
    UnidadEjecutoraRepository unidadEjecutoraRepo;
    @Autowired
    PuestoRepository puestosPersonasRepo;

    @Override
    public List<Persona> execute(BusquedaAvanzadaDto request) {
        Collection<FiltroAvanzadoDto> filtros = Collections2.filter(request.getFiltros(),
                new Predicate<FiltroAvanzadoDto>() {
            @Override
            public boolean apply(FiltroAvanzadoDto t) {
                return t.getCampo().equals(CampoBusquedaAvanzada.UNIDAD_EJECUTORA);
            }
        });
        List<Persona> r = new ArrayList<Persona>();
        if (!filtros.isEmpty()) {
            for (final FiltroAvanzadoDto fa : filtros) {
                r.addAll(Collections2
                        .transform(puestosPersonasRepo.findAll(new Specification<Puesto>() {
                            @Override
                            public javax.persistence.criteria.Predicate toPredicate(Root<Puesto> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                                return root.get(Puesto_.fkComunidad).in(Collections2
                                        .transform(Collections2
                                                .transform(unidadEjecutoraRepo.findAll(new UnidadEjectoraQSpec(fa.getValor1(), fa)),
                                                        new Function<UnidadEjecutora, List<UnidadNotificadora>>() {
                                                    @Override
                                                    public List<UnidadNotificadora> apply(UnidadEjecutora f) {
                                                        List<UnidadNotificadora> r = new ArrayList<UnidadNotificadora>();
                                                        r.addAll(getHijos(f.getId(), C.CAT_UN_COMUNIDAD2));
                                                        r.addAll(getHijos(f.getId(), C.CAT_UN_COMUNIDAD));
                                                        return r;
                                                    }
                                                }), new Function<UnidadNotificadora, Integer>() {
                                            @Override
                                            public Integer apply(UnidadNotificadora f) {
                                                return f.getId();
                                            }
                                        }));
                            }
                        }), new Function<Puesto, Persona>() {
                            @Override
                            public Persona apply(Puesto f) {
                                return f.getFkRegistroLaboral().getFkPersona();
                            }
                        }));

            }
        }
        return r;
    }

    public List<UnidadNotificadora> getHijos(Integer parent, String tipoDestino) {
        List<UnidadNotificadora> unidades = unidadNotificadoraRepo
                .findAll(new SingleFieldSpecification<UnidadNotificadora, Integer>(UnidadNotificadora_.codigoPadre, parent));

        if (!unidades.isEmpty() && !unidades.iterator().next().getTipo().equalsIgnoreCase(tipoDestino)) {
            List<UnidadNotificadora> childs = new ArrayList<UnidadNotificadora>();
            for (UnidadNotificadora un : unidades) {
                childs.addAll(getHijos(un.getId(), tipoDestino));
            }
            return childs;
        } else {
            return unidades;
        }

    }

}
