/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import gt.org.isis.api.AbstractValidationsRequestHandler;
import gt.org.isis.controller.dto.EstudioSaludDto;
import gt.org.isis.controller.dto.IdiomaDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.RegistroLaboralPuestoDto;
import gt.org.isis.controller.dto.ReqModPersonaDto;
import gt.org.isis.converters.DpiDtoConverter;
import gt.org.isis.converters.EstudiosSaludConverter;
import gt.org.isis.converters.IdiomaDtoConverter;
import gt.org.isis.converters.LugarResidenciaDtoConverter;
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.converters.PersonaHistorialConverter;
import gt.org.isis.converters.RegistroAcademicoConverter;
import gt.org.isis.converters.RegistroLaboralConverter;
import gt.org.isis.converters.RegistroLaboralPuestosConverter;
import gt.org.isis.model.Dpi;
import gt.org.isis.model.EstudioSalud;
import gt.org.isis.model.HistoricoPersona;
import gt.org.isis.model.Idioma;
import gt.org.isis.model.LugarResidencia;
import gt.org.isis.model.Persona;
import gt.org.isis.model.Puesto;
import gt.org.isis.model.RegistroAcademico;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.model.enums.EstadoVariable;
import gt.org.isis.model.utils.EntitiesHelper;
import gt.org.isis.repository.DpiRepository;
import gt.org.isis.repository.EstudiosSaludRepository;
import gt.org.isis.repository.HIstoricoPersonasRepository;
import gt.org.isis.repository.IdiomaRepository;
import gt.org.isis.repository.LugarResidenciaRepository;
import gt.org.isis.repository.PersonasRepository;
import gt.org.isis.repository.RegistroAcademicoRepository;
import gt.org.isis.repository.RegistroLaboralRepository;
import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edcracken
 */
@Service
public class PersonaModificarHandler extends AbstractValidationsRequestHandler<ReqModPersonaDto, Boolean> {

    @Autowired
    PersonasRepository repo;
    @Autowired
    HIstoricoPersonasRepository historicoRepo;
    @Autowired
    IdiomaRepository idiomasRepo;
    @Autowired
    EstudiosSaludRepository estudiosRepo;
    @Autowired
    PersonaDtoConverter converter;
    @Autowired
    RegistroAcademicoRepository rAcaRepository;
    @Autowired
    RegistroLaboralRepository rLabRepository;
    @Autowired
    DpiRepository dpiRepository;
    @Autowired
    LugarResidenciaRepository lrRepository;

    @Override
    public Boolean execute(ReqModPersonaDto r) {
        Persona p = repo.findOne(r.getCui());
        crearHistorico(p);
        guardaIdiomas(p, r);
        guardaEstudiosSalud(p, r);
        actualizaRegistroAcademico(p, r);
        actualizaRegistroLaboral(p, r);
        actualizaDpi(p, r);
        actualizaLugarResidencia(p, r);
        BeanUtils.copyProperties(r, p);
        p.setEdad(EntitiesHelper.getAge(r.getFechaNacimiento()));
        repo.save(p);
        return true;
    }

    private void guardaEstudiosSalud(Persona p, PersonaDto r) {
        estudiosRepo.deleteInBatch(p.getEstudioSaludCollection());

        for (EstudioSaludDto t : r.getEstudiosSalud()) {
            EstudioSalud i = new EstudiosSaludConverter().toEntity(t);
            i.setFkPersona(p);
            EntitiesHelper.setDateCreateRef(i);
            estudiosRepo.save(i);
        }
    }

    private void guardaIdiomas(Persona p, PersonaDto r) {
        idiomasRepo.deleteInBatch(p.getIdiomaCollection());
        for (IdiomaDto t : r.getIdiomas()) {
            Idioma i = new IdiomaDtoConverter().toEntity(t);
            i.setFkPersona(p);
            EntitiesHelper.setDateCreateRef(i);
            idiomasRepo.save(i);
        }
    }

    private void crearHistorico(Persona p) {
        HistoricoPersona hp = new PersonaHistorialConverter()
                .toEntity(new PersonaDtoConverter().toDTO(p));
        hp.setFkPersona(p);
        hp.setEdad(p.getEdad());
        EntitiesHelper.setDateCreateRef(hp);
        hp.setCreadoPor("admin");
        historicoRepo.save(hp);
    }

    private void actualizaRegistroAcademico(Persona p, PersonaDto r) {
        rAcaRepository.archivarRegitro(p.getCui());
        RegistroAcademico ra = new RegistroAcademicoConverter()
                .toEntity(r.getRegistroAcademico());
        ra.setEstado(EstadoVariable.ACTUAL);
        ra.setFkPersona(p);
        ra.setCreadoPor(p.getCreadoPor());
        EntitiesHelper.setDateCreateRef(ra);
        rAcaRepository.save(ra);
    }

    private void actualizaRegistroLaboral(Persona p, PersonaDto r) {
        rLabRepository.archivarRegitro(p.getCui());
        final RegistroLaboral rl = new RegistroLaboralConverter()
                .toEntity(r.getRegistroLaboral());
        rl.setEstado(EstadoVariable.ACTUAL);
        rl.setFkPersona(p);
        rl.setCreadoPor(p.getCreadoPor());
        EntitiesHelper.setDateCreateRef(rl);

        rl.setPuestoCollection(new ArrayList<Puesto>(Collections2
                .transform(r.getRegistroLaboral().getPuestos(),
                        new Function<RegistroLaboralPuestoDto, Puesto>() {
                    @Override
                    public Puesto apply(RegistroLaboralPuestoDto f) {
                        Puesto ps = new RegistroLaboralPuestosConverter().toEntity(f);
                        ps.setFkRegistroLaboral(rl);
                        EntitiesHelper.setDateCreateRef(ps);
                        ps.setCreadoPor("admin");
                        return ps;
                    }
                })));

        rLabRepository.save(rl);
    }

    private void actualizaDpi(Persona p, PersonaDto r) {
        dpiRepository.archivarRegitro(p.getCui());
        Dpi ra = new DpiDtoConverter()
                .toEntity(r.getDpi());
        ra.setEstado(EstadoVariable.ACTUAL);
        ra.setFkPersona(p);
        ra.setCreadoPor(p.getCreadoPor());
        EntitiesHelper.setDateCreateRef(ra);
        dpiRepository.save(ra);
    }

    private void actualizaLugarResidencia(Persona p, PersonaDto r) {
        lrRepository.archivarRegitro(p.getCui());
        LugarResidencia ra = new LugarResidenciaDtoConverter()
                .toEntity(r.getLugarResidencia());
        ra.setEstado(EstadoVariable.ACTUAL);
        ra.setFkPersona(p);
        ra.setCreadoPor(p.getCreadoPor());
        EntitiesHelper.setDateCreateRef(ra);
        lrRepository.save(ra);
    }

}
