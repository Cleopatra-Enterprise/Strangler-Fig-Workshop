package com.ces.slc.workshop.modules.estimating.application;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.modules.core.application.document.DocumentComponentRepository;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;

@Repository
public interface EstimateComponentRepository extends ListCrudRepository<EstimateComponent, Long>, DocumentComponentRepository<EstimateComponent> {

}
