/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.org.isis.controller.dto;

/**
 *
 * @author edcracken
 */
public class SecureRequestDto {

    private LoggedUserDto loggedUser;

    public SecureRequestDto() {
        loggedUser = new LoggedUserDto("admin");
    }

    public LoggedUserDto getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(LoggedUserDto loggedUser) {
        this.loggedUser = loggedUser;
    }

}
