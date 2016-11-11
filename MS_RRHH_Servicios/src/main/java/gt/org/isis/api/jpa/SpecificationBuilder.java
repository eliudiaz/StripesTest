/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.api.jpa;

import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 * @param <T>
 * @param <Q>
 */
public interface SpecificationBuilder<T, Q> {

    public Specification<Q> build(T param);
}
