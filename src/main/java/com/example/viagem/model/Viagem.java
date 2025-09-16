package com.example.viagem.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "A data de saída é obrigatória")
    private LocalDate dataSaida;

    @NotNull(message = "A data de chegada é obrigatória")
    private LocalDate dataChegada;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal valor;

    @OneToMany(mappedBy = "viagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Destino> destinos = new ArrayList<>();

    public Viagem() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }
    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDate getDataChegada() {
        return dataChegada;
    }
    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<Destino> getDestinos() {
        return destinos;
    }
    public void setDestinos(List<Destino> destinos) {
        this.destinos = destinos;
    }

public void addDestino(Destino destino) {
        this.destinos.add(destino);
        destino.setViagem(this);
    }

    public void removeDestino(Destino destino) {
        this.destinos.remove(destino);
        if (destino.getViagem() == this) {
            destino.setViagem(null);
        }
    }
}
