package com.backendkiss.backendkiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.entity.Media;
import com.backendkiss.backendkiss.entity.Skin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CharactersTest {

    private Characters characters;

    @BeforeEach
    void setUp() {
        characters = new Characters();
    }

    @Test
    void testId() {
        int id = 1;
        characters.setId(id);
        assertThat(characters.getId()).isEqualTo(id);
    }

    @Test
    void testMedia() {
        Media media = new Media();
        characters.setMedia(media);
        assertThat(characters.getMedia()).isEqualTo(media);
    }

    @Test
    void testSkins() {
        List<Skin> skinList = new ArrayList<>();
        characters.setSkins(skinList);
        assertThat(characters.getSkins()).isEqualTo(skinList);
    }
}
