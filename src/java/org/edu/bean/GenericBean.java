package org.edu.bean;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class GenericBean {

    public GenericBean() {
    }
 
    protected void redirect(String outcome) {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
        handler.performNavigation(outcome+"?faces-redirect=true");
    }
    
    private void showMessage(String text, FacesMessage.Severity severity){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(severity, text, null);
        context.addMessage(null, msg);
    }
    
    protected void showInfo(String text){
        showMessage(text, FacesMessage.SEVERITY_INFO);
    }
    
    protected void showError(String text){
        showMessage(text, FacesMessage.SEVERITY_ERROR);
    }
}
