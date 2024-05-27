package com.ces.slc.workshop.modules.estimating.application;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.modules.core.application.document.DocumentRepository;
import com.ces.slc.workshop.modules.estimating.domain.EstimateComponent;
import com.ces.slc.workshop.modules.estimating.domain.EstimateDocument;
import com.ces.slc.workshop.modules.knowledgebase.domain.KnowledgebaseDocument;

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
}
