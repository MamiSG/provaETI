package com.example.viagem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ViagemDTO {
    private Long id;
    private String nome;
    private LocalDate dataSaida;
    private LocalDate dataChegada;
    private BigDecimal valor;
    private List<DestinoDTO> destinos;
    // getters e setters...
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
    public List<DestinoDTO> getDestinos() {
        return destinos;
    }
    public void setDestinos(List<DestinoDTO> destinos) {
        this.destinos = destinos;
    }

}


