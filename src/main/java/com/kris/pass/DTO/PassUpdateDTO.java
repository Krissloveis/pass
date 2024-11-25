package com.kris.pass.DTO;

import com.kris.pass.model.PassStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassUpdateDTO {

    private Long passId;
    private String personName;
    private String personSurname;
    private PassStatus status;


}
