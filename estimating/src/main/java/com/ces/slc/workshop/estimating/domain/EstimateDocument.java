package com.ces.slc.workshop.estimating.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.ces.slc.workshop.shared.domain.Document;
import com.ces.slc.workshop.security.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class EstimateDocument extends Document<EstimateComponent> {

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<EstimateComponent> components = new HashSet<>();

    protected EstimateDocument() {
        // For JPA
    }

    public EstimateDocument(String name, User author, LocalDateTime creationTimestamp) {
        super(name, author, creationTimestamp);
    }

    @Override
    public Set<EstimateComponent> getComponents() {
        return Set.copyOf(components);
    }

    @Override
    public void addComponent(EstimateComponent component) {
        components.add(component);
    }

    @Override
    public void removeComponent(EstimateComponent component) {
        components.remove(component);
    }
}
