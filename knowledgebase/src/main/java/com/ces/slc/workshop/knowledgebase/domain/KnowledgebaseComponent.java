package com.ces.slc.workshop.knowledgebase.domain;

import com.ces.slc.workshop.shared.domain.DocumentComponent;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class KnowledgebaseComponent extends DocumentComponent {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private KnowledgebaseLevel level;

    protected KnowledgebaseComponent() {
        // For JPA
    }

    public KnowledgebaseComponent(KnowledgebaseDocument document) {
        this(document, document.getDefaultLevel());
    }

    public KnowledgebaseComponent(KnowledgebaseDocument document, KnowledgebaseLevel level) {
        super(document);
        this.level = level;
    }

    public KnowledgebaseLevel getLevel() {
        return level;
    }

    public void setLevel(KnowledgebaseLevel knowledgebaseLevel) {
        this.level = knowledgebaseLevel;
    }

    public KnowledgebaseDocument getKnowledgebaseDocument() {
        return (KnowledgebaseDocument) super.getDocument();
    }
}
