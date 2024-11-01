package com.backendkiss.backendkiss.services;

import java.beans.Transient;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backendkiss.backendkiss.entity.Media;
import com.backendkiss.backendkiss.repository.MediaRepository;

import jakarta.transaction.Transactional;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Transactional
    public boolean createMedia(Media media) {
        try {
            mediaRepository.save(media);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Media> getAllMedia() {
        try {
            return mediaRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

}
