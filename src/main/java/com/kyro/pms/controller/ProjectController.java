package com.kyro.pms.controller;

import com.kyro.pms.entity.Feature;
import com.kyro.pms.entity.Project;
import com.kyro.pms.service.FeatureService;
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

    @Autowired
    FeatureService featureService;

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

    @PostMapping("/{pId}/feature/")
    public ResponseEntity<Object> createFeature(@PathVariable Integer pId, @RequestBody Feature feature){
      ResponseEntity<Object> getProject= getProjectById(pId);
      if(getProject.getStatusCode().equals(HttpStatus.FOUND)){
          feature.setProject((Project) getProject.getBody());
          featureService.createFeature(feature);
          return ResponseEntity.status(HttpStatus.CREATED).body(feature);
      }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
    }

    @GetMapping("/{pId}/feature/")
    public ResponseEntity<Object> getAllFeaturesForProject(@PathVariable Integer pId){
        ResponseEntity<Object> getProject= getProjectById(pId);
        if(getProject.getStatusCode().equals(HttpStatus.FOUND)){
           Project project = (Project) getProject.getBody();
           return ResponseEntity.status(HttpStatus.OK).body(project.getFeatures());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
    }

    @GetMapping("/{pId}/feature/{fId}")
    public ResponseEntity<Object> getAllFeaturesForProject(@PathVariable Integer pId, @PathVariable Integer fId){
        log.info("GetAllFeaturesForProject with pId " + pId + " fId " + fId);
        ResponseEntity<Object> getProject= getProjectById(pId);
        if(getProject.getStatusCode().equals(HttpStatus.FOUND)){
            Feature feature = null;
            feature = featureService.getFeatureByProject((Project) getProject.getBody(),fId);
            if(feature == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feature not found");
            return ResponseEntity.status(HttpStatus.FOUND).body(feature);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
    }

}
