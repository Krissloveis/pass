package com.kris.pass.service;

import com.kris.pass.DTO.PassRequestDTO;
import com.kris.pass.DTO.PassResponseDTO;
import com.kris.pass.DTO.PassUpdateDTO;
import com.kris.pass.mapper.PassMapper;
import com.kris.pass.model.Pass;
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


    public PassResponseDTO get(Long id) {
        Optional<Pass> foundPass = passRepository.findById(id);
        return foundPass.map(passMapper::convertToDTO).orElse(null);

    }

    public PassResponseDTO change(PassUpdateDTO passUpdateDTO) {
        Pass existingPass = passRepository.findById(passUpdateDTO.getPassId())
                .orElseThrow(() -> new EntityNotFoundException("Pass not found with id: " + passUpdateDTO.getPassId()));
        existingPass.setPersonName(passUpdateDTO.getPersonName());
        existingPass.setPersonSurname(passUpdateDTO.getPersonSurname());
        existingPass.setStatus(passUpdateDTO.getStatus());
        Pass updatedPass = passRepository.save(existingPass);
        return passMapper.convertToDTO(updatedPass);
    }
        /*
    public String correct() {
        passList.correct();
        return id;
    }*/

        public PassResponseDTO delete (Long id) {
            Optional<Pass> optionalPass = passRepository.findById(id);
            if (optionalPass.isPresent()) {
                Pass passToDelete = optionalPass.get();
                passRepository.delete(passToDelete);
                return passMapper.convertToDTO(passToDelete);
            }
            return null;
        }
    }



