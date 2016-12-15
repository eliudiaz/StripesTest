/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers.specifications;

import gt.org.ms.controller.dto.FiltroAvanzadoDto;
import gt.org.ms.model.RegistroLaboral;
import gt.org.isis.model.RegistroLaboral_;
import gt.org.ms.model.enums.ComparadorBusqueda;
import gt.org.ms.model.enums.EstadoVariable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 */
public class RegistroLaboralQSpec implements Specification<RegistroLaboral> {

    final private FiltroAvanzadoDto f;

    public RegistroLaboralQSpec(FiltroAvanzadoDto f) {
        this.f = f;
    }

    @Override
    public Predicate toPredicate(Root<RegistroLaboral> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> ls = new ArrayList<Predicate>();
        if (f.getComparador().equals(ComparadorBusqueda.ENTRE)) {
            ls.add(cb.and(
                    cb.greaterThanOrEqualTo(root.get(RegistroLaboral_.anioIngreso),
                            f.getValor1()),
                    cb.lessThanOrEqualTo(root.get(RegistroLaboral_.anioIngreso),
                            f.getValor2())));
        }
        if (f.getComparador().equals(ComparadorBusqueda.MAYOR)) {
            ls.add(
                    cb.greaterThanOrEqualTo(root.get(RegistroLaboral_.anioIngreso),
                            f.getValor1()));
        }
        if (f.getComparador().equals(ComparadorBusqueda.MENOR)) {
            ls.add(
                    cb.lessThanOrEqualTo(root.get(RegistroLaboral_.anioIngreso),
                            f.getValor2()));
        }
        if (f.getComparador().equals(ComparadorBusqueda.DIFERENTE)) {
            ls.add(cb.notEqual(root.get(RegistroLaboral_.anioIngreso),
                    f.getValor1()));
        }
        if (f.getComparador().equals(ComparadorBusqueda.IGUAL)) {
            ls.add(cb.equal(root.get(RegistroLaboral_.anioIngreso),
                    f.getValor1()));
        }

        return cb.and(cb.equal(root.get(RegistroLaboral_.estado), EstadoVariable.ACTUAL),
                cb.or(ls.toArray(new Predicate[ls.size()])));
    }

}
