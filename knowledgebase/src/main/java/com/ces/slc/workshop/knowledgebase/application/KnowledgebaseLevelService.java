package com.ces.slc.workshop.knowledgebase.application;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseLevel;
import com.ces.slc.workshop.knowledgebase.domain.KnowledgebaseDocument;
import com.ces.slc.workshop.knowledgebase.web.dto.KnowledgebaseLevelDto;

@Service
public class KnowledgebaseLevelService {

    private static final String UNASSIGNED_LEVEL_NAME = "Unassigned";

    private final KnowledgebaseLevelRepository knowledgebaseLevelRepository;
    private final KnowledgebaseDocumentRepository knowledgebaseDocumentRepository;

    public KnowledgebaseLevelService(KnowledgebaseLevelRepository knowledgebaseLevelRepository,
            KnowledgebaseDocumentRepository knowledgebaseDocumentRepository) {
        this.knowledgebaseLevelRepository = knowledgebaseLevelRepository;
        this.knowledgebaseDocumentRepository = knowledgebaseDocumentRepository;
    }

    public Optional<KnowledgebaseLevel> getKnowledgebaseLevel(KnowledgebaseDocument document, Long id) {
        return knowledgebaseLevelRepository.findByDocumentAndId(document, id);
    }

    public KnowledgebaseLevel createUnassignedKnowledgebaseLevel(KnowledgebaseDocument knowledgebase) {
        KnowledgebaseLevel level = new KnowledgebaseLevel(UNASSIGNED_LEVEL_NAME, null);
        knowledgebase.addLevel(level);
        knowledgebase.setDefaultLevel(level);
        knowledgebaseLevelRepository.save(level);
        return level;
    }

    public KnowledgebaseLevel createKnowledgebaseLevel(KnowledgebaseDocument document, KnowledgebaseLevelDto levelDto) {
        KnowledgebaseLevel parent = null;
        if (levelDto.parent() != null) {
            parent = getKnowledgebaseLevel(document, levelDto.parent().id())
                    .orElseThrow(() -> new IllegalArgumentException("Parent level not found"));
        }
        if (parent != null && Objects.equals(parent.getId(), document.getDefaultLevel().getId())) {
            throw new IllegalArgumentException("Parent level cannot be the default (unassigned) level");
        }
        KnowledgebaseLevel level = new KnowledgebaseLevel(levelDto.name(), parent);
        knowledgebaseLevelRepository.save(level);
        return level;
    }

    public Optional<KnowledgebaseLevel> updateKnowledgebaseLevel(KnowledgebaseDocument document, Long id, KnowledgebaseLevelDto levelDto) {
        return getKnowledgebaseLevel(document, id).map(level -> {
            level.setName(levelDto.name());
            knowledgebaseLevelRepository.save(level);
            return level;
        });
    }

    public Optional<KnowledgebaseLevel> deleteKnowledgebaseLevel(KnowledgebaseDocument document, Long id) {
        return getKnowledgebaseLevel(document, id).map(level -> {
            if (Objects.equals(level.getId(), document.getDefaultLevel().getId())) {
                throw new IllegalArgumentException("Cannot delete the default (unassigned) level");
            }
            document.removeLevel(level);
            knowledgebaseDocumentRepository.save(document);
            knowledgebaseLevelRepository.delete(level);
            return level;
        });
    }
}
