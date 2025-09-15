package com.example.viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.viagem.model.Viagem;


public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}


