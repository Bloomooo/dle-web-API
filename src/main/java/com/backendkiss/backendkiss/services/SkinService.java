package com.backendkiss.backendkiss.services;

import org.springframework.stereotype.Service;

import com.backendkiss.backendkiss.entity.Media;
import com.backendkiss.backendkiss.entity.Skin;
import com.backendkiss.backendkiss.repository.LootableRepository;
import com.backendkiss.backendkiss.repository.MediaRepository;

import jakarta.transaction.Transactional;

@Service
public class SkinService {

    private final LootableRepository lootableRepository;
    private final MediaRepository mediaRepository;

    public SkinService(LootableRepository lootableRepository, MediaRepository mediaRepository) {
        this.lootableRepository = lootableRepository;
        this.mediaRepository = mediaRepository;
    }

    @Transactional
    public boolean createSkin(Skin skin) {
        try {
            Media mediaFounded = mediaRepository.findByName(skin.getMedia().getName());
            if (mediaFounded == null) {
                return false;
            }
            skin.setMedia(mediaFounded);
            lootableRepository.save(skin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
