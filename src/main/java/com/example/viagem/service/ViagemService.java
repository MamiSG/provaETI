package com.example.viagem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.viagem.model.Viagem;
import com.example.viagem.repository.ViagemRepository;


@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    public List<Viagem> listarTodos() {
        return viagemRepository.findAll();
    }

    public Viagem saveViagem(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

}