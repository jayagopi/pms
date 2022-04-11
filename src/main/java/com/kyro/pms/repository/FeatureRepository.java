package com.kyro.pms.repository;

import com.kyro.pms.entity.Feature;
import com.kyro.pms.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature,Integer> {
    public Feature findByfIdAndProject(Integer fId, Project p);
}
