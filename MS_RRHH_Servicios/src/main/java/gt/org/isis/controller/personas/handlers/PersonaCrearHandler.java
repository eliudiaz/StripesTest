/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import static gt.org.isis.api.requesting.ValidationsHelper.isNull;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.DpiDto;
import gt.org.isis.controller.dto.EstudioSaludDto;
import gt.org.isis.controller.dto.IdiomaDto;
import gt.org.isis.controller.dto.RegistroLaboralPuestoDto;
import gt.org.isis.controller.dto.RequestCreatePersonaDto;
import gt.org.isis.converters.DpiDtoConverter;
import gt.org.isis.converters.EstudiosSaludConverter;
import gt.org.isis.converters.IdiomaDtoConverter;
import gt.org.isis.converters.LugarResidenciaDtoConverter;
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.converters.RegistroAcademicoConverter;
import gt.org.isis.converters.RegistroLaboralConverter;
import gt.org.isis.converters.RegistroLaboralPuestosConverter;
import gt.org.isis.model.Dpi;
import gt.org.isis.model.EstudioSalud;
import gt.org.isis.model.Idioma;
import gt.org.isis.model.LugarResidencia;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.RegistroAcademico;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.model.enums.Estado;
import gt.org.isis.model.enums.EstadoVariable;
import gt.org.isis.repository.DpiRepository;
import gt.org.isis.repository.EstudiosSaludRepository;
import gt.org.isis.repository.IdiomaRepository;
import gt.org.isis.repository.LugarResidenciaRepository;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.PuestoRepository;
import gt.org.isis.repository.RegistroAcademicoRepository;
import gt.org.isis.repository.RegistroLaboralRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edcracken
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonaCrearHandler extends PersonasBaseHandler<RequestCreatePersonaDto, Boolean> {

    @Autowired
    PersonasRepository repo;
    @Autowired
    PersonaDtoConverter converter;
    @Autowired
    IdiomaRepository idiomasRepo;
    @Autowired
    RegistroAcademicoRepository regAcadRepo;
    @Autowired
    RegistroLaboralRepository regLaboralRepo;
    @Autowired
    PuestoRepository puestoRepo;
    @Autowired
    EstudiosSaludRepository estudiosRepo;
    @Autowired
    DpiRepository dpiRepo;
    @Autowired
    LugarResidenciaRepository lugaresRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean execute(RequestCreatePersonaDto r) {
        Persona p = converter.toEntity(r);
        guardarDatosGeneralesPersona(r, p)
                .guardarDpi(r, p)
                .guardarIdiomas(r, p)
                .guardarRegAcademico(r, p)
                .guardarRegLaboral(r, p)
                .guardarEstudiosSalud(r, p)
                .guardarLugarResidencia(r, p);

        return true;
    }

    private PersonaCrearHandler guardarDatosGeneralesPersona(RequestCreatePersonaDto r, Persona currentPersona) {
        currentPersona.setEstado(Estado.ACTIVO);
        setCreateInfo(currentPersona);

        if (r.isLector()) {
            setDatosGeneralesByLector(currentPersona, r);
        }
        currentPersona = repo.save(currentPersona);
        return this;
    }

    private PersonaCrearHandler guardarIdiomas(RequestCreatePersonaDto r, final Persona currentPersona) {
        idiomasRepo.save(
                Collections2.transform(r.getIdiomas(),
                        new Function<IdiomaDto, Idioma>() {
                    @Override
                    public Idioma apply(IdiomaDto f) {
                        Idioma i = new IdiomaDtoConverter().toEntity(f);
                        i.setFkPersona(currentPersona);
                        setCreateInfo(i);
                        return i;
                    }
                }));

        return this;
    }

    private PersonaCrearHandler guardarRegAcademico(RequestCreatePersonaDto r, Persona currentPersona) {
        RegistroAcademico ra = new RegistroAcademicoConverter().toEntity(r.getRegistroAcademico());
        ra.setFkPersona(currentPersona);
        ra.setEstado(EstadoVariable.ACTUAL);
        setCreateInfo(ra);
        regAcadRepo.save(ra);

        return this;
    }

    private PersonaCrearHandler guardarRegLaboral(RequestCreatePersonaDto r, Persona currentPersona) {
        RegistroLaboral rl = new RegistroLaboralConverter()
                .toEntity(r.getRegistroLaboral());
        rl.setEstado(EstadoVariable.ACTUAL);
        rl.setFkPersona(currentPersona);
        setCreateInfo(rl);
        final RegistroLaboral rl2 = regLaboralRepo.save(rl);

        puestoRepo.save((Collection) Collections2.transform(r.getRegistroLaboral().getPuestos(),
                new Function<RegistroLaboralPuestoDto, Puesto>() {
            @Override
            public Puesto apply(RegistroLaboralPuestoDto f) {
                Puesto ps = new RegistroLaboralPuestosConverter().toEntity(f);
                ps.setFkRegistroLaboral(rl2);
                setCreateInfo(ps);
                return ps;
            }
        }));

        return this;
    }

    private PersonaCrearHandler guardarEstudiosSalud(RequestCreatePersonaDto r, final Persona currentPersona) {
        if (!isNull(r.getEstudiosSalud())) {

            if (!Collections2.filter(r.getEstudiosSalud(), new Predicate<EstudioSaludDto>() {
                @Override
                public boolean apply(EstudioSaludDto t) {
                    return isNull(t.getAnioEstudio());
                }
            }).isEmpty()) {
                throw ExceptionsManager.newValidationException("anio_estudio", "Los estudios de salud deben tener el año correspondiente!");
            }
            estudiosRepo.save(
                    Collections2.transform(r.getEstudiosSalud(),
                            new Function<EstudioSaludDto, EstudioSalud>() {
                        @Override
                        public EstudioSalud apply(EstudioSaludDto f) {
                            EstudioSalud es = new EstudiosSaludConverter()
                                    .toEntity(f);
                            es.setFkPersona(currentPersona);
                            setCreateInfo(es);
                            return es;
                        }
                    }));
        }
        return this;
    }

    private PersonaCrearHandler guardarDpi(RequestCreatePersonaDto r, Persona currentPersona) {
        DpiDto dpiDto = r.getDpi();
        Dpi dpi = new DpiDtoConverter().toEntity(dpiDto);
        dpi.setFkPersona(currentPersona);
        dpi.setEstado(EstadoVariable.ACTUAL);
        setCreateInfo(dpi);

        currentPersona.setDpiCollection(new ArrayList<Dpi>());
        currentPersona.getDpiCollection().add(dpi);
        repo.save(currentPersona);
        return this;
    }

    private PersonaCrearHandler guardarLugarResidencia(RequestCreatePersonaDto r, Persona currentPersona) {
        LugarResidencia lr = new LugarResidenciaDtoConverter().toEntity(r.getLugarResidencia());
        lr.setFkPersona(currentPersona);
        lr.setEstado(EstadoVariable.ACTUAL);
        setCreateInfo(lr);
        lugaresRepo.save(lr);
        return this;
    }

}
