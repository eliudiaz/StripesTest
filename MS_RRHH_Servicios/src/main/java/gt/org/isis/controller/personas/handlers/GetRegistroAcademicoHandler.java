/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import gt.org.isis.api.AbstractRequestHandler;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.jpa.SingularAttrSpecificationBased;
import gt.org.isis.controller.dto.RegistroAcademicoDto;
import gt.org.isis.converters.RegistroAcademicoConverter;
import gt.org.isis.model.Catalogos;
import gt.org.isis.model.Catalogos_;
import gt.org.isis.model.RegistroAcademico;
import gt.org.isis.model.enums.EstadoVariable;
import gt.org.isis.repository.CatalogosRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eliud
 */
@Component
public class GetRegistroAcademicoHandler extends AbstractRequestHandler<List<RegistroAcademico>, RegistroAcademicoDto> {

    @Override
    public RegistroAcademicoDto execute(List<RegistroAcademico> request) {
        List<RegistroAcademicoDto> hisRA = new ArrayList<RegistroAcademicoDto>();
        RegistroAcademicoDto currentRA = null;
        for (RegistroAcademico ra : request) {
            if (ra.getEstado().equals(EstadoVariable.ACTUAL)) {
                currentRA = new RegistroAcademicoConverter().toDTO(ra);
                currentRA.setHistorial(hisRA);
                fillRegistro(currentRA);
            } else {
                hisRA.add(new RegistroAcademicoConverter().toDTO(ra));
            }
        }
        if (isNull(currentRA)) {
            currentRA = !hisRA.isEmpty() ? hisRA.get(0) : null;
            fillRegistro(currentRA);
        }

        return currentRA;
    }
    @Autowired
    CatalogosRepository catalogosRepo;

    private void fillRegistro(RegistroAcademicoDto reg) {
        Catalogos c = (Catalogos) catalogosRepo
                .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, reg.getUltimoGrado()));
        reg.setNombreUltimoGrado(c.getValor());
        if (reg.getEstudiaActualmente()) {
            c = (Catalogos) catalogosRepo
                    .findOne(new SingularAttrSpecificationBased<Catalogos>(Catalogos_.id, reg.getGradoActual()));
            reg.setNombreGradoActual(c.getValor());
        }
    }

}
