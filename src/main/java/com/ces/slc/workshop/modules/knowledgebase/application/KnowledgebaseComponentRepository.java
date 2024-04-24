package com.ces.slc.workshop.modules.knowledgebase.application;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseComponent;

@Repository
public interface KnowledgebaseComponentRepository extends ListCrudRepository<KnowledgebaseComponent, Long> {

    @Query("""
    select distinct estimateComponent
    from EstimateComponent estimateComponent
    join estimateComponent.knowledgebaseComponent knowledgebaseComponent
    where knowledgebaseComponent = :knowledgebaseComponent
    """)
    Set<EstimateComponent> getReferencingEstimateComponents(KnowledgebaseComponent knowledgebaseComponent);
}
