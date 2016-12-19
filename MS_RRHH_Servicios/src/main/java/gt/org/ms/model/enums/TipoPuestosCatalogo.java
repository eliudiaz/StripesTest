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
public enum TipoPuestosCatalogo {

    RENGLON("renglon"),
    PUESTO_NOMINAL("puesto_nominal");

    private String value;

    private TipoPuestosCatalogo(String value) {
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
    public static TipoPuestosCatalogo forValue(String value) {
        return TipoPuestosCatalogo.valueOf(value);
    }

}
