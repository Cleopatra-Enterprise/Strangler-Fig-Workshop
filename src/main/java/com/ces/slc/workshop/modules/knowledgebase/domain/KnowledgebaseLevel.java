package com.ces.slc.workshop.modules.knowledgebase.domain;

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
public class KnowledgebaseLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private KnowledgebaseLevel parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<KnowledgebaseLevel> children = new HashSet<>();

    protected KnowledgebaseLevel() {
        // For JPA
    }

    public KnowledgebaseLevel(String name, KnowledgebaseLevel parent) {
        this.name = name;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KnowledgebaseLevel getParent() {
        return parent;
    }

    public Set<KnowledgebaseLevel> getChildren() {
        return children;
    }

    public void addChild(KnowledgebaseLevel child) {
        children.add(child);
    }

    public void removeChild(KnowledgebaseLevel child) {
        children.remove(child);
    }
}
