package com.kyro.pms.service;

import com.kyro.pms.entity.Project;
import com.kyro.pms.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project createProject(Project project){
      return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project getProjectById(Integer pId){
        return projectRepository.findById(pId).get();
    }
}
