package com.kyro.pms.repository;

import com.kyro.pms.entity.Feature;
import com.kyro.pms.entity.Project;
import com.kyro.pms.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    public Task findBytIdAndFeature(Integer tId, Feature feature);
}
