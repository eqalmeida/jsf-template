package org.edu.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.edu.model.Cliente;
import org.edu.model.Usuario;
import org.edu.repo.Repositorio;

/**
 *
 * @author eduardoalmeida
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserBean extends GenericBean {

    private boolean logado = false;
    private Integer usuarioId;
    private String loginName;
    private String loginPassword;
    private Repositorio<Usuario> repositorio;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        super();
    }
    
    @PostConstruct
    private void init(){
        repositorio = new Repositorio(Usuario.class);
        
        Usuario admin = repositorio.busca("login", "admin");
        
        if(admin == null){
            admin = new Usuario();
            admin.setLogin("admin");
            admin.setSenha("admin");
            admin.setAdministrador(true);
            
            repositorio.gravar(admin);
            
        }
    }

    public void checkLogin() {
        if (!logado) {
            redirect("login");
        }
    }
    
    public Usuario getUsuarioLogado(){
        
        Usuario user = new Usuario();
        
        if(logado){
            user = repositorio.buscaPorId(usuarioId);
            
        }
        
        return user;
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
        
        Usuario user = repositorio.busca("login", loginName);
        
        if(user == null){
            showError("Login inválido!");
            return;
        }
        
        if(!(user.getSenha().equals(loginPassword))){
            showError("Senha inválida!");
            return;
        }
        
        if(!(user.isAtivo())){
            showError("Usuário desativado!");
            return;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("userId", user.getId());        
        
        
        usuarioId = user.getId();
        logado = true;
        redirect("index");
    }
    
    public void doLogoff(){
        
        
        loginName = "";
        loginPassword = "";
        logado = false;
        redirect("login");
        showInfo("Sessão encerrada!");
    }
}
