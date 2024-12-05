package com.kris.pass.mapper;


import com.kris.pass.DTO.PassRequestDTO;
import com.kris.pass.DTO.PassResponseDTO;
import com.kris.pass.DTO.PassUpdateDTO;
import com.kris.pass.model.Pass;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PassMapper {

    PassResponseDTO convertToDTO(Pass pass);

    @Mapping(target = "passID", ignore = true)
    @Mapping(target = "addedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    Pass convertToPass(PassRequestDTO passRequestDTO);

    // Обновление существующей сущности на основе DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePassFromDTO(PassUpdateDTO passUpdateDTO, @MappingTarget Pass existingPass);
}


