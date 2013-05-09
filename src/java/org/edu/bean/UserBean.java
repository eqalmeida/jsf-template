/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.edu.bean;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eduardoalmeida
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean {

    private boolean logado = false;
    private String loginName;
    private String loginPassword;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    private void redirect(String outcome) {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
        handler.performNavigation(outcome);
    }

    public void checkLogin() {
        if (!logado) {
            redirect("login");
        }
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void doLogin() {
        if (loginName.equals("eqalmeida") && loginPassword.equals("181294")) {
            logado = true;
            redirect("index");
        }
    }
    
    public void doLogoff(){
        loginName = "";
        loginPassword = "";
        logado = false;
        redirect("login");
    }
}
