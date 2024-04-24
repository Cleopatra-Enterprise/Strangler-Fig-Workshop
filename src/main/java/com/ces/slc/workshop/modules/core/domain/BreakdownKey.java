package com.ces.slc.workshop.modules.core.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class BreakdownKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BreakdownStructure structure;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private BreakdownKey parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<BreakdownKey> children = new HashSet<>();

    private String name;

    private String description;

    protected BreakdownKey() {
        // For JPA
    }

    public BreakdownKey(String name, BreakdownStructure structure) {
        this.name = name;
        this.structure = structure;
    }

    public BreakdownKey(String name, BreakdownKey parent) {
        this.name = name;
        this.parent = parent;
        this.structure = parent.getStructure();
    }

    public Long getId() {
        return id;
    }

    public BreakdownStructure getStructure() {
        return structure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BreakdownKey getParent() {
        return parent;
    }

    public Set<BreakdownKey> getChildren() {
        return children;
    }

    public void addChild(BreakdownKey child) {
        children.add(child);
    }

    public void removeChild(BreakdownKey child) {
        children.remove(child);
    }

}
