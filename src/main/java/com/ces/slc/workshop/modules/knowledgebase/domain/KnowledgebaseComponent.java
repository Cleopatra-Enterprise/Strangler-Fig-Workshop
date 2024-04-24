package com.ces.slc.workshop.modules.knowledgebase.domain;

import com.ces.slc.workshop.modules.core.domain.DocumentComponent;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class KnowledgebaseComponent extends DocumentComponent {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private KnowledgebaseLevel level;

    protected KnowledgebaseComponent() {
        // For JPA
    }

    public KnowledgebaseComponent(KnowledgebaseLevel level) {
        this.level = level;
    }

    public KnowledgebaseLevel getLevel() {
        return level;
    }
}
