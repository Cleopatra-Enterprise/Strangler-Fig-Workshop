package com.ces.slc.workshop.estimating.application;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseComponent;
import com.ces.slc.workshop.shared.application.document.DocumentComponentRepository;

@Repository
public interface EstimateComponentRepository extends DocumentComponentRepository<EstimateComponent> {

    @Query("""
    select distinct estimateComponent
    from EstimateComponent estimateComponent
    where estimateComponent.knowledgebaseComponent = :knowledgebaseComponent
    and estimateComponent.knowledgebaseComponent is not null
    """)
    Set<EstimateComponent> getReferencingEstimateComponents(KnowledgebaseComponent knowledgebaseComponent);
}
