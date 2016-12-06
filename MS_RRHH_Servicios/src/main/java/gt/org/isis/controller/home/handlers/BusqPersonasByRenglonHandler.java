/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.C;
import gt.org.isis.api.requesting.AbstractValidationsRequestHandler;
import gt.org.isis.api.utils.EntitiesHelper;
import gt.org.isis.controller.dto.BusquedaAvanzadaDto;
import gt.org.isis.controller.dto.FiltroAvanzadoDto;
import gt.org.isis.model.Persona;
import gt.org.isis.model.PersonaChildEntity;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.Puesto_;
import gt.org.isis.model.Puestos;
import gt.org.isis.model.Puestos_;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.model.enums.CampoBusquedaAvanzada;
import gt.org.isis.model.enums.ComparadorBusqueda;
import gt.org.isis.repository.PuestoRepository;
import gt.org.isis.repository.PuestosRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 *
 * @author eliud
 */
@Component
public class BusqPersonasByRenglonHandler extends AbstractValidationsRequestHandler<BusquedaAvanzadaDto, List<Persona>> {

    @Autowired
    PuestoRepository puestoPersonaRepo;
    @Autowired
    PuestosRepository puestosRepo;

    @Override
    public List<Persona> execute(BusquedaAvanzadaDto request) {
        List<Persona> personas = new ArrayList();
        List<FiltroAvanzadoDto> filtros = (List<FiltroAvanzadoDto>) Collections2.filter(request.getFiltros(),
                new com.google.common.base.Predicate<FiltroAvanzadoDto>() {
            @Override
            public boolean apply(FiltroAvanzadoDto t) {
                return t.getCampo().equals(CampoBusquedaAvanzada.REGLON);
            }
        });
        for (final FiltroAvanzadoDto filtro : filtros) {
            if (filtro.getCampo().equals(CampoBusquedaAvanzada.REGLON)) {
                final List<Integer> catRenglonesIds = new ArrayList();
                catRenglonesIds.addAll(Collections2.transform(puestosRepo.findAll(new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {

                        if (filtro.getComparador().equals(ComparadorBusqueda.MAYOR)) {
                            return cb.greaterThanOrEqualTo(root.get(Puestos_.valorNum), filtro.getValor1());
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.MENOR)) {
                            return cb.lessThanOrEqualTo(root.get(Puestos_.valorNum), filtro.getValor1());
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.IGUAL)) {
                            return cb.equal(root.get(Puestos_.valorNum), filtro.getValor1());
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.DIFERENTE)) {
                            return cb.notEqual(root.get(Puestos_.valorNum), filtro.getValor1());
                        }
                        return cb.and(
                                cb.like(root.get(Puestos_.tipo), C.CAT_PUESTOS_RENGLON),
                                cb.greaterThanOrEqualTo(root.get(Puestos_.valorNum), filtro.getValor1()),
                                cb.lessThanOrEqualTo(root.get(Puestos_.valorNum), filtro.getValor2()));

                    }
                }), new Function<Puestos, Integer>() {
                    @Override
                    public Integer apply(Puestos f) {
                        return f.getId();
                    }
                }));
                final List<Integer> catPuestosNominalesIds = new ArrayList();

                catPuestosNominalesIds.addAll(Collections2.transform(puestosRepo.findAll(new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                        return root.get(Puestos_.codigoPadre).in(catRenglonesIds);
                    }
                }), new Function<Puestos, Integer>() {
                    @Override
                    public Integer apply(Puestos f) {
                        return f.getId();
                    }
                }));
                personas.addAll(EntitiesHelper
                        .getPersonas(new ArrayList<PersonaChildEntity>(Collections2.transform(puestoPersonaRepo.findAll(new Specification() {
                            @Override
                            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                                return root.get(Puesto_.fkPuestoNominal).in(catPuestosNominalesIds);
                            }
                        }), new Function<Puesto, RegistroLaboral>() {
                            @Override
                            public RegistroLaboral apply(Puesto f) {
                                return f.getFkRegistroLaboral();
                            }
                        }))));

            }
        }

        return personas;
    }
}
