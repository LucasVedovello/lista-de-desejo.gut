
package com.aos.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "wish")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String preco;

    @Column(nullable = false, length = 500)
    private String prioridade;

 @Column(nullable = false)
    private boolean comprado;

    public Item() {
        this.preco = "";
        this.comprado = false; 
    }
      public Item(String preco, String prioridade){
        this.preco = preco;
        this.prioridade = prioridade;
        this.comprado = false;
    }
    public Long getId(){
        return id;
    }
    public String getPreco(){
        return preco;
    }
    public String getPrioridade(){
        return prioridade;
    }
    public boolean getComprado(){
        return comprado;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setPreço(String preco){
        this.preco = preco;
    }
    public void setPrioridade(String prioridade){
        this.prioridade = prioridade;
    }
    public void setComprado(boolean comprado){
        this.comprado = comprado;
    }



}