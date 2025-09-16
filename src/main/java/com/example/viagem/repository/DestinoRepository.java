package com.example.viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.viagem.model.Destino;

public interface    DestinoRepository extends JpaRepository<Destino, Long> {
}
