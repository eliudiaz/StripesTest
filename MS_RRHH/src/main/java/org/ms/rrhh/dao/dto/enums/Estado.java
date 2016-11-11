package org.ms.rrhh.dao.dto.enums;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

/**
 *
 * @author edcracken
 */
public enum Estado {

    ACTIVO("activo"),
    INACTIVO("inactivo");

    private String value;

    private Estado(String value) {
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
    public static Estado forValue(String value) {
        return Estado.valueOf(value);
    }

}
