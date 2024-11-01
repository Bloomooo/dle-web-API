package com.backendkiss.backendkiss.controllers.skin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import com.backendkiss.backendkiss.entity.Skin;
import com.backendkiss.backendkiss.resolver.SkinQueryResolver;

@Controller
public class SkinGraphQLController {

    private final SkinQueryResolver skinQueryResolver;

    public SkinGraphQLController(SkinQueryResolver skinQueryResolver) {
        this.skinQueryResolver = skinQueryResolver;
    }

    @Query
    public List<Skin> getAllSkins() {
        return skinQueryResolver.getAllSkins();
    }
}
