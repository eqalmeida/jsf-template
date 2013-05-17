package org.edu.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.edu.model.Cliente;
import org.edu.repo.Connection;
import org.edu.repo.GenericRepo;

@ManagedBean
@ViewScoped
public class ClienteBean extends GenericBean{
    
    GenericRepo<Cliente> repo = null;
    
    @PostConstruct
    private void init(){
        repo = new GenericRepo<Cliente>(Connection.getManager(), Cliente.class);
    }

    public ClienteBean() {
    }
    
    public List<Cliente> getListarTodos(){
        return repo.listaTodos();
    }
}
