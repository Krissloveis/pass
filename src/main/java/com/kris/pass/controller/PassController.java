package com.kris.pass.controller;

import com.kris.pass.DTO.PassRequestDTO;
import com.kris.pass.DTO.PassResponseDTO;
import com.kris.pass.DTO.PassUpdateDTO;
import com.kris.pass.exception.PassNotFoundException;
import com.kris.pass.service.PassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController


@RequestMapping("pass")


public class PassController {

    private final PassService passService;

    public PassController(PassService passService) {
        this.passService = passService;
    }


    @PostMapping
    public PassResponseDTO addPass(@RequestBody PassRequestDTO passRequestDTO) {
        return passService.addPass(passRequestDTO);
    }


    @GetMapping
    public List<PassResponseDTO> getAll() {
        return passService.getAll();
    }

    @GetMapping("/{id}")
    public PassResponseDTO getById(@PathVariable Long id) {
        return passService.getById(id);
    }

    @PutMapping()
    public PassResponseDTO change(@RequestBody PassUpdateDTO passUpdateDTO) {
        return passService.change(passUpdateDTO);
    }

    @PatchMapping("/{id}/cancel")
    public PassResponseDTO cancelPass(@PathVariable Long id) {
        return passService.cancelPass(id);
    }

    @DeleteMapping("/{id}")
    public PassResponseDTO delete(@PathVariable Long id) {
        return passService.delete(id);
    }

}
