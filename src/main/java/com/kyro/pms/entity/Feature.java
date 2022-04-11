package com.kyro.pms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fId;

    @NotNull(message = "Feature name cannot be null")
    private String fname;

    private Integer fpoints;

    @ManyToOne
    @JoinColumn(name="pId")
    private Project project;

    @OneToMany(mappedBy = "feature")
    @JsonIgnore
    private List<Task> taskList;

    public void addTask(Task task){
        if(taskList == null)
            taskList = new ArrayList<>();
        taskList.add(task);
    }
}
