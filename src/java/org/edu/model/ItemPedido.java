package org.edu.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class ItemPedido implements Comparable<ItemPedido>, Serializable {

    @Transient
    private int pos;
    
    @ManyToOne
    private Produto produto;
    
    @Column(length = 50, nullable = false)
    private String descricao;
    @Column(precision = 7, scale = 2)
    private BigDecimal valorUnit;
    private Integer qtd = 1;

    /**
     * Calcula o valor total do Item.
     *
     * @return Valor Total
     */
    public BigDecimal getValorTotal() {
        if (this.valorUnit == null || this.qtd == null) {
            return BigDecimal.ZERO;
        }

        return (this.valorUnit.multiply(new BigDecimal(this.qtd)));

    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(BigDecimal valorUnit) {
        this.valorUnit = valorUnit;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.descricao != null ? this.descricao.hashCode() : 0);
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
        final ItemPedido other = (ItemPedido) obj;
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(ItemPedido t) {
        return (this.getDescricao().compareTo(t.getDescricao()));
    }
}
