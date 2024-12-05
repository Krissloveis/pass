package com.kris.pass.service;

import com.kris.pass.DTO.PassRequestDTO;
import com.kris.pass.DTO.PassResponseDTO;
import com.kris.pass.DTO.PassUpdateDTO;
import com.kris.pass.exception.PassNotFoundException;
import com.kris.pass.mapper.PassMapper;
import com.kris.pass.model.Pass;
import com.kris.pass.model.PassStatus;
import com.kris.pass.repository.PassRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PassService {

    private final PassRepository passRepository;
    private final PassMapper passMapper;


    public PassService(PassRepository passRepository, PassMapper passMapper) {
        this.passRepository = passRepository;
        this.passMapper = passMapper;
    }

    public PassResponseDTO addPass(PassRequestDTO passRequestDTO) {
        Pass pass = passMapper.convertToPass(passRequestDTO);
        Pass createdPass = passRepository.save(pass);
        return passMapper.convertToDTO(createdPass);
    }

    public List<PassResponseDTO> getAll() {
        List<Pass> allPasses = passRepository.findAll();
        List<PassResponseDTO> passDTO = allPasses.stream()
                .map(passMapper::convertToDTO)
                .collect(Collectors.toList());
        Collections.reverse(passDTO);
        return passDTO;
    }

    public PassResponseDTO getById(Long id) {
        Optional<Pass> foundPass = passRepository.findById(id);
        return foundPass.map(passMapper::convertToDTO).orElse(null);

    }

    public PassResponseDTO change(PassUpdateDTO passUpdateDTO) {
        Pass existingPass = passRepository.findById(passUpdateDTO.getPassID())
                .orElseThrow(() -> new PassNotFoundException(passUpdateDTO.getPassID()));
        passMapper.updatePassFromDTO(passUpdateDTO, existingPass);
        Pass updatedPass = passRepository.save(existingPass);
        return passMapper.convertToDTO(updatedPass);
    }

    public PassResponseDTO cancelPass(Long id) {
        Pass existingPass = passRepository.findById(id)
                .orElseThrow(() -> new PassNotFoundException(id));
        existingPass.setStatus(PassStatus.CANCELLED);
        Pass updatedStatusPass = passRepository.save(existingPass);
        return passMapper.convertToDTO(updatedStatusPass);
    }

    public PassResponseDTO delete(Long id) {
        Pass existingPass = passRepository.findById(id)
                .orElseThrow(() -> new PassNotFoundException(id));
        passRepository.delete(existingPass);
        return passMapper.convertToDTO(existingPass);
    }
}



