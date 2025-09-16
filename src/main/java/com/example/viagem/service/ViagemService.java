package com.example.viagem.service;

import com.example.viagem.dto.ViagemDTO;
import com.example.viagem.dto.DestinoDTO;
import java.util.List;

public interface ViagemService {
    List<ViagemDTO> findAll();
    ViagemDTO findById(Long id);
    ViagemDTO create(ViagemDTO viagem);
    ViagemDTO update(Long id, ViagemDTO viagem);
    void delete(Long id);

    DestinoDTO addDestino(Long viagemId, DestinoDTO destino);
    void removeDestino(Long viagemId, Long destinoId);

}
