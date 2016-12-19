/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.ms.controller.dto;

import gt.org.ms.api.utils.ExcelCol;
import gt.org.ms.model.enums.EstadoCivil;
import gt.org.ms.model.enums.Sexo;

/**
 *
 * @author edcracken
 */
public class PersonaRowsFileDto {

    @ExcelCol(order = 1, title = "PRIMER NOMBRE")
    private String primerNombre;
    @ExcelCol(order = 2, title = "SEGUNDO NOMBRE")
    private String segundoNombre;
    @ExcelCol(order = 3, title = "PRIMER APELLIDO")
    private String primerApellido;
    @ExcelCol(order = 4, title = "SEGUNDO APELLIDO")
    private String segundoApellido;
    @ExcelCol(order = 5, title = "APELLIDO DE CASADA")
    private String apellidoCasada;
    @ExcelCol(order = 6, title = "CUI")
    private String cui;
    @ExcelCol(order = 7, title = "SEXO")
    private Sexo sexo;
    @ExcelCol(order = 8, title = "ESTADO CIVIL")
    private EstadoCivil estadoCivil;
    @ExcelCol(order = 9, title = "NACIONALIDAD")
    private String fkNacionalidadNombre;
    @ExcelCol(order = 10, title = "LIMITACIONES FISICAS")
    private String limitacionesFisicas;
    @ExcelCol(order = 11, title = "SABER LEER")
    private String sabeLeer;
    @ExcelCol(order = 12, title = "SABER ESCRIBIR")
    private String sabeEscribir;
    @ExcelCol(order = 13, title = "FECHA DE NACIMIENTO")
    private String fechaNacimientoTexto;
    @ExcelCol(order = 15, title = "EDAD")
    private Integer edad;
    @ExcelCol(order = 14, title = "PAIS RESIDENCIA")
    private String paisResidencia;
    @ExcelCol(order = 16, title = "DEPARTAMENTO RESIDENCIA")
    private String departamentoResidencia;
    @ExcelCol(order = 17, title = "MUNICIPIO RESIDENCIA")
    private String municipioResidencia;
    @ExcelCol(order = 18, title = "DETALLE RESIDENCIA")
    private String direccion;
    @ExcelCol(order = 19, title = "PUEBLO")
    private String pueblo;
    @ExcelCol(order = 20, title = "COMUNIDAD LINGUISTICA")
    private String comunidadLinguistica;
    @ExcelCol(order = 21, title = "IDIOMAS")
    private String idiomas;
    @ExcelCol(order = 22, title = "ULTIMO GRADO")
    private String ultimoGrado;
    @ExcelCol(order = 23, title = "GRADO APROBADO")
    private String nivelUltimoGrado;
    @ExcelCol(order = 24, title = "ESTUDIA ACTUALMENTE")
    private String estudiaActualmente;
    @ExcelCol(order = 25, title = "GRADO QUE ESTUDIA ACTUALMENTE")
    private String gradoActual;
    @ExcelCol(order = 26, title = "GRADO ACTUAL")
    private String nivelGradoActual;
    @ExcelCol(order = 27, title = "OTROS ESTUDIOS")
    private String otrosEstudios;
    @ExcelCol(order = 28, title = "AÑO DE INGRESO INSTITUCION")
    private String anioIngreso;
    @ExcelCol(order = 29, title = "PUESTO DE ACUERSO A SUS FUNCIONES")
    private String puestoFunciones;
    @ExcelCol(order = 29, title = "RENGLON PRESUPUESTARIO")
    private String renglon;
    @ExcelCol(order = 31, title = "UNIDAD EJECUTORA ACTUAL")
    private String unidadEjecutora;
    @ExcelCol(order = 32, title = "CLASIFICACION DEL SERVICIO")
    private String clasificacionServicio;
    @ExcelCol(order = 33, title = "ES COMISIONADO")
    private String comisionado;
    @ExcelCol(order = 34, title = "CUENTA CON OTRO PUESTO")
    private String puestoAdicional;

    public String getNivelGradoActual() {
        return nivelGradoActual;
    }

    public String getRenglon() {
        return renglon;
    }

    public void setRenglon(String renglon) {
        this.renglon = renglon;
    }

    public void setNivelGradoActual(String nivelGradoActual) {
        this.nivelGradoActual = nivelGradoActual;
    }

    public String getNivelUltimoGrado() {
        return nivelUltimoGrado;
    }

    public void setNivelUltimoGrado(String nivelUltimoGrado) {
        this.nivelUltimoGrado = nivelUltimoGrado;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidoCasada() {
        return apellidoCasada;
    }

    public void setApellidoCasada(String apellidoCasada) {
        this.apellidoCasada = apellidoCasada;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getFkNacionalidadNombre() {
        return fkNacionalidadNombre;
    }

    public void setFkNacionalidadNombre(String fkNacionalidadNombre) {
        this.fkNacionalidadNombre = fkNacionalidadNombre;
    }

    public String getLimitacionesFisicas() {
        return limitacionesFisicas;
    }

    public void setLimitacionesFisicas(String limitacionesFisicas) {
        this.limitacionesFisicas = limitacionesFisicas;
    }

    public String getSabeLeer() {
        return sabeLeer;
    }

    public void setSabeLeer(String sabeLeer) {
        this.sabeLeer = sabeLeer;
    }

    public String getSabeEscribir() {
        return sabeEscribir;
    }

    public void setSabeEscribir(String sabeEscribir) {
        this.sabeEscribir = sabeEscribir;
    }

    public String getFechaNacimientoTexto() {
        return fechaNacimientoTexto;
    }

    public void setFechaNacimientoTexto(String fechaNacimientoTexto) {
        this.fechaNacimientoTexto = fechaNacimientoTexto;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getPaisResidencia() {
        return paisResidencia;
    }

    public void setPaisResidencia(String paisResidencia) {
        this.paisResidencia = paisResidencia;
    }

    public String getDepartamentoResidencia() {
        return departamentoResidencia;
    }

    public void setDepartamentoResidencia(String departamentoResidencia) {
        this.departamentoResidencia = departamentoResidencia;
    }

    public String getMunicipioResidencia() {
        return municipioResidencia;
    }

    public void setMunicipioResidencia(String municipioResidencia) {
        this.municipioResidencia = municipioResidencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPueblo() {
        return pueblo;
    }

    public void setPueblo(String pueblo) {
        this.pueblo = pueblo;
    }

    public String getComunidadLinguistica() {
        return comunidadLinguistica;
    }

    public void setComunidadLinguistica(String comunidadLinguistica) {
        this.comunidadLinguistica = comunidadLinguistica;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getUltimoGrado() {
        return ultimoGrado;
    }

    public void setUltimoGrado(String ultimoGrado) {
        this.ultimoGrado = ultimoGrado;
    }

    public String getEstudiaActualmente() {
        return estudiaActualmente;
    }

    public void setEstudiaActualmente(String estudiaActualmente) {
        this.estudiaActualmente = estudiaActualmente;
    }

    public String getGradoActual() {
        return gradoActual;
    }

    public void setGradoActual(String gradoActual) {
        this.gradoActual = gradoActual;
    }

    public String getOtrosEstudios() {
        return otrosEstudios;
    }

    public void setOtrosEstudios(String otrosEstudios) {
        this.otrosEstudios = otrosEstudios;
    }

    public String getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(String anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public String getPuestoFunciones() {
        return puestoFunciones;
    }

    public void setPuestoFunciones(String puestoFunciones) {
        this.puestoFunciones = puestoFunciones;
    }

    public String getUnidadEjecutora() {
        return unidadEjecutora;
    }

    public void setUnidadEjecutora(String unidadEjecutora) {
        this.unidadEjecutora = unidadEjecutora;
    }

    public String getClasificacionServicio() {
        return clasificacionServicio;
    }

    public void setClasificacionServicio(String clasificacionServicio) {
        this.clasificacionServicio = clasificacionServicio;
    }

    public String getComisionado() {
        return comisionado;
    }

    public void setComisionado(String comisionado) {
        this.comisionado = comisionado;
    }

    public String getPuestoAdicional() {
        return puestoAdicional;
    }

    public void setPuestoAdicional(String puestoAdicional) {
        this.puestoAdicional = puestoAdicional;
    }

    public PersonaRowsFileDto() {
    }

    private PersonaRowsFileDto(Builder builder) {
        this.primerNombre = builder.primerNombre;
        this.segundoNombre = builder.segundoNombre;
        this.primerApellido = builder.primerApellido;
        this.segundoApellido = builder.segundoApellido;
        this.apellidoCasada = builder.apellidoCasada;
        this.cui = builder.cui;
        this.sexo = builder.sexo;
        this.estadoCivil = builder.estadoCivil;
        this.fkNacionalidadNombre = builder.fkNacionalidadNombre;
        this.limitacionesFisicas = builder.limitacionesFisicas;
        this.sabeLeer = builder.sabeLeer;
        this.sabeEscribir = builder.sabeEscribir;
        this.fechaNacimientoTexto = builder.fechaNacimientoTexto;
        this.edad = builder.edad;
        this.paisResidencia = builder.paisResidencia;
        this.departamentoResidencia = builder.departamentoResidencia;
        this.municipioResidencia = builder.municipioResidencia;
        this.direccion = builder.direccion;
        this.pueblo = builder.pueblo;
        this.comunidadLinguistica = builder.comunidadLinguistica;
        this.idiomas = builder.idiomas;
        this.ultimoGrado = builder.ultimoGrado;
        this.nivelUltimoGrado = builder.nivelUltimoGrado;
        this.estudiaActualmente = builder.estudiaActualmente;
        this.gradoActual = builder.gradoActual;
        this.nivelGradoActual = builder.nivelGradoActual;
        this.otrosEstudios = builder.otrosEstudios;
        this.anioIngreso = builder.anioIngreso;
        this.puestoFunciones = builder.puestoFunciones;
        this.unidadEjecutora = builder.unidadEjecutora;
        this.clasificacionServicio = builder.clasificacionServicio;
        this.comisionado = builder.comisionado;
        this.puestoAdicional = builder.puestoAdicional;
        this.renglon = builder.renglon;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String primerNombre;
        private String segundoNombre;
        private String primerApellido;
        private String segundoApellido;
        private String apellidoCasada;
        private String cui;
        private Sexo sexo;
        private EstadoCivil estadoCivil;
        private String fkNacionalidadNombre;
        private String limitacionesFisicas;
        private String sabeLeer;
        private String sabeEscribir;
        private String fechaNacimientoTexto;
        private Integer edad;
        private String paisResidencia;
        private String departamentoResidencia;
        private String municipioResidencia;
        private String direccion;
        private String pueblo;
        private String comunidadLinguistica;
        private String idiomas;
        private String ultimoGrado;
        private String nivelUltimoGrado;
        private String estudiaActualmente;
        private String gradoActual;
        private String nivelGradoActual;
        private String otrosEstudios;
        private String anioIngreso;
        private String puestoFunciones;
        private String unidadEjecutora;
        private String clasificacionServicio;
        private String comisionado;
        private String puestoAdicional;
        private String renglon;

        public Builder withRenglon(String renglon) {
            this.renglon = renglon;
            return this;
        }

        public Builder withPrimerNombre(String primerNombre) {
            this.primerNombre = primerNombre;
            return this;
        }

        public Builder withSegundoNombre(String segundoNombre) {
            this.segundoNombre = segundoNombre;
            return this;
        }

        public Builder withPrimerApellido(String primerApellido) {
            this.primerApellido = primerApellido;
            return this;
        }

        public Builder withSegundoApellido(String segundoApellido) {
            this.segundoApellido = segundoApellido;
            return this;
        }

        public Builder withApellidoCasada(String apellidoCasada) {
            this.apellidoCasada = apellidoCasada;
            return this;
        }

        public Builder withCui(String cui) {
            this.cui = cui;
            return this;
        }

        public Builder withSexo(Sexo sexo) {
            this.sexo = sexo;
            return this;
        }

        public Builder withEstadoCivil(EstadoCivil estadoCivil) {
            this.estadoCivil = estadoCivil;
            return this;
        }

        public Builder withFkNacionalidadNombre(String fkNacionalidadNombre) {
            this.fkNacionalidadNombre = fkNacionalidadNombre;
            return this;
        }

        public Builder withLimitacionesFisicas(String limitacionesFisicas) {
            this.limitacionesFisicas = limitacionesFisicas;
            return this;
        }

        public Builder withSabeLeer(String sabeLeer) {
            this.sabeLeer = sabeLeer;
            return this;
        }

        public Builder withSabeEscribir(String sabeEscribir) {
            this.sabeEscribir = sabeEscribir;
            return this;
        }

        public Builder withFechaNacimientoTexto(String fechaNacimientoTexto) {
            this.fechaNacimientoTexto = fechaNacimientoTexto;
            return this;
        }

        public Builder withEdad(Integer edad) {
            this.edad = edad;
            return this;
        }

        public Builder withPaisResidencia(String paisResidencia) {
            this.paisResidencia = paisResidencia;
            return this;
        }

        public Builder withDepartamentoResidencia(String departamentoResidencia) {
            this.departamentoResidencia = departamentoResidencia;
            return this;
        }

        public Builder withMunicipioResidencia(String municipioResidencia) {
            this.municipioResidencia = municipioResidencia;
            return this;
        }

        public Builder withDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder withPueblo(String pueblo) {
            this.pueblo = pueblo;
            return this;
        }

        public Builder withComunidadLinguistica(String comunidadLinguistica) {
            this.comunidadLinguistica = comunidadLinguistica;
            return this;
        }

        public Builder withIdiomas(String idiomas) {
            this.idiomas = idiomas;
            return this;
        }

        public Builder withUltimoGrado(String ultimoGrado) {
            this.ultimoGrado = ultimoGrado;
            return this;
        }

        public Builder withNivelUltimoGrado(String nivelUltimoGrado) {
            this.nivelUltimoGrado = nivelUltimoGrado;
            return this;
        }

        public Builder withNivelGradoActual(String nivelGradoActual) {
            this.nivelGradoActual = nivelGradoActual;
            return this;
        }

        public Builder withEstudiaActualmente(String estudiaActualmente) {
            this.estudiaActualmente = estudiaActualmente;
            return this;
        }

        public Builder withGradoActual(String gradoActual) {
            this.gradoActual = gradoActual;
            return this;
        }

        public Builder withOtrosEstudios(String otrosEstudios) {
            this.otrosEstudios = otrosEstudios;
            return this;
        }

        public Builder withAnioIngreso(String anioIngreso) {
            this.anioIngreso = anioIngreso;
            return this;
        }

        public Builder withPuestoFunciones(String puestoFunciones) {
            this.puestoFunciones = puestoFunciones;
            return this;
        }

        public Builder withUnidadEjecutora(String unidadEjecutora) {
            this.unidadEjecutora = unidadEjecutora;
            return this;
        }

        public Builder withClasificacionServicio(String clasificacionServicio) {
            this.clasificacionServicio = clasificacionServicio;
            return this;
        }

        public Builder withComisionado(String comisionado) {
            this.comisionado = comisionado;
            return this;
        }

        public Builder withPuestoAdicional(String puestoAdicional) {
            this.puestoAdicional = puestoAdicional;
            return this;
        }

        public PersonaRowsFileDto build() {
            return new PersonaRowsFileDto(this);
        }
    }

}
