package com.backendkiss.backendkiss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.GraphQlSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.entity.Skin;
import com.backendkiss.backendkiss.resolver.CharacterQueryResolver;
import com.backendkiss.backendkiss.resolver.MediaQueryResolver;
import com.backendkiss.backendkiss.resolver.SkinQueryResolver;
import com.backendkiss.backendkiss.resolver.UserQueryResolver;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.lang.NonNull;

@Configuration
public class Config implements WebMvcConfigurer {

    private final UserQueryResolver userQueryResolver;
    private final CharacterQueryResolver lootableQueryResolver;
    private final MediaQueryResolver mediaQueryResolver;
    private final SkinQueryResolver skinQueryResolver;

    public Config(UserQueryResolver userQueryResolver, CharacterQueryResolver lootableQueryResolver,
            MediaQueryResolver mediaQueryResolver, SkinQueryResolver skinQueryResolver) {
        this.userQueryResolver = userQueryResolver;
        this.lootableQueryResolver = lootableQueryResolver;
        this.mediaQueryResolver = mediaQueryResolver;
        this.skinQueryResolver = skinQueryResolver;
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true);
    }

    @Bean
    public GraphQlSource graphQlSource() throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = schemaParser
                .parse(new FileReader("src/main/resources/graphql/schema.graphqls"));

        RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("getUserById", env -> {
                            int id = env.getArgument("id");
                            return userQueryResolver.getUserById(id);
                        })
                        .dataFetcher("getAllUsers", env -> userQueryResolver.getAllUsers())
                        .dataFetcher("getAllCharacters", env -> lootableQueryResolver.getAllCharacters())
                        .dataFetcher("getAllMedia", env -> mediaQueryResolver.getAllMedia())
                        .dataFetcher("getAllSkins", env -> skinQueryResolver.getAllSkins()))
                .type(TypeRuntimeWiring.newTypeWiring("Lootable")
                        .typeResolver(env -> {
                            Object source = env.getObject();
                            if (source instanceof Characters) {
                                return GraphQLObjectType.newObject().name("Characters").build();
                            } else if (source instanceof Skin) {
                                return GraphQLObjectType.newObject().name("Skin").build();
                            }
                            return null;
                        }))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);

        return GraphQlSource.builder(schema).build();
    }
}
