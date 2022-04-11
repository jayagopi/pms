package com.kyro.pms.service;

import com.kyro.pms.entity.Feature;
import com.kyro.pms.entity.Project;
import com.kyro.pms.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class FeatureService {

    @Autowired
    FeatureRepository featureRepository;

    public Feature createFeature(Feature feature){
      return featureRepository.save(feature);
    }

    public List<Feature> getAllFeatures(){
        return featureRepository.findAll();
    }

    public Feature getFeatureById(Integer fId){
        return featureRepository.findById(fId).get();
    }

    public Feature getFeatureByProject(Project project, Integer fId){
        return featureRepository.findByfIdAndProject(fId,project);
    }
}
