package com.kris.pass.DTO;

import com.kris.pass.model.PassStatus;
import com.kris.pass.model.Zone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PassResponseDTO {
    private Long passID;
    private String personName;
    private LocalDateTime addedAt;
    private LocalDateTime updatedAt;
    private String personSurname;
    private PassStatus status;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private Zone zone;

}
