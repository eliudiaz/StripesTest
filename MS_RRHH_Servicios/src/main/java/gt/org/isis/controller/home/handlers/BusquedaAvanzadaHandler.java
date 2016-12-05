/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.home.handlers;

import gt.org.isis.controller.home.handlers.specifications.PuestoPorFiltroAvanzadoQSpec;
import gt.org.isis.controller.home.handlers.specifications.RegistroLaboralQSpec;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.requesting.AbstractValidationsRequestHandler;
import static gt.org.isis.api.requesting.ValidationsHelper.isNull;
import gt.org.isis.api.jpa.ManySpecificationANDHandler;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.BusquedaAvanzadaDto;
import gt.org.isis.controller.dto.FiltroAvanzadoDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.model.Persona;
import gt.org.isis.model.PersonaChildEntity;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.Puesto_;
import gt.org.isis.model.Puestos;
import gt.org.isis.model.Puestos_;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.model.enums.CampoBusquedaAvanzada;
import gt.org.isis.model.enums.ComparadorBusqueda;
import gt.org.isis.api.utils.EntitiesHelper;
import gt.org.isis.repository.LugarResidenciaRepository;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.PuestoRepository;
import gt.org.isis.repository.PuestosRepository;
import gt.org.isis.repository.RegistroLaboralRepository;
import java.util.ArrayList;
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
public class BusquedaAvanzadaHandler
        extends AbstractValidationsRequestHandler<BusquedaAvanzadaDto, List<PersonaDto>> {

    @Autowired
    PersonasRepository personasRepo;

    @Autowired
    RegistroLaboralRepository rLaboralRepo;

    @Autowired
    PuestoRepository puestoRepo;

    @Autowired
    PuestosRepository puestosRepo;

    @Autowired
    LugarResidenciaRepository lugarRepo;

    @Override
    public List<PersonaDto> execute(final BusquedaAvanzadaDto request) {
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
            personas.addAll(EntitiesHelper
                    .getPersonas(new ArrayList<PersonaChildEntity>(Collections2.transform(puestosRepo
                                    .findAll(new ManySpecificationANDHandler<Puesto>(puestoSpecs)),
                            new Function<Puesto, RegistroLaboral>() {
                        @Override
                        public RegistroLaboral apply(Puesto f) {
                            return f.getFkRegistroLaboral();
                        }
                    }))));

            if (filtro.getCampo().equals(CampoBusquedaAvanzada.REGLON)) {
                Integer valor1 = EntitiesHelper.isNumeric(filtro.getValor1()) ? Integer.valueOf(filtro.getValor1()) : null;
                Integer valor2 = EntitiesHelper.isNumeric(filtro.getValor2()) ? Integer.valueOf(filtro.getValor2()) : null;
                if (filtro.getComparador().equals(ComparadorBusqueda.ENTRE) && (isNull(valor1) || isNull(valor2))) {
                    throw ExceptionsManager.newValidationException("rango_invalido",
                            new String[]{"rango_invalido,El rango de numeros esta incompleto!"});
                }
                if (filtro.getComparador().equals(ComparadorBusqueda.ENTRE) && valor1 > valor2) {
                    throw ExceptionsManager.newValidationException("rango_invalido",
                            new String[]{"rango_invalido,El valor inicial debe ser menor al valor final!"});
                }

                final List<Integer> vals = new ArrayList();
                vals.addAll(Collections2.transform(puestosRepo.findAll(new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {

                        if (filtro.getComparador().equals(ComparadorBusqueda.MAYOR)) {
                            return cb.greaterThanOrEqualTo(root.get(Puestos_.valorNum), Integer.valueOf(filtro.getValor1()));
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.MENOR)) {
                            return cb.lessThanOrEqualTo(root.get(Puestos_.valorNum), Integer.valueOf(filtro.getValor1()));
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.IGUAL)) {
                            return cb.equal(root.get(Puestos_.valorNum), Integer.valueOf(filtro.getValor1()));
                        }
                        if (filtro.getComparador().equals(ComparadorBusqueda.DIFERENTE)) {
                            return cb.notEqual(root.get(Puestos_.valorNum), Integer.valueOf(filtro.getValor1()));
                        }
                        return cb.and(
                                cb.greaterThanOrEqualTo(root.get(Puestos_.valorNum), Integer.valueOf(filtro.getValor1())),
                                cb.lessThanOrEqualTo(root.get(Puestos_.valorNum), Integer.valueOf(filtro.getValor2())));

                    }
                }), new Function<Puestos, Integer>() {
                    @Override
                    public Integer apply(Puestos f) {
                        return f.getId();
                    }
                }));
                vals.clear();
                vals.addAll(Collections2.transform(puestosRepo.findAll(new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                        return root.get(Puestos_.codigoPadre).in(vals);
                    }
                }), new Function<Puestos, Integer>() {
                    @Override
                    public Integer apply(Puestos f) {
                        return f.getId();
                    }
                }));
                personas.addAll(EntitiesHelper
                        .getPersonas(new ArrayList<PersonaChildEntity>(Collections2.transform(puestoRepo.findAll(new Specification() {
                            @Override
                            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
                                return root.get(Puesto_.fkPuestoNominal).in(vals);
                            }
                        }), new Function<Puesto, RegistroLaboral>() {
                            @Override
                            public RegistroLaboral apply(Puesto f) {
                                return f.getFkRegistroLaboral();
                            }
                        }))));

            }
            if (filtro.getCampo().equals(CampoBusquedaAvanzada.ANIO_INGRESO)) {
                personas.addAll(EntitiesHelper
                        .getPersonas(rLaboralRepo
                                .findAll(new RegistroLaboralQSpec(filtro))));
            }

        }
        return new PersonaDtoConverter().toDTO(personas);
    }

}
