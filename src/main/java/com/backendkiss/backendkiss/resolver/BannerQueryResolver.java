package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.repository.BannerRepository;

@Component
public class BannerQueryResolver {

    private final BannerRepository bannerRepository;

    public BannerQueryResolver(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }
}
