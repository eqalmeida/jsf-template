package org.edu.bean;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Bean genérico com funções comuns para os demais beans.
 * @author eduardoalmeida
 */
public abstract class GenericBean {

    
    public GenericBean() {
    }
    

    /**
     * Redireciona para um caso de navegação.
     * @param outcome Caso de navegação.
     */
    protected void redirect(String outcome) {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
        handler.performNavigation(outcome+"?faces-redirect=true");
    }
    
    /**
     * Exibe uma mensagem na página JSF.
     * @param text Texto a ser exibido.
     * @param severity Nível de severidade.
     */
    private void showMessage(String text, FacesMessage.Severity severity){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(severity, text, null);
        context.addMessage(null, msg);
    }
    
    /**
     * Exibe uma mensagem de Informação.
     * @param text Texto a ser exibido.
     */
    protected void showInfo(String text){
        showMessage(text, FacesMessage.SEVERITY_INFO);
    }
    
    /**
     * Exibe uma mensagem de erro.
     * @param text Texto a ser exibido
     */
    protected void showError(String text){
        showMessage(text, FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Exibe uma mensagem de erro, a partir de uma excessão.
     * @param exception Excessão.
     */
    protected void showError(Exception exception){
        showMessage(exception.getMessage(), FacesMessage.SEVERITY_ERROR);
    }
}
