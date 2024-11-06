# Étape 1 : Utiliser une image de base Java
FROM openjdk:17-jdk-alpine

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans le conteneur
COPY target/kaddem-1.0.jar /app/kaddem.jar

# Étape 4 : Exposer le port que l'application utilise
EXPOSE 8089

# Étape 5 : Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "kaddem.jar"]