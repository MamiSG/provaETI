package com.example.viagem.service;

import com.example.viagem.dto.ViagemDTO;
import com.example.viagem.dto.DestinoDTO;
import com.example.viagem.model.Viagem;
import com.example.viagem.model.Destino;
import com.example.viagem.repository.DestinoRepository;
import com.example.viagem.repository.ViagemRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
@Transactional
public class ViagemServiceImpl implements ViagemService {

    private final ViagemRepository viagemRepo;
    private final DestinoRepository destinoRepo;

    public ViagemServiceImpl(ViagemRepository viagemRepo, DestinoRepository destinoRepo) {
        this.viagemRepo = viagemRepo;
        this.destinoRepo = destinoRepo;
    }

    private ViagemDTO toDTO(Viagem v) {
        ViagemDTO dto = new ViagemDTO();
        dto.setId(v.getId());
        dto.setNome(v.getNome());
        dto.setDataSaida(v.getDataSaida());
        dto.setDataChegada(v.getDataChegada());
        dto.setValor(v.getValor());
        dto.setDestinos(
            v.getDestinos().stream()
                .map(d -> {
                    DestinoDTO dd = new DestinoDTO();
                    dd.setId(d.getId());
                    dd.setNome(d.getNomeDestino());
                    return dd;
                })
                .collect(Collectors.toList())
        );
        return dto;
    }

    private Viagem toEntity(ViagemDTO dto) {
        Viagem v = new Viagem();
        v.setNome(dto.getNome());
        v.setDataSaida(dto.getDataSaida());
        v.setDataChegada(dto.getDataChegada());
        v.setValor(dto.getValor() == null ? BigDecimal.ZERO : dto.getValor());
        return v;
    }

    @Override
    public List<ViagemDTO> findAll() {
        return viagemRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Viagem findEntityOrThrow(Long id) {
        return viagemRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Viagem não encontrada: " + id));
    }

    @Override
    public ViagemDTO findById(Long id) {
        return toDTO(findEntityOrThrow(id));
    }

    @Override
    public ViagemDTO create(ViagemDTO dto) {
        Viagem v = toEntity(dto);
        if (dto.getDestinos() != null) {
            dto.getDestinos().forEach(dDto -> {
                Destino d = new Destino();
                d.setNomeDestino(dDto.getNome());
                v.addDestino(d);
            });
        }
        Viagem saved = viagemRepo.save(v);
        return toDTO(saved);
    }

    @Override
    public ViagemDTO update(Long id, ViagemDTO dto) {
        Viagem existing = findEntityOrThrow(id);
        existing.setNome(dto.getNome());
        existing.setDataSaida(dto.getDataSaida());
        existing.setDataChegada(dto.getDataChegada());
        existing.setValor(dto.getValor());

        if (dto.getDestinos() != null) {
            while (!existing.getDestinos().isEmpty()) {
                existing.removeDestino(existing.getDestinos().get(0));
            }
            dto.getDestinos().forEach(dDto -> {
                Destino d = new Destino();
                d.setNomeDestino(dDto.getNome());
                existing.addDestino(d);
            });
        }

        Viagem saved = viagemRepo.save(existing);
        return toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        viagemRepo.deleteById(id);
    }

    @Override
    public DestinoDTO addDestino(Long viagemId, DestinoDTO destinoDTO) {
        Viagem viagem = findEntityOrThrow(viagemId);
        Destino d = new Destino();
        d.setNomeDestino(destinoDTO.getNome());
        d.setViagem(viagem);
        viagem.addDestino(d);
        Destino savedDestino = destinoRepo.save(d);
        viagemRepo.save(viagem);
        DestinoDTO out = new DestinoDTO();
        out.setId(savedDestino.getId());
        out.setNome(savedDestino.getNomeDestino());
        return out;
    }

    @Override
    public void removeDestino(Long viagemId, Long destinoId) {
        Viagem viagem = findEntityOrThrow(viagemId);
        Optional<Destino> opt = viagem.getDestinos().stream().filter(d -> d.getId().equals(destinoId)).findFirst();
        if (opt.isEmpty()) throw new EntityNotFoundException("Destino não pertence à viagem ou não existe");
        Destino destino = opt.get();
        viagem.removeDestino(destino);
        viagemRepo.save(viagem);
    }
}