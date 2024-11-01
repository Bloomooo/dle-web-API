package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Media;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MediaTest {

    private Media media;

    @BeforeEach
    void setUp() {
        media = new Media();
    }

    @Test
    void testId() {
        media.setId(1);
        assertThat(media.getId()).isEqualTo(1);
    }

    @Test
    void testName() {
        media.setName("Test Media");
        assertThat(media.getName()).isEqualTo("Test Media");
    }

    @Test
    void testImg() {
        media.setImg("image_url.png");
        assertThat(media.getImg()).isEqualTo("image_url.png");
    }
}
