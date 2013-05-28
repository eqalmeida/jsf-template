package org.edu.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.edu.model.Cliente;
import org.edu.model.FormaPagamento;
import org.edu.model.ItemPedido;
import org.edu.model.PagtoType;
import org.edu.model.Pedido;
import org.edu.model.PedidoStatus;
import org.edu.model.Usuario;
import org.edu.repo.Repositorio;

@ManagedBean(name = "newPedido")
@SessionScoped
public class NewPedidoBean extends GenericBean {
    
    private Pedido pedido;
    private ItemPedido item;
    private List<PagtoType> tiposPagto;

    public List<PagtoType> getTiposPagto() {
        return tiposPagto;
    }
    
    
    
    @PostConstruct
    private void init() {
        novoPedido();
    }
    
    public void gravar() {
        
        try {
            
            //
            // Verifica usuário logado.
            //
            Usuario logado = getUsuarioLogado();
            
            if (logado == null) {
                throw new Exception("Falha obtendo usuário logado!");
            }
            
            //
            // Verifica itens.
            //
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
        
        for (int i=0; i<4; i++){
            FormaPagamento pag = new FormaPagamento();
            pag.setPagtoType(new PagtoType());
            pedido.addPagamento(pag);
        }
        
        Repositorio<PagtoType> repo = new Repositorio(PagtoType.class);
        tiposPagto = repo.listaTodos();
    }
    
    public void alteraPagto(FormaPagamento f){
        
        int i = f.getPagtoType().getId();
        if(i>0){
            Repositorio<PagtoType> r = new Repositorio<PagtoType>(PagtoType.class);
            f.setPagtoType(r.buscaPorId(i));
            f.setValor(BigDecimal.ZERO);
        }else{
            f.setPagtoType(new PagtoType());
            f.setValor(null);
        }
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
