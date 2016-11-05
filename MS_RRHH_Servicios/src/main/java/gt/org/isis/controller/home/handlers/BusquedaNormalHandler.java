/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractValidationsRequestHandler;
import gt.org.isis.controller.dto.BusquedaNormalDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.model.LugarResidencia;
import gt.org.isis.model.Persona;
import gt.org.isis.repository.LugarResidenciaRepository;
import gt.org.isis.repository.PersonasRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author edcracken
 */
public class BusquedaNormalHandler extends AbstractValidationsRequestHandler<BusquedaNormalDto, List<PersonaDto>> {

    @Autowired
    PersonasRepository repo;

    @Autowired
    LugarResidenciaRepository lugarRepo;

    @Override
    public List<PersonaDto> execute(final BusquedaNormalDto request) {

        List<Persona> s1 = repo.findAll(new Specification<Persona>() {
            @Override
            public Predicate toPredicate(Root<Persona> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return CriteriaBuilderPersona
                        .get()
                        .withRoot(root)
                        .withCB(cb)
                        .withCq(cq)
                        .withDto(request)
                        .build();
            }
        }, request.getPageable())
                .getContent();
        if (request.getDireccion() != null || request.getMunicipio() != null) {

            (s1 = (s1 == null ? new ArrayList<Persona>() : s1))
                    .addAll(Collections2.transform(lugarRepo.findAll(new Specification<LugarResidencia>() {
                        @Override
                        public Predicate toPredicate(Root<LugarResidencia> root, CriteriaQuery<?> cq,
                                CriteriaBuilder cb) {
                            return CriteriaBuilderLugarResidencia
                                    .get()
                                    .withDto(request)
                                    .withCB(cb)
                                    .withCq(cq)
                                    .withRoot(root)
                                    .build();
                        }
                    }, request.getPageable())
                            .getContent(), new Function<LugarResidencia, Persona>() {
                        @Override
                        public Persona apply(LugarResidencia f) {
                            return f.getFkPersona();
                        }
                    }));
        }
        return new PersonaDtoConverter().toDTO(s1);
    }

}
