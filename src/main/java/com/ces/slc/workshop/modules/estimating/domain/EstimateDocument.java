package com.ces.slc.workshop.modules.estimating.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.ces.slc.workshop.modules.core.domain.Document;
import com.ces.slc.workshop.security.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class EstimateDocument extends Document<EstimateComponent> {

    @OneToMany(mappedBy = "document", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<EstimateComponent> topLevelComponents = new HashSet<>();

    protected EstimateDocument() {
        // For JPA
    }

    public EstimateDocument(String name, User author, LocalDateTime creationTimestamp) {
        super(name, author, creationTimestamp);
    }

    @Override
    public Set<EstimateComponent> getTopLevelComponents() {
        return Set.copyOf(topLevelComponents);
    }

    @Override
    public void addTopLevelComponent(EstimateComponent component) {
        topLevelComponents.add(component);
    }

    @Override
    public void removeTopLevelComponent(EstimateComponent component) {
        topLevelComponents.remove(component);
    }
}
