package com.kyro.pms.repository;

import com.kyro.pms.entity.Feature;
import com.kyro.pms.entity.Task;
import com.kyro.pms.entity.TaskRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRelationRepository extends JpaRepository<TaskRelation,Integer> {
    public List<TaskRelation> findByfeatureIdAndProjectId(Integer featureId, Integer projectId);
}
