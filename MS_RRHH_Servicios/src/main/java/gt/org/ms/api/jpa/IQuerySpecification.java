/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.api.jpa;

import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 */
public interface IQuerySpecification<T> extends Specification<T> {

    
}
