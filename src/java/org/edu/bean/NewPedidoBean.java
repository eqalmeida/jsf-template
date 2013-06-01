package org.edu.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.edu.model.Cliente;
import org.edu.model.FormaPagamento;
import org.edu.model.ItemPedido;
import org.edu.model.PagtoType;
import org.edu.model.Pedido;
import org.edu.model.PedidoStatus;
import org.edu.model.Usuario;
import org.edu.repo.Repositorio;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

@ManagedBean(name = "newPedido")
@SessionScoped
public class NewPedidoBean extends GenericBean implements Serializable{
    
    private static final long serialVersionUID = 7526472295622776147L;

    private Pedido pedido;
    private List<SelectItem> tiposPagtoSL;
    private Repositorio<PagtoType> pagtoTypeRepo;

    public Repositorio<PagtoType> getPagtoTypeRepo() {
        if(pagtoTypeRepo == null){
            pagtoTypeRepo = new Repositorio<PagtoType>(PagtoType.class);
        }
        return pagtoTypeRepo;
    }
    
    

    public List<SelectItem> getTiposPagtoSL() {
        if (tiposPagtoSL == null || tiposPagtoSL.isEmpty()) {
            Repositorio<PagtoType> repo = new Repositorio(PagtoType.class);
            List<PagtoType> tiposPagto = repo.listaTodos();
            
            tiposPagtoSL = new ArrayList<SelectItem>();
            
            for(PagtoType type: tiposPagto){
                SelectItem sel = new SelectItem(type.getId(), type.getNome());
                tiposPagtoSL.add(sel);
            }
            
        }
        return tiposPagtoSL;
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

        for(int i=0; i<4 ;i++){
            FormaPagamento fp = new FormaPagamento();
            fp.setPagtoType(new PagtoType());
            pedido.addPagamento(fp);
        }

        //for(int i=0; i<4 ;i++){
            pedido.addItem(new ItemPedido());
        //}

        pedido.setDataPedido(new Date());

    }
    
    public void pagamentoAlterado(FormaPagamento pag){
        showInfo("Tipo: " + pag.getPagtoType().getId());
    }
    
    public void updatePagamentos(){
        int count = 0;
        for(FormaPagamento fp : pedido.getPagamentos()){
            if(!fp.getPagtoType().isNew()){
                int fpId = fp.getPagtoType().getId();
                PagtoType type = getPagtoTypeRepo().buscaPorId(fpId);
                fp.setPagtoType(type);
            }
            
            if(fp.getPagtoType().getNome() != null 
                    && (!fp.getPagtoType().getNome().isEmpty())
                    && fp.getValor().doubleValue() > 0.0){
                count += 1;
            }
        }
        
        if(count == pedido.getPagamentos().size()){
            FormaPagamento f = new FormaPagamento();
            f.setPagtoType(new PagtoType());
            pedido.addPagamento(f);
            
        }
    }
    
    public void onCellEdit(CellEditEvent event) {  
        Object oldValue = event.getOldValue();  
        Object newValue = event.getNewValue();  
          
        if(newValue != null && !newValue.equals(oldValue)) {  
            updatePagamentos();
            RequestContext.getCurrentInstance().update("fmPagtos:tblPagtos");
        }  
    }      

    public void alteraPagto(FormaPagamento f) {

        int i = f.getPagtoType().getId();
        if (i > 0) {
            Repositorio<PagtoType> r = new Repositorio<PagtoType>(PagtoType.class);
            f.setPagtoType(r.buscaPorId(i));
            f.setValor(BigDecimal.ZERO);
        } else {
            f.setPagtoType(new PagtoType());
            f.setValor(null);
        }
    }

    public void addItem() {
        pedido.addItem(new ItemPedido());
    }

    public void deleteItem(int itemId) {
        pedido.removeItem(itemId);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
