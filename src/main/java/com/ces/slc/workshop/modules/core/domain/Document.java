package com.ces.slc.workshop.modules.core.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.ces.slc.workshop.security.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Document<C extends DocumentComponent> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "document", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<C> topLevelComponents = new HashSet<>();

    @OneToMany(mappedBy = "document", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BreakdownStructure> breakdownStructures = new HashSet<>();

    private String description;
    private String name;
    private LocalDateTime creationTimestamp;

    protected Document() {
        // For JPA
    }

    protected Document(String name, User author, LocalDateTime creationTimestamp) {
        this.name = name;
        this.author = author;
        this.creationTimestamp = creationTimestamp;
    }

    public long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Set<C> getTopLevelComponents() {
        return topLevelComponents;
    }

    public void addTopLevelComponent(C component) {
        topLevelComponents.add(component);
    }

    public void removeTopLevelComponent(C component) {
        topLevelComponents.remove(component);
    }

    public Set<BreakdownStructure> getBreakdownStructures() {
        return breakdownStructures;
    }

    public void addBreakdownStructure(BreakdownStructure breakdownStructure) {
        breakdownStructures.add(breakdownStructure);
    }

    public void removeBreakdownStructure(BreakdownStructure breakdownStructure) {
        breakdownStructures.remove(breakdownStructure);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @PrePersist
    protected abstract void initialize();
}
