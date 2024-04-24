package com.ces.slc.workshop.modules.knowledgebase.application;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseDocument;

@Repository
public interface KnowledgebaseDocumentRepository extends ListCrudRepository<KnowledgebaseDocument, Long> {

    @Query("""
    select distinct estimate
    from EstimateDocument estimate
    join EstimateComponent estimateComponent on estimateComponent.document = estimate
    join estimateComponent.knowledgebaseComponent knowledgebaseComponent
    where knowledgebaseComponent = :knowledgebaseDocument
    """)
    Set<EstimateDocument> getReferencingEstimateDocuments(KnowledgebaseDocument knowledgebaseDocument);

    @Query("""
    select distinct estimateComponent
    from EstimateComponent estimateComponent
    join estimateComponent.knowledgebaseComponent knowledgebaseComponent
    where knowledgebaseComponent.document = :knowledgebaseDocument
    """)
    Set<EstimateComponent> getReferencingEstimateComponents(KnowledgebaseDocument knowledgebaseDocument);
}
