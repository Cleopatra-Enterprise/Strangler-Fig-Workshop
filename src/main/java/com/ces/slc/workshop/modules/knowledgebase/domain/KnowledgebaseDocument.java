package com.ces.slc.workshop.modules.knowledgebase.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.security.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class KnowledgebaseDocument extends Document<KnowledgebaseComponent> {

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<KnowledgebaseLevel> levels = new HashSet<>();

    protected KnowledgebaseDocument() {
        // For JPA
    }

    public KnowledgebaseDocument(String description, User author, LocalDateTime creationTimestamp) {
        super(description, author, creationTimestamp);
    }

    public Set<KnowledgebaseLevel> getLevels() {
        return levels;
    }

    public void addLevel(KnowledgebaseLevel level) {
        levels.add(level);
    }

    public void removeLevel(KnowledgebaseLevel level) {
        levels.remove(level);
    }

    @Override
    protected void initialize() {
        // TODO: Implement this method
    }
}
