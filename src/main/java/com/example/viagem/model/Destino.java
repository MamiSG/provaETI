package com.example.viagem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O destino é obrigatório")
    private String nomeDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;  

    public Destino() {} 

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDestino() {
        return nomeDestino;
    }
    public void setNomeDestino(String nomeDestino) {
        this.nomeDestino = nomeDestino;
    }

    public Viagem getViagem() {
        return viagem;
    }
    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
}
