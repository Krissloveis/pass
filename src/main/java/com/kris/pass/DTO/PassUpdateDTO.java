package com.kris.pass.DTO;

import com.kris.pass.model.PassStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PassUpdateDTO {

    private Long passID;
    private String personName;
    private String personSurname;
    private PassStatus status;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;



}
