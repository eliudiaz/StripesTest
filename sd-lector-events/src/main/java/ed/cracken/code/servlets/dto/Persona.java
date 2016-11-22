/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.cracken.code.servlets.dto;

/**
 *
 * @author edcracken
 */
public class Persona {

    private String session;
    private String lblPicture; //base64
    private String txtApellido1;
    private String txtApellido2;
    private String txtApellido3;
    private String txtCUI;
    private String txtFecEmision; // 9ABR2013
    private String txtFecVencimiento; // 8ABR2023
    private String txtNombre1;
    private String txtNombre2;
    private String txtOtrosNombres; //
    private String txtMRZ2_1;
    private String txtMRZ2_2;
    private String txtGenero;
    private String txtEstadoCivil;
    private String txtNacionalidad;
    private String txtNumSerie;
    private String txtProfesion;
    private String txtLimitaciones; //
    private boolean chkOficialActivo; // Oficial Activo ; // valor: false
    private boolean chkSabeLeer; // Sabe Leer ; // valor: true
    private boolean chkSabeEscribir; // Sabe Escribir ; // valor: true
    private String txtVecMunicipio;
    private String txtVecDepto;
    private String txtFechaNacimiento; // 09MAY1983
    private String txtNacMunicipio; // CHIQUIMULILLA
    private String txtNacDepartamento; // SANTA ROSA
    private String txtNacPais; // GUATEMALA
    private String txtLibro;
    private String txtFolio;
    private String txtPartida;
    private boolean chkLeftThumb; // Pulgar ; // valor: false
    private boolean chkLeftIndex; // Índice ; // valor: true
    private boolean chkLeftMiddle; // Medio ; // valor: false
    private boolean chkLeftRing; // Anular ; // valor: false
    private boolean chkLeftLittle; // Meñique ; // valor: false
    private boolean chkRightThumb; // Pulgar ; // valor: false
    private boolean chkRightIndex; // Índice ; // valor: true
    private boolean chkRightMiddle; // Medio ; // valor: false
    private boolean chkRightRing; // Anular ; // valor: false
    private boolean chkRightLittle; // Meñique ; // valor: false
    private String txtCedulaNumero;
    private String txtCedulaMunicipio;
    private String txtCedulaDepto;
    private Integer edad;

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getLblPicture() {
        return lblPicture;
    }

    public void setLblPicture(String lblPicture) {
        this.lblPicture = lblPicture;
    }

    public String getTxtApellido1() {
        return txtApellido1;
    }

    public void setTxtApellido1(String txtApellido1) {
        this.txtApellido1 = txtApellido1;
    }

    public String getTxtApellido2() {
        return txtApellido2;
    }

    public void setTxtApellido2(String txtApellido2) {
        this.txtApellido2 = txtApellido2;
    }

    public String getTxtApellido3() {
        return txtApellido3;
    }

    public void setTxtApellido3(String txtApellido3) {
        this.txtApellido3 = txtApellido3;
    }

    public String getTxtCUI() {
        return txtCUI;
    }

    public void setTxtCUI(String txtCUI) {
        this.txtCUI = txtCUI;
    }

    public String getTxtFecEmision() {
        return txtFecEmision;
    }

    public void setTxtFecEmision(String txtFecEmision) {
        this.txtFecEmision = txtFecEmision;
    }

    public String getTxtFecVencimiento() {
        return txtFecVencimiento;
    }

    public void setTxtFecVencimiento(String txtFecVencimiento) {
        this.txtFecVencimiento = txtFecVencimiento;
    }

    public String getTxtNombre1() {
        return txtNombre1;
    }

    public void setTxtNombre1(String txtNombre1) {
        this.txtNombre1 = txtNombre1;
    }

    public String getTxtNombre2() {
        return txtNombre2;
    }

    public void setTxtNombre2(String txtNombre2) {
        this.txtNombre2 = txtNombre2;
    }

    public String getTxtOtrosNombres() {
        return txtOtrosNombres;
    }

    public void setTxtOtrosNombres(String txtOtrosNombres) {
        this.txtOtrosNombres = txtOtrosNombres;
    }

    public String getTxtMRZ2_1() {
        return txtMRZ2_1;
    }

    public void setTxtMRZ2_1(String txtMRZ2_1) {
        this.txtMRZ2_1 = txtMRZ2_1;
    }

    public String getTxtMRZ2_2() {
        return txtMRZ2_2;
    }

    public void setTxtMRZ2_2(String txtMRZ2_2) {
        this.txtMRZ2_2 = txtMRZ2_2;
    }

    public String getTxtGenero() {
        return txtGenero;
    }

    public void setTxtGenero(String txtGenero) {
        this.txtGenero = txtGenero;
    }

    public String getTxtEstadoCivil() {
        return txtEstadoCivil;
    }

    public void setTxtEstadoCivil(String txtEstadoCivil) {
        this.txtEstadoCivil = txtEstadoCivil;
    }

    public String getTxtNacionalidad() {
        return txtNacionalidad;
    }

    public void setTxtNacionalidad(String txtNacionalidad) {
        this.txtNacionalidad = txtNacionalidad;
    }

    public String getTxtNumSerie() {
        return txtNumSerie;
    }

    public void setTxtNumSerie(String txtNumSerie) {
        this.txtNumSerie = txtNumSerie;
    }

    public String getTxtProfesion() {
        return txtProfesion;
    }

    public void setTxtProfesion(String txtProfesion) {
        this.txtProfesion = txtProfesion;
    }

    public String getTxtLimitaciones() {
        return txtLimitaciones;
    }

    public void setTxtLimitaciones(String txtLimitaciones) {
        this.txtLimitaciones = txtLimitaciones;
    }

    public boolean isChkOficialActivo() {
        return chkOficialActivo;
    }

    public void setChkOficialActivo(boolean chkOficialActivo) {
        this.chkOficialActivo = chkOficialActivo;
    }

    public boolean isChkSabeLeer() {
        return chkSabeLeer;
    }

    public void setChkSabeLeer(boolean chkSabeLeer) {
        this.chkSabeLeer = chkSabeLeer;
    }

    public boolean isChkSabeEscribir() {
        return chkSabeEscribir;
    }

    public void setChkSabeEscribir(boolean chkSabeEscribir) {
        this.chkSabeEscribir = chkSabeEscribir;
    }

    public String getTxtVecMunicipio() {
        return txtVecMunicipio;
    }

    public void setTxtVecMunicipio(String txtVecMunicipio) {
        this.txtVecMunicipio = txtVecMunicipio;
    }

    public String getTxtVecDepto() {
        return txtVecDepto;
    }

    public void setTxtVecDepto(String txtVecDepto) {
        this.txtVecDepto = txtVecDepto;
    }

    public String getTxtFechaNacimiento() {
        return txtFechaNacimiento;
    }

    public void setTxtFechaNacimiento(String txtFechaNacimiento) {
        this.txtFechaNacimiento = txtFechaNacimiento;
    }

    public String getTxtNacMunicipio() {
        return txtNacMunicipio;
    }

    public void setTxtNacMunicipio(String txtNacMunicipio) {
        this.txtNacMunicipio = txtNacMunicipio;
    }

    public String getTxtNacDepartamento() {
        return txtNacDepartamento;
    }

    public void setTxtNacDepartamento(String txtNacDepartamento) {
        this.txtNacDepartamento = txtNacDepartamento;
    }

    public String getTxtNacPais() {
        return txtNacPais;
    }

    public void setTxtNacPais(String txtNacPais) {
        this.txtNacPais = txtNacPais;
    }

    public String getTxtLibro() {
        return txtLibro;
    }

    public void setTxtLibro(String txtLibro) {
        this.txtLibro = txtLibro;
    }

    public String getTxtFolio() {
        return txtFolio;
    }

    public void setTxtFolio(String txtFolio) {
        this.txtFolio = txtFolio;
    }

    public String getTxtPartida() {
        return txtPartida;
    }

    public void setTxtPartida(String txtPartida) {
        this.txtPartida = txtPartida;
    }

    public boolean isChkLeftThumb() {
        return chkLeftThumb;
    }

    public void setChkLeftThumb(boolean chkLeftThumb) {
        this.chkLeftThumb = chkLeftThumb;
    }

    public boolean isChkLeftIndex() {
        return chkLeftIndex;
    }

    public void setChkLeftIndex(boolean chkLeftIndex) {
        this.chkLeftIndex = chkLeftIndex;
    }

    public boolean isChkLeftMiddle() {
        return chkLeftMiddle;
    }

    public void setChkLeftMiddle(boolean chkLeftMiddle) {
        this.chkLeftMiddle = chkLeftMiddle;
    }

    public boolean isChkLeftRing() {
        return chkLeftRing;
    }

    public void setChkLeftRing(boolean chkLeftRing) {
        this.chkLeftRing = chkLeftRing;
    }

    public boolean isChkLeftLittle() {
        return chkLeftLittle;
    }

    public void setChkLeftLittle(boolean chkLeftLittle) {
        this.chkLeftLittle = chkLeftLittle;
    }

    public boolean isChkRightThumb() {
        return chkRightThumb;
    }

    public void setChkRightThumb(boolean chkRightThumb) {
        this.chkRightThumb = chkRightThumb;
    }

    public boolean isChkRightIndex() {
        return chkRightIndex;
    }

    public void setChkRightIndex(boolean chkRightIndex) {
        this.chkRightIndex = chkRightIndex;
    }

    public boolean isChkRightMiddle() {
        return chkRightMiddle;
    }

    public void setChkRightMiddle(boolean chkRightMiddle) {
        this.chkRightMiddle = chkRightMiddle;
    }

    public boolean isChkRightRing() {
        return chkRightRing;
    }

    public void setChkRightRing(boolean chkRightRing) {
        this.chkRightRing = chkRightRing;
    }

    public boolean isChkRightLittle() {
        return chkRightLittle;
    }

    public void setChkRightLittle(boolean chkRightLittle) {
        this.chkRightLittle = chkRightLittle;
    }

    public String getTxtCedulaNumero() {
        return txtCedulaNumero;
    }

    public void setTxtCedulaNumero(String txtCedulaNumero) {
        this.txtCedulaNumero = txtCedulaNumero;
    }

    public String getTxtCedulaMunicipio() {
        return txtCedulaMunicipio;
    }

    public void setTxtCedulaMunicipio(String txtCedulaMunicipio) {
        this.txtCedulaMunicipio = txtCedulaMunicipio;
    }

    public String getTxtCedulaDepto() {
        return txtCedulaDepto;
    }

    public void setTxtCedulaDepto(String txtCedulaDepto) {
        this.txtCedulaDepto = txtCedulaDepto;
    }

    @Override
    public String toString() {
        return "Persona{" + "lblPicture=" + lblPicture + ", txtApellido1=" + txtApellido1
                + ", txtApellido2=" + txtApellido2
                + ", txtApellido3=" + txtApellido3 + ", txtCUI=" + txtCUI
                + ", txtFecEmision=" + txtFecEmision + ", txtFecVencimiento=" + txtFecVencimiento
                + ", txtNombre1=" + txtNombre1 + ", txtNombre2=" + txtNombre2
                + ", txtOtrosNombres=" + txtOtrosNombres + ", txtMRZ2_1=" + txtMRZ2_1
                + ", txtMRZ2_2=" + txtMRZ2_2 + ", txtGenero=" + txtGenero
                + ", txtEstadoCivil=" + txtEstadoCivil + ", txtNacionalidad=" + txtNacionalidad
                + ", txtNumSerie=" + txtNumSerie + ", txtProfesion=" + txtProfesion
                + ", txtLimitaciones=" + txtLimitaciones + ", chkOficialActivo=" + chkOficialActivo + ", chkSabeLeer=" + chkSabeLeer + ", chkSabeEscribir=" + chkSabeEscribir + ", txtVecMunicipio=" + txtVecMunicipio + ", txtVecDepto=" + txtVecDepto + ", txtFechaNacimiento=" + txtFechaNacimiento + ", txtNacMunicipio=" + txtNacMunicipio + ", txtNacDepartamento=" + txtNacDepartamento + ", txtNacPais=" + txtNacPais + ", txtLibro=" + txtLibro + ", txtFolio=" + txtFolio + ", txtPartida=" + txtPartida + ", chkLeftThumb=" + chkLeftThumb + ", chkLeftIndex=" + chkLeftIndex + ", chkLeftMiddle=" + chkLeftMiddle + ", chkLeftRing=" + chkLeftRing + ", chkLeftLittle=" + chkLeftLittle + ", chkRightThumb=" + chkRightThumb + ", chkRightIndex=" + chkRightIndex + ", chkRightMiddle=" + chkRightMiddle + ", chkRightRing=" + chkRightRing + ", chkRightLittle=" + chkRightLittle + ", txtCedulaNumero=" + txtCedulaNumero + ", txtCedulaMunicipio=" + txtCedulaMunicipio + ", txtCedulaDepto=" + txtCedulaDepto + '}';
    }

}
