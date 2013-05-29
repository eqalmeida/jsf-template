package org.edu.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido extends IntegerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(optional = false)
    private Cliente cliente;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataPedido;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro = new Date();
    
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;
    
    @ElementCollection
    private List<ItemPedido> items;
    
    @ElementCollection
    private List<FormaPagamento> pagamentos;

    /**
     * Retorna o valor total do Pedido através da soma do valor de cada item.
     *
     * @return
     */
    public BigDecimal getValorTotalPedido() {

        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedido it : this.getItems()) {
            total = total.add(it.getValorTotal());
        }

        return total;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemPedido> getItems() {

        if (items == null) {
            items = new ArrayList<ItemPedido>();
        }

        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }
    
    private void reordenarPagamentos(){
        int cont = 0;
        for(FormaPagamento p : getPagamentos()){
            p.setId(cont);
            cont++;
        }
    }
    
    public void deletePagamento(FormaPagamento pagamento){
        getPagamentos().remove(pagamento);
        reordenarPagamentos();
    }
    
    public void addPagamento(FormaPagamento pagamento){
        pagamento.setId(getPagamentos().size() + 1);
        getPagamentos().add(pagamento);
    }

    public List<FormaPagamento> getPagamentos() {
        
        if(pagamentos == null){
            pagamentos = new ArrayList<FormaPagamento>();
        }
        
        return pagamentos;
    }

    public void setPagamentos(List<FormaPagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
    
}
