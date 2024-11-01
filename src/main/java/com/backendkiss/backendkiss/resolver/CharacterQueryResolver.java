package com.backendkiss.backendkiss.resolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.repository.LootableRepository;

@Component
public class CharacterQueryResolver {
    private final LootableRepository lootableRepository;

    public CharacterQueryResolver(LootableRepository lootableRepository) {
        this.lootableRepository = lootableRepository;
    }

    public List<Characters> getAllCharacters() {
        return lootableRepository.findAllByType(Characters.class).stream()
                .map(lootable -> convertCharacterBlobs((Characters) lootable))
                .collect(Collectors.toList());
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
