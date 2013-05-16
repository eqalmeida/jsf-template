package org.edu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.edu.model.Cliente;
import org.edu.repo.GenericRepo;

/**
 *
 * @author eduardoalmeida
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean extends GenericBean {

    private boolean logado = false;
    private String loginName;
    private String loginPassword;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        super();
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
        else{
            showError("Login inválido!");
        }
    }
    
    public void doLogoff(){
        
        GenericRepo cliRepo = new GenericRepo<Cliente>(manager,Cliente.class);
        
        Cliente c = new Cliente();
        cliRepo.gravar(c);
        
        loginName = "";
        loginPassword = "";
        logado = false;
        redirect("login");
        showInfo("Sessão encerrada!");
    }
}
