package com.kyro.pms.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TaskRelation")
public class TaskRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tRelId;

    private Integer taskId;

    private Integer taskDepId;

    private Integer featureId;

    private Integer projectId;
}
