package com.ces.slc.workshop.estimating.application;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.shared.application.document.DocumentRepository;

@Repository
public interface EstimateDocumentRepository extends DocumentRepository<EstimateDocument, EstimateComponent> {

    @Query("""
    select distinct knowledgebase
    from EstimateDocument estimate
    join EstimateComponent component on component.document = estimate
    join KnowledgebaseDocument knowledgebase on component.knowledgebaseComponent.document = knowledgebase
    where estimate = :estimateDocument
    and component.knowledgebaseComponent is not null
    """)
    Set<KnowledgebaseDocument> getKnowledgebaseReferences(EstimateDocument estimateDocument);

    @Query("""
    select component
    from EstimateDocument document
    join EstimateComponent component on component.document = document
    where document = :document and component.parent is null
    """)
    Set<EstimateComponent> getTopLevelComponents(EstimateDocument document);

    @Query("""
    select distinct estimate
    from EstimateDocument estimate
    join EstimateComponent estimateComponent on estimateComponent.document = estimate
    join estimateComponent.knowledgebaseComponent knowledgebaseComponent
    where knowledgebaseComponent.document = :knowledgebaseDocument
    and estimateComponent.knowledgebaseComponent is not null
    """)
    Set<EstimateDocument> getReferencingEstimateDocuments(KnowledgebaseDocument knowledgebaseDocument);
}
