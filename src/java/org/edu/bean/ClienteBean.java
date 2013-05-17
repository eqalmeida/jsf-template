package org.edu.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.edu.model.Cliente;
import org.edu.repo.GenericRepo;

@ManagedBean
@ViewScoped
public class ClienteBean extends GenericBean{
    
    private GenericRepo<Cliente> repo = null;
    private Cliente cliente;
    
    @PostConstruct
    private void init(){
        repo = new GenericRepo(Cliente.class);
        cliente = new Cliente();
    }

    private void showJSFDialog(){
        // TODO Implementar
    }
    
    public void novo(){
        cliente = new Cliente();
        
        showJSFDialog();
    }
    
    public void editar(Integer id){
        try{
            cliente = repo.buscaPorId(id);
            
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
    
    public List<Cliente> getListarTodos(){
        return repo.listaTodos();
    }
}
