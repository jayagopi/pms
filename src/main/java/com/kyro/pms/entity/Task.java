package com.kyro.pms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tId;

    @NotNull(message = "Task name cannot be null")
    private String tname;

    @Min(value = 1, message = "Task points should be greater than zero")
    @Max(value = 8, message = "Task points should be less than eight")
    private Integer tpoints;

    @ManyToOne
    @JoinColumn(name="fId")
    private Feature feature;
}
