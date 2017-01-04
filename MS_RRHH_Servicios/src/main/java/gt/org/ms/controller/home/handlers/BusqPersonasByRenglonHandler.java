/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.C;
import gt.org.ms.api.requesting.AbstractValidationsRequestHandler;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.utils.EntitiesHelper;
import gt.org.ms.controller.dto.BusquedaAvanzadaDto;
import gt.org.ms.controller.dto.FiltroAvanzadoDto;
import gt.org.ms.model.Persona;
import gt.org.ms.model.PersonaChildEntity;
import gt.org.ms.model.Puesto;
import gt.org.ms.model.Puestos;
import gt.org.ms.model.Puesto_;
import gt.org.ms.model.Puestos_;
import gt.org.ms.model.RegistroLaboral;
import gt.org.ms.model.enums.CampoBusquedaAvanzada;
import gt.org.ms.model.enums.ComparadorBusqueda;
import gt.org.ms.repository.PuestoRepository;
import gt.org.ms.repository.PuestosRepository;
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
        List<FiltroAvanzadoDto> filtros = new ArrayList<FiltroAvanzadoDto>(Collections2.filter(request.getFiltros(),
                new com.google.common.base.Predicate<FiltroAvanzadoDto>() {
            @Override
            public boolean apply(FiltroAvanzadoDto t) {
                return t.getCampo().equals(CampoBusquedaAvanzada.RENGLON);
            }
        }));
        for (final FiltroAvanzadoDto filtro : filtros) {
            if (filtro.getCampo().equals(CampoBusquedaAvanzada.RENGLON)) {
                final List<Integer> catRenglonesIds = new ArrayList();
                final Puestos inicio = puestosRepo.findOne(filtro.getValor1());
                catRenglonesIds.addAll(Collections2.transform(puestosRepo.findAll(new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {

                        if (filtro.getComparador().equals(ComparadorBusqueda.MAYOR)) {
                            return cb.greaterThanOrEqualTo(root.get(Puestos_.valorNum), inicio.getValorNum());
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.MENOR)) {
                            return cb.lessThanOrEqualTo(root.get(Puestos_.valorNum), inicio.getValorNum());
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.IGUAL)) {
                            return cb.equal(root.get(Puestos_.valorNum), inicio.getValorNum());
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.DIFERENTE)) {
                            return cb.notEqual(root.get(Puestos_.valorNum), inicio.getValorNum());
                        }
                        final Puestos fin = isNull(filtro.getValor2()) ? null : puestosRepo.findOne(filtro.getValor2());
                        return cb.and(
                                cb.like(root.get(Puestos_.tipo), C.CAT_PUESTOS_RENGLON),
                                cb.greaterThanOrEqualTo(root.get(Puestos_.valorNum), inicio.getValorNum()),
                                cb.lessThanOrEqualTo(root.get(Puestos_.valorNum), fin.getValorNum()));

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
