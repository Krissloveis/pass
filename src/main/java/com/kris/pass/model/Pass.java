package com.kris.pass.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;


import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Accessors(chain = false)
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pass {

    @Id
    @EqualsAndHashCode.Include
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

    @Column(name ="begin_time" )
    private LocalDateTime beginTime;

    @Column(name ="end_time" )
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id")
    private Zone zone;
}

