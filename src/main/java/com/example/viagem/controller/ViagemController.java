package com.example.viagem.controller;

import com.example.viagem.dto.ViagemDTO;
import com.example.viagem.dto.DestinoDTO;
import com.example.viagem.service.ViagemService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viagem")
public class ViagemController {

    private final ViagemService service;

    public ViagemController(ViagemService service) {
        this.service = service;
    }

    @GetMapping
    public List<ViagemDTO> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ViagemDTO get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<ViagemDTO> create(@Valid @RequestBody ViagemDTO dto) {
        ViagemDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ViagemDTO update(@PathVariable Long id, @Valid @RequestBody ViagemDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/destinos")
    public ResponseEntity<DestinoDTO> addDestino(@PathVariable Long id, @Valid @RequestBody DestinoDTO destino) {
        DestinoDTO created = service.addDestino(id, destino);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/{id}/destinos/{destId}")
    public ResponseEntity<Void> removeDestino(@PathVariable Long id, @PathVariable Long destId) {
        service.removeDestino(id, destId);
        return ResponseEntity.noContent().build();
    }
}