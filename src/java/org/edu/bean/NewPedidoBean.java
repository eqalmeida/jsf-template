package org.edu.bean;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.edu.model.Cliente;
import org.edu.model.ItemPedido;
import org.edu.model.Pedido;
import org.edu.model.PedidoStatus;
import org.edu.model.Usuario;
import org.edu.repo.Repositorio;

@ManagedBean(name = "newPedido")
@SessionScoped
public class NewPedidoBean extends GenericBean {
    
    private Pedido pedido;
    private ItemPedido item;
    
    @PostConstruct
    private void init() {
        novoPedido();
    }
    
    public void gravar() {
        
        try {
            
            Usuario logado = getUsuarioLogado();
            
            if (logado == null) {
                throw new Exception("Falha obtendo usuário logado!");
            }
            
            if (pedido.getItems().isEmpty()) {
                throw new Exception("Este pedido não possui ítens!");
            }
            
            Repositorio<Cliente> clienteRepo = new Repositorio<Cliente>(Cliente.class);
            
            List<Cliente> clientes = clienteRepo.listaTodos();
            
            Cliente c = new Cliente();
            
            if (clientes == null || clientes.isEmpty()) {
                c.setNome("Cliente teste");
                clienteRepo.gravar(c);
            } else {
                c = clientes.get(0);
            }
            
            pedido.setCliente(c);
            pedido.setUsuario(logado);
            pedido.setStatus(PedidoStatus.NOVO);
            
            Repositorio<Pedido> pedidoRepo = new Repositorio<Pedido>(Pedido.class);
            
            pedidoRepo.gravar(pedido);
            
            novoPedido();
            
            showInfo("Pedido gerado com sucesso!");
            
            
        } catch (Exception e) {
            showError(e);
        }
    }
    
    public void novoPedido() {
        pedido = new Pedido();
        pedido.setCliente(new Cliente());
        item = new ItemPedido();
        pedido.setDataPedido(new Date());
    }
    
    public void addItem() {
        if (pedido.getItems().contains(item)) {
            showError("Ítem já cadastrado!");
        } else {
            this.pedido.getItems().add(item);
            this.item = new ItemPedido();
        }
    }
    
    public void deleteItem(ItemPedido it) {
        pedido.getItems().remove(it);
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public ItemPedido getItem() {
        return item;
    }
    
    public void setItem(ItemPedido item) {
        this.item = item;
    }
}
