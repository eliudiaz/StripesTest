/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.home.handlers;

import gt.org.ms.controller.home.handlers.specifications.CriteriaBuilderLugarResidencia;
import gt.org.ms.controller.home.handlers.specifications.CriteriaBuilderPersona;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.ms.api.requesting.AbstractValidationsRequestHandler;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.controller.dto.BusquedaNormalDto;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.converters.PersonaDtoConverter;
import gt.org.ms.model.AreaGeografica;
import gt.org.ms.model.AreaGeografica_;
import gt.org.ms.model.LugarResidencia;
import gt.org.ms.model.Persona;
import gt.org.ms.model.enums.Estado;
import gt.org.ms.repository.AreasGeografRepository;
import gt.org.ms.repository.CatalogosRepository;
import gt.org.ms.repository.LugarResidenciaRepository;
import gt.org.ms.repository.PersonasRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class BusquedaNormalHandler extends AbstractValidationsRequestHandler<BusquedaNormalDto, List<PersonaDto>> {

    @Autowired
    PersonasRepository repo;

    @Autowired
    LugarResidenciaRepository lugarRepo;

    @Autowired
    CatalogosRepository catsRepo;

    @Autowired
    AreasGeografRepository areaGeograficaRepo;

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
        });
        if (!isNull(request.getDireccion())
                || !isNull(request.getDepartamento())
                || !isNull(request.getMunicipio())) {
            final Collection<Integer> munisLs = new ArrayList();
            if (!isNull(request.getDepartamento()) && isNull(request.getMunicipio())) {
                munisLs.addAll(Collections2.transform(areaGeograficaRepo.findAll(new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                        return cb.equal(root.get(AreaGeografica_.codigoPadre),
                                request.getDepartamento());
                    }
                }), new Function<AreaGeografica, Integer>() {
                    @Override
                    public Integer apply(AreaGeografica f) {
                        return f.getId();
                    }
                }));
            }
            (s1 = (s1 == null ? new ArrayList<Persona>() : s1))
                    .addAll(Collections2.filter(Collections2.transform(lugarRepo.findAll(new Specification<LugarResidencia>() {
                        @Override
                        public Predicate toPredicate(Root<LugarResidencia> root, CriteriaQuery<?> cq,
                                CriteriaBuilder cb) {
                            return CriteriaBuilderLugarResidencia
                                    .get()
                                    .withDto(request)
                                    .withCB(cb)
                                    .withCq(cq)
                                    .withMunisList(munisLs)
                                    .withRoot(root)
                                    .build();
                        }
                    }), new Function<LugarResidencia, Persona>() {
                        @Override
                        public Persona apply(LugarResidencia f) {
                            return f.getFkPersona();
                        }
                    }), new com.google.common.base.Predicate<Persona>() {
                        @Override
                        public boolean apply(Persona t) {
                            return t.getEstado().equals(Estado.ACTIVO);
                        }
                    }));
        }
        return new PersonaDtoConverter().toDTO(s1);
    }

}
