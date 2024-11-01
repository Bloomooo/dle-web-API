package com.backendkiss.backendkiss.controllers.skin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendkiss.backendkiss.entity.ResponseBody;
import com.backendkiss.backendkiss.entity.Skin;
import com.backendkiss.backendkiss.services.SkinService;

@RestController
@RequestMapping("/skin")
public class SkinController {
    private SkinService skinService;

    public SkinController(SkinService skinService) {
        this.skinService = skinService;
    }

    @PostMapping("/createSkin")
    public ResponseEntity<ResponseBody> createSkin(@RequestBody Skin skin) {
        try {
            boolean isCreated = skinService.createSkin(skin);
            return isCreated ? new ResponseEntity<>(new ResponseBody("C'est OK", true, skin), HttpStatus.OK)
                    : new ResponseEntity<>(new ResponseBody("C'est pas OK", false, skin), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseBody("Erreur interne du serveur : " + e.getMessage(), false, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
