package com.kyro.pms.service;

import com.kyro.pms.entity.Feature;
import com.kyro.pms.entity.Task;
import com.kyro.pms.entity.TaskRelation;
import com.kyro.pms.repository.TaskRelationRepository;
import com.kyro.pms.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskRelationService {

    @Autowired
    TaskRelationRepository taskRelationRepository;

    public TaskRelation createTaskRelation(TaskRelation taskRelation){
      return taskRelationRepository.save(taskRelation);
    }

    public List<TaskRelation> getAllTaskRelations(){
        return taskRelationRepository.findAll();
    }

    public TaskRelation getTaskRelationById(Integer tId){
        return taskRelationRepository.findById(tId).get();
    }

    public List<TaskRelation> getTaskRelationsByFeature(TaskRelation taskRelation){
        return taskRelationRepository.findByfeatureIdAndProjectId(
                taskRelation.getFeatureId(),
                taskRelation.getProjectId());
    }

    public boolean isRelationValid(TaskRelation taskRelation){
       List<TaskRelation> taskRelations = getTaskRelationsByFeature(taskRelation);
       Map<Integer,List<Integer>> relationMap = new HashMap<>();

       if(!taskRelations.isEmpty()){
         for(TaskRelation tRelation : taskRelations){
             Integer key = tRelation.getTaskId();
             Integer value = tRelation.getTaskDepId();
             List<Integer> values = null;
             if(relationMap.get(key) == null) {
                 values = new ArrayList<>();
             }else{
                 values = relationMap.get(key);
             }
             values.add(value);
             relationMap.put(key,values);
         }
         return isRelationPath(relationMap,taskRelation.getTaskId(),taskRelation.getTaskDepId()) &&
                isRelationPath(relationMap,taskRelation.getTaskDepId(),taskRelation.getTaskId());
       }
       return true;
    }

    public boolean isRelationPath(Map<Integer,List<Integer>> rMap, int source, int dest){
        List<Integer> dependencies = rMap.get(source);
        if(dependencies != null){
            for (Integer dep : dependencies) {
                if (dep == dest)
                    return false;
                if (!isRelationPath(rMap, dep, dest))
                    return false;
            }
        }
        return true;
    }
}
