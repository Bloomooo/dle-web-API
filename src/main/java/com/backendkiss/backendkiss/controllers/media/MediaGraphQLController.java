package com.backendkiss.backendkiss.controllers.media;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import com.backendkiss.backendkiss.entity.Media;
import com.backendkiss.backendkiss.resolver.MediaQueryResolver;

@Controller
public class MediaGraphQLController {

    private final MediaQueryResolver mediaQueryResolver;

    public MediaGraphQLController(MediaQueryResolver mediaQueryResolver) {
        this.mediaQueryResolver = mediaQueryResolver;
    }

    @Query
    public List<Media> getAllMedia() {
        return mediaQueryResolver.getAllMedia();
    }
}
