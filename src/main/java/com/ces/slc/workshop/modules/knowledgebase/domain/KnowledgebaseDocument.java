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

    @OneToMany(mappedBy = "document", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<KnowledgebaseComponent> topLevelComponents = new HashSet<>();

    protected KnowledgebaseDocument() {
        // For JPA
    }

    public KnowledgebaseDocument(String description, User author, LocalDateTime creationTimestamp) {
        super(description, author, creationTimestamp);
    }

    @Override
    public Set<KnowledgebaseComponent> getTopLevelComponents() {
        return Set.copyOf(topLevelComponents);
    }

    @Override
    public void addTopLevelComponent(KnowledgebaseComponent component) {
        topLevelComponents.add(component);
    }

    @Override
    public void removeTopLevelComponent(KnowledgebaseComponent component) {
        topLevelComponents.remove(component);
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
}
