# Utiliser une image OpenJDK pour Java 21
FROM eclipse-temurin:21-jre

# Configurer le dossier de travail
WORKDIR /app

# Copier le fichier JAR généré par Spring Boot dans le conteneur
COPY target/kiss-back.jar app.jar

# Exposer le port de l'application
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
