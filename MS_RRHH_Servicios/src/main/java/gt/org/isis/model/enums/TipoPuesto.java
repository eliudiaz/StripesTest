package gt.org.isis.model.enums;

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
public enum TipoPuesto {

    PRINCIPAL("principal"),
    ADICIONAL("adicional");

    private String value;

    private TipoPuesto(String value) {
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
    public static TipoPuesto forValue(String value) {
        return TipoPuesto.valueOf(value);
    }

}
