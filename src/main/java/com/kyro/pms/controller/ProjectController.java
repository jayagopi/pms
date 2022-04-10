package com.kyro.pms.controller;

import com.kyro.pms.entity.Project;
import com.kyro.pms.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/project")
@Slf4j
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/")
    public ResponseEntity<Object> createProject(@Valid @RequestBody Project project){
        Project p = null;

        log.info("Entered create Project");

        try {
            p = projectService.createProject(project);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problem in creation of Project");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects(){
       return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @GetMapping("/{pId}")
    public ResponseEntity<Object> getProjectById(@PathVariable Integer pId){
       Project project = null;
       try{
         project = projectService.getProjectById(pId);
       }catch (NoSuchElementException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
       }
           return ResponseEntity.status(HttpStatus.FOUND).body(project);
    }

}
