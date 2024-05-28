package com.ces.slc.workshop.knowledgebase.application;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseLevel;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseDocument;

@Repository
public interface KnowledgebaseLevelRepository extends ListCrudRepository<KnowledgebaseLevel, Long> {

    @Query("""
    select l
    from KnowledgebaseLevel l
    join KnowledgebaseDocument d on l member of d.levels
    where d = :document and l.id = :id
    """)
    Optional<KnowledgebaseLevel> findByDocumentAndId(KnowledgebaseDocument document, Long id);
}
