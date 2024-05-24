package com.ces.slc.workshop.modules.knowledgebase.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.security.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class KnowledgebaseDocument extends Document<KnowledgebaseComponent> {

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<KnowledgebaseLevel> levels = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private KnowledgebaseLevel defaultLevel;

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, orphanRemoval = true)
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
        if (level.getParent() == null) {
            levels.add(level);
        }
    }

    public void removeLevel(KnowledgebaseLevel level) {
        levels.removeIf(l -> l.getId().equals(level.getId()));
    }

    public KnowledgebaseLevel getDefaultLevel() {
        return defaultLevel;
    }

    public void setDefaultLevel(KnowledgebaseLevel defaultLevel) {
        if (this.defaultLevel != null) {
            throw new IllegalStateException("The default level is already set");
        }
        if (defaultLevel.getParent() != null) {
            throw new IllegalArgumentException("The default level cannot have a parent");
        }
        this.levels.add(defaultLevel);
        this.defaultLevel = defaultLevel;
    }
}
