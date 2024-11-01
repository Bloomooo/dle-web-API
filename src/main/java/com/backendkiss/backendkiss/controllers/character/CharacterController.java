package com.backendkiss.backendkiss.controllers.character;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.entity.ResponseBody;
import com.backendkiss.backendkiss.services.CharacterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/character")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/createCharacter")
    public ResponseEntity<ResponseBody> createCharacter(@RequestBody Characters character) {
        try {
            if (character.getCardSplashartBytes() != null) {
                byte[] cardData = character.getCardSplashartBytes();
                Blob cardBlob = new SerialBlob(cardData);
                character.setCardSplashartBlob(cardBlob);
            }

            if (character.getBannerSplashartBytes() != null) {
                byte[] bannerData = character.getBannerSplashartBytes();
                Blob bannerBlob = new SerialBlob(bannerData);
                character.setBannerSplashartBlob(bannerBlob);
            }
            boolean isCreated = characterService.createCharacter(character);
            return isCreated ? new ResponseEntity<>(new ResponseBody("C'est OK", true, character), HttpStatus.OK)
                    : new ResponseEntity<>(new ResponseBody("C'est pas OK", false, character), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody("Erreur interne du serveur : " + e.getMessage(), false, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/editCharacter")
    public ResponseEntity<ResponseBody> editCharacter(@RequestBody Characters character) {
        try {
            if (character.getCardSplashartBytes() != null) {
                byte[] cardData = character.getCardSplashartBytes();
                Blob cardBlob = new SerialBlob(cardData);
                character.setCardSplashartBlob(cardBlob);
            }

            if (character.getBannerSplashartBytes() != null) {
                byte[] bannerData = character.getBannerSplashartBytes();
                Blob bannerBlob = new SerialBlob(bannerData);
                character.setBannerSplashartBlob(bannerBlob);
            }
            boolean isEdited = characterService.editCharacter(character);
            return isEdited ? new ResponseEntity<>(new ResponseBody("C'est OK", true, character), HttpStatus.OK)
                    : new ResponseEntity<>(new ResponseBody("C'est pas OK", false, character), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody("Erreur interne du serveur : " + e.getMessage(), false, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("deleteCharacter")
    public ResponseEntity<ResponseBody> deleteCharacter(@RequestBody Characters character) {
        try {
            boolean isDeleted = characterService.deleteCharacter(character);
            return isDeleted ? new ResponseEntity<>(new ResponseBody("C'est OK", true, character), HttpStatus.OK)
                    : new ResponseEntity<>(new ResponseBody("C'est pas OK", false, character), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody("Erreur interne du serveur : " + e.getMessage(), false, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
