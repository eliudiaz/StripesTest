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
public enum Pueblo {

    MESTIZO_O_LADINO("mestizo_o_ladino"),
    MAYA("maya"),
    GARIFUNA("garifuna"),
    XINCA("xinca"),
    OTRO("otro"),
    NO_INDICA("no_indica");

    private String value;

    private Pueblo(String value) {
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
    public static Pueblo forValue(String value) {
        return Pueblo.valueOf(value);
    }

}
