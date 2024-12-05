package com.kris.pass.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;


import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Accessors(chain = false)
@ToString
@NoArgsConstructor
public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long passID;

    @Column(name = "name")
    private String personName;

    @Column(name = "surname")
    private String personSurname;

    @CreationTimestamp (source = SourceType.VM)
    @Column(name = "added_at")
    private LocalDateTime addedAt;

    @UpdateTimestamp (source = SourceType.VM)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated (EnumType.STRING)
    private PassStatus status = PassStatus.ACTIVE;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;


}

