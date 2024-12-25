package com.kris.pass.mapper;


import com.kris.pass.DTO.PassRequestDTO;
import com.kris.pass.DTO.PassResponseDTO;
import com.kris.pass.DTO.PassUpdateDTO;
import com.kris.pass.model.Pass;
import com.kris.pass.model.Zone;
import com.kris.pass.repository.ZoneRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PassMapper {

    @Autowired
    private ZoneRepository zoneRepository;

    public abstract PassResponseDTO convertToDTO(Pass pass);

    @Mapping(target = "passID", ignore = true)
    @Mapping(target = "addedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(source = "zoneId", target = "zone", qualifiedByName = "zoneById")
    public abstract Pass convertToPass(PassRequestDTO passRequestDTO);

    // Обновление существующей сущности на основе DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updatePassFromDTO(PassUpdateDTO passUpdateDTO, @MappingTarget Pass existingPass);


    @Named("zoneById")
    Zone zoneById(Long zoneId) {
        if (zoneId == null) {
            return null;
        }
        // Берём Zone из базы данных
        return zoneRepository.findById(zoneId)
                .orElseThrow(() -> new IllegalArgumentException("Zone not found with id: " + zoneId));
    }

}


