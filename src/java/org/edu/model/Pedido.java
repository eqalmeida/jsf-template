package org.edu.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido implements EntityFacade<Integer>, Serializable {

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

    @Override
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

    @Override
    public boolean isNew() {
        return (this.id == null || this.id == 0);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
