package org.edu.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.edu.model.Cliente;
import org.edu.repo.Repositorio;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ClienteBean extends GenericBean{
    
    private Repositorio<Cliente> repo = null;
    private Cliente cliente;
    private List<Cliente> clientes;
    private static int versaoGlobal = 0;
    private int versaoLocal = 0;
    
    @PostConstruct
    private void init(){
        repo = new Repositorio(Cliente.class);
        cliente = new Cliente();
    }

    public int getVersaoLocal() {
        return versaoLocal;
    }
    
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    

    private void showJSFDialog(){
        // TODO Implementar
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("clienteDlg.show()");        
    }
    

    private void closeJSFDialog(){
        // TODO Implementar
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("clienteDlg.hide()");        
    }
    
    public void novo(){
        cliente = new Cliente();
        
        showJSFDialog();
    }
    
    public void editar(final Cliente cl){
        try{
            
            cliente = cl;
            
            showJSFDialog();
        }
        catch(Exception e){
            showError(e);
        }
        
    }
    
    public void gravar(){
        try{
            repo.gravar(cliente);
            cliente = new Cliente();
            closeJSFDialog();
            versaoGlobal += 1;
            showInfo("Cliente gravado com sucesso!");
           
        }
        catch(Exception e){
            showError(e);
        }
    }
    
    public void excluir(Integer id){
        try{
            cliente = repo.buscaPorId(id);
            repo.excluir(cliente);
            cliente = new Cliente();
            showInfo("Cliente excluído com sucesso!");
            versaoGlobal += 1;
        }
        catch(Exception e){
            showError(e);
        }
    }
    
    public List<Cliente> getListarTodos(){
        if(clientes == null || versaoGlobal != versaoLocal){
            versaoLocal = versaoGlobal;
            clientes = repo.listaTodos();
        }
        
        return clientes;
    }
}
