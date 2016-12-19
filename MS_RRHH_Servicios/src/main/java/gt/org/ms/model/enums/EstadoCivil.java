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
public enum EstadoCivil {
    SOLTERA("soltera"),
    SOLTERO("soltero"),
    CASADO("casado"), CASADA("casada"),
    DIVORCIADO("divorciado"), DIVORCIADA("divorciada"),
    VIUDO("viudo"), VIUDA("viuda"), UNIDO("unido"), UNIDA("unida");

    private String value;

    private EstadoCivil(String value) {
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
    public static EstadoCivil forValue(String value) {
        return EstadoCivil.valueOf(value);
    }

}
