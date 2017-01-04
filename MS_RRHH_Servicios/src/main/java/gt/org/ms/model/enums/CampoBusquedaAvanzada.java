package gt.org.ms.model.enums;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author edcracken
 */
public enum CampoBusquedaAvanzada {

    UNIDAD_EJECUTORA("unidad_ejecutora"),
    PUESTO_NOMINAL("puesto_nominal"),
    PUESTO_FUNCIONAL("puesto_funcional"),
    RENGLON("renglon"),
    CLASIFICACION_SERVICIO("clasificacion_servicio"),
    ANIO_INGRESO("anio_ingreso");

    private String value;

    private CampoBusquedaAvanzada(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CampoBusquedaAvanzada forValue(String value) {
        return CampoBusquedaAvanzada.valueOf(value);
    }

}
