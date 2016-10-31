/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import gt.org.isis.api.AbstractRequestHandler;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.converters.DpiDtoConverter;
import gt.org.isis.converters.EstudiosSaludConverter;
import gt.org.isis.converters.IdiomaDtoConverter;
import gt.org.isis.converters.PersonaDtoConverter;
import gt.org.isis.converters.RegistroAcademicoConverter;
import gt.org.isis.converters.RegistroLaboralConverter;
import gt.org.isis.model.Dpi;
import gt.org.isis.model.Persona;
import gt.org.isis.model.RegistroAcademico;
import gt.org.isis.model.RegistroLaboral;
import gt.org.isis.repository.PersonasRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author edcracken
 */
@Service
public class PersonaCrearHandler extends AbstractRequestHandler<PersonaDto, Boolean> {

    @Autowired
    PersonasRepository repo;
    @Autowired
    PersonaDtoConverter converter;

    @Override
    public Boolean execute(PersonaDto r) {
        Persona p = converter.toEntity(r);
        repo.save(p);
        p.setIdiomaCollection(new IdiomaDtoConverter().toEntity(r.getIdiomas()));

        RegistroAcademico ra;
        p.setRegistroAcademicoCollection(
                Arrays.asList(
                        ra = new RegistroAcademicoConverter().toEntity(r.getRegistroAcademico())));
        ra.setFkPersona(p);

        RegistroLaboral rl;
        p.setRegistroLaboralCollection(
                Arrays.asList(rl = new RegistroLaboralConverter().toEntity(r.getRegistroLaboral()))
        );
        rl.setFkPersona(p);

        Dpi dpi;
        p.setDpiCollection(Arrays.asList(dpi = new DpiDtoConverter().toEntity(r.getDpi())));
        p.setEstudioSaludCollection(
                new EstudiosSaludConverter()
                        .toEntity(r.getEstudiosSalud()));
        dpi.setFkPersona(p);

        return true;
    }

}