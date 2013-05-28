package org.edu.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends IntegerModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 60, unique = true, nullable = false)
    private String nome;
    
    @Column(length = 15)
    private String telefone;

    @Column(length = 120)
    private String email;
    
    @OneToMany(mappedBy = "cliente")
    private Collection<Pedido> pedidos;
    
    

    public Collection<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Collection<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome != null){
            nome = nome.toUpperCase();
        }
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Override
    public Integer getId() {
        return this.id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }
}
