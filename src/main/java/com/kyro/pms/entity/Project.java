package com.kyro.pms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pId;

    @NotNull(message="Project name cannot be null")
    private String pname;

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    private List<Feature> features;

    public void addFeature(Feature f){
        if(features == null)
            features = new ArrayList<>();
        features.add(f);
        f.setProject(this);
    }
}
