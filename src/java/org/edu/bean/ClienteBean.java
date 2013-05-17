package org.edu.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.edu.model.Cliente;
import org.edu.repo.GenericRepo;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ClienteBean extends GenericBean{
    
    private GenericRepo<Cliente> repo = null;
    private Cliente cliente;
    private DataModel<Cliente> clientes;
    
    @PostConstruct
    private void init(){
        repo = new GenericRepo(Cliente.class);
        cliente = new Cliente();
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
        }
        catch(Exception e){
            showError(e);
        }
    }
    
    public DataModel<Cliente> getListarTodos(){
        clientes = new ListDataModel<Cliente>(repo.listaTodos());
        return clientes;
    }
}
