/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.personas.handlers;

import gt.org.isis.api.AbstractValidationsRequestHandler;
import gt.org.isis.api.C;
import gt.org.isis.api.ValidationsHelper;
import static gt.org.isis.api.ValidationsHelper.containsAny;
import static gt.org.isis.api.ValidationsHelper.isNull;
import gt.org.isis.api.misc.exceptions.ExceptionsManager;
import gt.org.isis.controller.dto.DpiDto;
import gt.org.isis.controller.dto.PersonaDto;
import gt.org.isis.controller.dto.RequestPersonaDto;
import gt.org.isis.model.AreaGeografica;
import gt.org.isis.model.AreaGeografica_;
import gt.org.isis.model.Catalogos;
import gt.org.isis.model.Catalogos_;
import gt.org.isis.model.Persona;
import static gt.org.isis.model.utils.EntitiesHelper.parseFechaDPI;
import gt.org.isis.repository.AreasGeografRepository;
import gt.org.isis.repository.CatalogosRepository;
import java.util.Calendar;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author eliud
 */
public abstract class PersonasBaseHandler<T extends PersonaDto, Q> extends AbstractValidationsRequestHandler<T, Q> {

    @Override
    public abstract Q execute(T request);
    @Autowired
    AreasGeografRepository areasRepo;
    @Autowired
    CatalogosRepository catalogosRepo;

    private Catalogos getCatalogoByNombreAndTipo(final String nombre, final String tipo) {
        List<Catalogos> all = catalogosRepo.findAll(new Specification<Catalogos>() {
            @Override
            public Predicate toPredicate(Root<Catalogos> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(Catalogos_.tipo), tipo);
            }
        });
        for (Catalogos ag : all) {
            if (containsAny(ag.getValor(), nombre)) {
                return ag;
            }
        }
        return all.get(0);
    }

    private AreaGeografica getAreaByNombreAndTipo(final String nombre, final String tipo) {
        List<AreaGeografica> all = areasRepo.findAll(new Specification<AreaGeografica>() {
            @Override
            public Predicate toPredicate(Root<AreaGeografica> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(AreaGeografica_.tipo), tipo);
            }
        });
        for (AreaGeografica ag : all) {
            if (ag.getValor().trim().equalsIgnoreCase(nombre.trim())) {
                return ag;
            }
        }

        return ValidationsHelper.findBestMatchItem(nombre.trim(), all);
    }

    public void setDatosGeneralesByLector(Persona currentPersona, RequestPersonaDto r) {
        if (!isNull(r.getFechaNacimientoTexto()) && !r.getFechaNacimientoTexto().isEmpty()) {
            currentPersona.setFechaNacimiento(parseFechaDPI(r.getFechaNacimientoTexto()));
        } else {
            throw ExceptionsManager.newValidationException("fecha_nacimiento", "fecha de nacimiento es requerida!");
        }

        currentPersona.setEdad(Years.yearsBetween(LocalDate.fromDateFields(currentPersona.getFechaNacimiento()),
                LocalDate.fromDateFields(Calendar.getInstance().getTime())).getYears());

        if (!isNull(r.getFkMunicipioNacimientoNombre()) && !r.getFkMunicipioNacimientoNombre().isEmpty()) {
            currentPersona.setFkMunicipioNacimiento(getAreaByNombreAndTipo(r.getFkMunicipioNacimientoNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        } else {
            throw ExceptionsManager.newValidationException("municipio_nacimiento", "municipio nacimiento es requerido!");
        }

        if (!isNull(r.getFkMunicipioCedulaNombre()) && !r.getFkMunicipioCedulaNombre().isEmpty()) {
            currentPersona.setFkMunicipioCedula(getAreaByNombreAndTipo(r.getFkMunicipioCedulaNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        }

        if (!isNull(r.getFkNacionalidadNombre()) && !r.getFkNacionalidadNombre().isEmpty()) {
            currentPersona.setFkNacionalidad(getCatalogoByNombreAndTipo(r.getFkNacionalidadNombre(),
                    C.CAT_GEN_NACIONALIDAD).getId());
        } else {
            throw ExceptionsManager.newValidationException("municipio_nacimiento", "Nacionalidad es requerido!");
        }
        DpiDto dpiDto;
        if ((dpiDto = r.getDpi()) != null) {
            if (!isNull(dpiDto.getFechaEmisionTexto()) && !isNull(dpiDto.getFechaVencimientoTexto())) {
                dpiDto.setFechaEmision(parseFechaDPI(dpiDto.getFechaEmisionTexto()));
                dpiDto.setFechaVencimiento(parseFechaDPI(dpiDto.getFechaVencimientoTexto()));
                r.setDpi(dpiDto);
            } else if (isNull(dpiDto.getFechaVencimiento()) || isNull(dpiDto.getFechaEmision())) {
                throw ExceptionsManager.newValidationException("dpi_datos",
                        new String[]{"fechas_dpi,Debe ingresar datos de fecha vencimiento y emision del DPI"});
            }
        } else {
            throw ExceptionsManager.newValidationException("dpi",
                    new String[]{"dpi,Lectura de DPI es invalida, datos del DPI son requeridos!"});
        }
    }
}
