/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import gt.org.ms.api.C;
import gt.org.ms.api.jpa.SingleFieldSpecification;
import gt.org.ms.api.requesting.AbstractValidationsRequestHandler;
import gt.org.ms.controller.dto.BusquedaAvanzadaDto;
import gt.org.ms.controller.dto.FiltroAvanzadoDto;
import gt.org.ms.model.Persona;
import gt.org.ms.model.Puesto;
import gt.org.isis.model.Puesto_;
import gt.org.ms.model.UnidadEjecutora;
import gt.org.ms.model.UnidadNotificadora;
import gt.org.isis.model.UnidadNotificadora_;
import gt.org.ms.model.enums.CampoBusquedaAvanzada;
import gt.org.ms.model.enums.ComparadorBusqueda;
import gt.org.ms.repository.PuestoRepository;
import gt.org.ms.repository.UnidadEjecutoraRepository;
import gt.org.ms.repository.UnidadNotificadoraRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
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
                final UnidadEjecutora ue = unidadEjecutoraRepo.findOne(fa.getValor1());
                final List<UnidadNotificadora> unidades = new ArrayList<UnidadNotificadora>();
                unidades.addAll(getHijos(ue.getId(), C.CAT_UN_COMUNIDAD2));
                unidades.addAll(getHijos(ue.getId(), C.CAT_UN_COMUNIDAD));

                r.addAll(Collections2
                        .transform(puestosPersonasRepo.findAll(new Specification<Puesto>() {
                            @Override
                            public javax.persistence.criteria.Predicate toPredicate(Root<Puesto> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                                List<Integer> ids = new LinkedList<Integer>();
                                ids.addAll(Collections2.transform(unidades, new Function<UnidadNotificadora, Integer>() {
                                    @Override
                                    public Integer apply(UnidadNotificadora f) {
                                        return f.getId();
                                    }
                                }));
                                if (fa.getComparador().equals(ComparadorBusqueda.DIFERENTE)) {
                                    return cb.not(root.get(Puesto_.fkComunidad).in(ids));
                                }
                                return root.get(Puesto_.fkComunidad).in(ids);
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
        System.out.println(">> padre >> " + parent);
        if (!unidades.isEmpty() && !unidades.iterator().next().getTipo().equalsIgnoreCase(tipoDestino)) {
            List<UnidadNotificadora> childs = new ArrayList<UnidadNotificadora>();
            for (UnidadNotificadora un : unidades) {
                childs.addAll(getHijos(un.getId(), tipoDestino));
                System.out.println(">> hijos >> " + childs.size());
            }
            return childs;
        } else {
            return unidades;
        }

    }

}
