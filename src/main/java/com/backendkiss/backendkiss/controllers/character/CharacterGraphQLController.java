package com.backendkiss.backendkiss.controllers.character;

import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.resolver.CharacterQueryResolver;

@Controller
public class CharacterGraphQLController {

    private final CharacterQueryResolver lootableQueryResolver;

    public CharacterGraphQLController(CharacterQueryResolver lootableQueryResolver) {
        this.lootableQueryResolver = lootableQueryResolver;
    }

    @QueryMapping()
    public List<Characters> getAllCharacters() {
        return lootableQueryResolver.getAllCharacters();
    }
}
