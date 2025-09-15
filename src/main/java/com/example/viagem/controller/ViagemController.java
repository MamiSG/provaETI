package com.example.viagem.controller;

import com.example.viagem.model.Viagem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Viagem")
public class ViagemController {
    private final com.example.viagem.service.ViagemService viagemService;
    private final com.example.viagem.repository.ViagemRepository viagemRepository;

    public ViagemController(com.example.viagem.service.ViagemService viagemService, com.example.viagem.repository.ViagemRepository viagemRepository) {
        this.viagemService = viagemService;
        this.viagemRepository = viagemRepository;
    }

    @GetMapping
    public List<Viagem> listarTodos() {
        return viagemService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viagem> obterPorId(@PathVariable Long id) {
        Optional<Viagem> viagem = viagemRepository.findById(id);
        return viagem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Viagem criar(@RequestBody Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viagem> atualizar(@PathVariable Long id, @RequestBody Viagem viagemDetalhes) {
        Optional<Viagem> optionalViagem = viagemRepository.findById(id);
        if (optionalViagem.isPresent()) {
            Viagem viagem = optionalViagem.get();
            viagem.setNome(viagemDetalhes.getNome());
            viagem.setDataSaida(viagemDetalhes.getDataSaida());
            viagem.setDataChegada(viagemDetalhes.getDataChegada());
            viagem.setValor(viagemDetalhes.getValor());
            Viagem viagemAtualizada = viagemRepository.save(viagem);
            return ResponseEntity.ok(viagemAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (viagemRepository.existsById(id)) {
            viagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}