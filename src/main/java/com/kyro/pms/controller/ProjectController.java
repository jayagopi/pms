package com.kyro.pms.controller;

import com.kyro.pms.entity.Feature;
import com.kyro.pms.entity.Project;
import com.kyro.pms.entity.Task;
import com.kyro.pms.service.FeatureService;
import com.kyro.pms.service.ProjectService;
import com.kyro.pms.service.TaskService;
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

    @Autowired
    TaskService taskService;

    @PostMapping("/")
    public ResponseEntity<Object> createProject(@Valid @RequestBody Project project){
        Project p = null;

        log.info("Entered create Project");

        try {
            project.setPoints(0);
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
          feature.setFpoints(0);
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
    public ResponseEntity<Object> getFeatureById(@PathVariable Integer pId, @PathVariable Integer fId){
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

    @PostMapping("/{pId}/feature/{fId}/task/")
    public ResponseEntity<Object> createTask(@PathVariable Integer pId, @PathVariable Integer fId,@Valid @RequestBody Task task){
        ResponseEntity<Object> getFeature = getFeatureById(pId,fId);
        if(getFeature.getStatusCode().equals(HttpStatus.FOUND)){
            Feature feature = (Feature) getFeature.getBody();
            feature.setFpoints(feature.getFpoints()+task.getTpoints());
            Project project = feature.getProject();
            project.setPoints(project.getPoints()+task.getTpoints());
            task.setFeature(feature);
            taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(task);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((String)getFeature.getBody());
    }

    @GetMapping("/{pId}/feature/{fId}/task/")
    public ResponseEntity<Object> getAllTasksForProject(@PathVariable Integer pId, @PathVariable Integer fId){
        ResponseEntity<Object> getFeature = getFeatureById(pId,fId);
        if(getFeature.getStatusCode().equals(HttpStatus.FOUND)){
            Feature feature = (Feature) getFeature.getBody();
            return ResponseEntity.status(HttpStatus.OK).body(feature.getTaskList());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((String)getFeature.getBody());
    }

    @GetMapping("/{pId}/feature/{fId}/task/{tId}")
    public ResponseEntity<Object> getTaskById(@PathVariable Integer pId, @PathVariable Integer fId, @PathVariable Integer tId){
        ResponseEntity<Object> getFeature = getFeatureById(pId,fId);
        if(getFeature.getStatusCode().equals(HttpStatus.FOUND)){
            Feature feature = (Feature) getFeature.getBody();
            Task task = taskService.getTaskById(tId,feature);
            if(task == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
            else
                return ResponseEntity.status(HttpStatus.FOUND).body(task);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((String)getFeature.getBody());
    }

}
