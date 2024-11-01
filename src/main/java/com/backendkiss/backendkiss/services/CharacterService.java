package com.backendkiss.backendkiss.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.entity.Lootable;
import com.backendkiss.backendkiss.entity.Media;
import com.backendkiss.backendkiss.entity.Skin;
import com.backendkiss.backendkiss.repository.LootableRepository;
import com.backendkiss.backendkiss.repository.MediaRepository;

import jakarta.transaction.Transactional;

@Service
public class CharacterService {
    private final LootableRepository lootableRepository;
    private final MediaRepository mediaRepository;
    private final ModelMapper modelMapper;

    public CharacterService(LootableRepository lootableRepository, MediaRepository mediaRepository) {
        this.lootableRepository = lootableRepository;
        this.mediaRepository = mediaRepository;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public boolean createCharacter(Characters character) {
        try {
            Media mediaFounded = mediaRepository.findByName(character.getMedia().getName());
            if (mediaFounded == null) {
                return false;
            }
            if (character.getSkin() != null) {
                Skin skinFounded = (Skin) lootableRepository.findByNameAndType(character.getSkin().getName(),
                        Skin.class);

                if (skinFounded != null) {
                    character.setSkins(skinFounded);
                }
            }

            character.setMedia(mediaFounded);
            lootableRepository.save(character);
            this.convertCharacterBlobs(character);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean editCharacter(Characters character) {
        Boolean isEdited = true;
        try {
            Optional<Lootable> characterFounded = lootableRepository.findById(character.getId());
            if (characterFounded.isPresent()) {
                Lootable existingCharacter = characterFounded.get();
                Media mediaFounded = mediaRepository.findByName(character.getMedia().getName());
                if (mediaFounded == null) {
                    return false;
                }
                character.setMedia(mediaFounded);
                modelMapper.map(character, existingCharacter);
                lootableRepository.save(existingCharacter);
                this.convertCharacterBlobs(character);
                return true;
            }
        } catch (Exception e) {
            isEdited = false;
        }
        return isEdited;
    }

    @Transactional
    public boolean deleteCharacter(Characters character) {
        try {
            lootableRepository.deleteById(character.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public List<Lootable> getLootable() {
        return lootableRepository.findAll();
    }

    private Characters convertCharacterBlobs(Characters character) {
        character.setCardSplashartBytes(blobToBytes(character.getCardSplashartBlob()));
        character.setBannerSplashartBytes(blobToBytes(character.getBannerSplashartBlob()));

        character.setCardSplashartString(convertBlobToBase64(character.getCardSplashartBytes()));
        character.setBannerSplashartString(convertBlobToBase64(character.getBannerSplashartBytes()));
        return character;
    }

    private byte[] blobToBytes(Blob blob) {
        if (blob == null) {
            return null;
        }
        try (InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertBlobToBase64(byte[] blobBytes) {
        if (blobBytes == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(blobBytes);
    }
}
