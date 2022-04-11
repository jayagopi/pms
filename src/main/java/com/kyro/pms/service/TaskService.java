package com.kyro.pms.service;

import com.kyro.pms.entity.Feature;
import com.kyro.pms.entity.Project;
import com.kyro.pms.entity.Task;
import com.kyro.pms.repository.ProjectRepository;
import com.kyro.pms.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public Task createTask(Task task){
      return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer tId, Feature feature){
        return taskRepository.findBytIdAndFeature(tId,feature);
    }
}
