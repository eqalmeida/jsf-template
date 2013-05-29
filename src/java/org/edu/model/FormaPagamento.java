package org.edu.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class FormaPagamento implements Serializable{
    
    @Column(nullable = false)
    private int id;
    
    @ManyToOne(optional = false)
    private PagtoType pagtoType;
    
    @Column(precision = 7, scale = 2)
    private BigDecimal valor;
    
    @Column(nullable = false)
    private int qtdParcelas = 1;

    @Column(precision = 7, scale = 4)
    private double taxaJurosMes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public PagtoType getPagtoType() {
        return pagtoType;
    }

    public void setPagtoType(PagtoType pagtoType) {
        this.pagtoType = pagtoType;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public double getTaxaJurosMes() {
        return taxaJurosMes;
    }

    public void setTaxaJurosMes(double taxaJurosMes) {
        this.taxaJurosMes = taxaJurosMes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final FormaPagamento other = (FormaPagamento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
