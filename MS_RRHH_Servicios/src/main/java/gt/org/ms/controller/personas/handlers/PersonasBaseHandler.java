/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.personas.handlers;

import gt.org.ms.api.requesting.AbstractValidationsRequestHandler;
import gt.org.ms.api.C;
import gt.org.ms.api.requesting.ValidationsHelper;
import static gt.org.ms.api.requesting.ValidationsHelper.containsAny;
import static gt.org.ms.api.requesting.ValidationsHelper.isNull;
import gt.org.ms.api.misc.exceptions.ExceptionsManager;
import gt.org.ms.controller.dto.DpiDto;
import gt.org.ms.controller.dto.PersonaDto;
import gt.org.ms.controller.dto.RequestCreatePersonaDto;
import gt.org.ms.model.AreaGeografica;
import gt.org.isis.model.AreaGeografica_;
import gt.org.ms.model.Catalogos;
import gt.org.isis.model.Catalogos_;
import gt.org.ms.model.Persona;
import static gt.org.ms.api.utils.EntitiesHelper.parseFechaDPI;
import gt.org.ms.model.enums.EstadoCivil;
import gt.org.ms.repository.AreasGeografRepository;
import gt.org.ms.repository.CatalogosRepository;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author edcracken
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
        return ValidationsHelper.findBestMatchItem(nombre.trim(), all);
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

    public void setDatosGeneralesByLector(Persona currentPersona, RequestCreatePersonaDto r) {
        if (isNull(r.getFoto()) || r.getFoto().isEmpty()) {
            throw ExceptionsManager.newValidationException("foto", "La fotografia es requerida!");
        }

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

        if (!isNull(r.getFkMunicipioVecindadNombre()) && !r.getFkMunicipioVecindadNombre().isEmpty()) {
            currentPersona.setFkMunicipioVecindad(getAreaByNombreAndTipo(r.getFkMunicipioVecindadNombre(),
                    C.CAT_AG_TIPO_MUNICIPIOS).getId());
        } else {
            throw ExceptionsManager.newValidationException("municipio_vecindad", "municipio vecindad es requerido!");
        }

        if (!isNull(r.getFkNacionalidadNombre()) && !r.getFkNacionalidadNombre().isEmpty()) {
            currentPersona.setFkNacionalidad(getCatalogoByNombreAndTipo(r.getFkNacionalidadNombre(),
                    C.CAT_GEN_NACIONALIDAD).getId());
        } else {
            throw ExceptionsManager.newValidationException("nacionalidad", "Nacionalidad es requerido!");
        }

        DpiDto dpiDto;
        if (!isNull(dpiDto = r.getDpi())) {
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
