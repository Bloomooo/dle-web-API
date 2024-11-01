package com.backendkiss.backendkiss.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.entity.Media;
import com.backendkiss.backendkiss.repository.MediaRepository;

@Component
public class MediaQueryResolver {

    private final MediaRepository mediaRepository;

    public MediaQueryResolver(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }
}
