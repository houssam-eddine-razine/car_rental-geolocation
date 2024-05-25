# Application de géolocalisation

Dans le monde en constante évolution de l'ingénierie et de la technologie, il est essentiel pour les étudiants en sciences et en ingénierie de relever des défis concrets. Ce projet de fin d'année représente une opportunité précieuse de développer nos compétences techniques, de renforcer notre capacité à travailler en équipe et de découvrir les exigences du marché.

Pour cela, notre projet de fin d'année consiste en la réalisation d'une application de gestion de flotte et de géolocalisation des véhicules.

## Table des matières

- [Introduction générale](#introduction-générale)
- [Architecture de l’Application](#Architecture-de-l-Application)
- [Docker](#Docker)
- [Frontend](#Frontend)
- [Backend](#Backend)
- [Guide de démarrage](#Guide-de-démarrage)
- [Video Demonstration du Projet](#Video-Demonstration-du-Projet)
- [Contributeurs](#Contributeurs)

## Introduction générale

L'application de géolocalisation que nous envisageons de concevoir répond à un besoin urgent des entreprises : la gestion efficace de leur flotte de véhicules. En permettant le suivi en temps réel des véhicules, l'optimisation des itinéraires et la surveillance de l'état des véhicules, notre solution vise à apporter des gains significatifs en termes de productivité, de réduction des coûts et d'amélioration de la sécurité.

C’est dans ce cadre que s’inscrit la réalisation de notre projet qui consiste à développer une application de géolocalisation.

## Architecture de l’Application

![Architecture de l'application mobile](imagereadme/ANGUSPRING.png)

L'architecture de notre application repose sur une séparation claire entre le front-end et le back-end, facilitant ainsi le développement, le déploiement et la maintenance. Voici les principaux composants :

- **Front-End : Angular**
  - **Templates et Composants** : Angular utilise des templates HTML et des composants pour afficher le contenu et gérer la logique de l'interface utilisateur.
  - **Services** : Les services Angular partagent des données et des fonctionnalités entre les composants.
  - **HTTP Client** : `HttpClient` d'Angular envoie des requêtes HTTP au back-end.
  
- **Back-End : Spring Boot**
  - **Spring Rest Controller** : Les contrôleurs REST gèrent les requêtes HTTP entrantes et définissent les points de terminaison de l'API.
  - **Spring Boot** : Simplifie le développement et le déploiement des applications Spring avec des configurations par défaut.
  - **Spring Data JPA** : Gère les opérations CRUD en interagissant avec la base de données via des référentiels JPA.
  
- **Base de Données : MySQL**
  - **MySQL** : Stocke les données de l'application dans une structure relationnelle, gérée via Spring Data JPA.
    
## Docker

### Dockerfile pour Spring Boot

![Docker](imagereadme/5.jpg)

### Dockerfile pour Angular

![Docker](imagereadme/4.jpg)

### Build image

![Docker](imagereadme/2.jpg)

### docker-compose.yml

Pour gérer les conteneurs Spring Boot et Angular.

![Docker](imagereadme/3.jpg)

### Containers

![Docker](imagereadme/6.jpg)
![Docker](imagereadme/7.jpg)

## Frontend

les technologies utilisées 
- Angular
- Bootstrap

### Dependences

Liste des dependencies principales pour angular frontend.

```json
{
   "dependencies": {
    "@angular/animations": "^17.3.0",
    "@angular/common": "^17.3.0",
    "@angular/compiler": "^17.3.0",
    "@angular/core": "^17.3.0",
    "@angular/forms": "^17.3.0",
    "@angular/platform-browser": "^17.3.0",
    "@angular/platform-browser-dynamic": "^17.3.0",
    "@angular/platform-server": "^17.3.0",
    "@angular/router": "^17.3.0",
    "@angular/ssr": "^17.3.6",
    "express": "^4.18.2",
    "ng-zorro-antd": "^17.4.0",
    "rxjs": "~7.8.0",
    "tslib": "^2.3.0",
    "zone.js": "~0.14.3"
  },
  "devDependencies": {
    "@angular-devkit/build-angular": "^17.3.6",
    "@angular/cli": "^17.3.6",
    "@angular/compiler-cli": "^17.3.0",
    "@types/express": "^4.17.17",
    "@types/jasmine": "~5.1.0",
    "@types/node": "^18.18.0",
    "jasmine-core": "~5.1.0",
    "karma": "~6.4.0",
    "karma-chrome-launcher": "~3.2.0",
    "karma-coverage": "~2.2.0",
    "karma-jasmine": "~5.1.0",
    "karma-jasmine-html-reporter": "~2.1.0",
    "typescript": "~5.4.2"
  }
}
```

## Backend

les technologies utilisées

- Spring Boot
- MySQL

### Dependences

1. **Spring Boot Starter Test :**
   - Objectif : Fournit les dépendances nécessaires pour écrire et exécuter des tests unitaires et d'intégration avec Spring Boot.
   
2. **Spring Boot Starter Security :**
   - Objectif : Fournit les dépendances pour intégrer Spring Security dans votre application Spring Boot. Spring Security est utilisé pour la sécurité et l'authentification des applications.
   
3. **Spring Boot Starter Data JPA :**
   - Objectif : Fournit les dépendances pour intégrer Spring Data JPA dans votre application Spring Boot. Spring Data JPA simplifie l'interaction avec les bases de données relationnelles en fournissant une couche d'abstraction au-dessus de JPA (Java Persistence API).
   
4. **Spring Boot Starter Web :**
   - Objectif : Fournit les dépendances pour créer des applications web avec Spring Boot. Il inclut des bibliothèques pour le développement web, telles que Spring MVC et Tomcat.
   
5. **JWT (JSON Web Token) :**
   - Objectif : Les dépendances liées à JWT sont utilisées pour la gestion des tokens JWT dans le cadre de l'authentification et de l'autorisation. Cela permet d'implémenter des mécanismes d'authentification basés sur des tokens.
   
6. **Spring Security Test :**
   - Objectif : Cette dépendance fournit les outils de test pour les applications sécurisées avec Spring Security. Elle est utilisée pour écrire et exécuter des tests pour les configurations de sécurité.
   
7. **PDFBox :**
   - Objectif : PDFBox est une bibliothèque Java pour travailler avec des fichiers PDF. Cette dépendance est utilisée pour la manipulation de fichiers PDF dans l'application.
   
8. **Commons Lang :**
   - Objectif : Commons Lang est une bibliothèque Apache Commons qui fournit des utilitaires pour travailler avec des chaînes, des nombres, des tableaux, etc. dans Java.
   
9. **MySQL Connector :**
   - Objectif : Cette dépendance fournit le pilote JDBC pour se connecter à une base de données MySQL à partir de l'application.
   
10. **Google Android JSON :**
    - Objectif : Cette dépendance fournit des classes pour travailler avec JSON dans le cadre du projet Android de Google.
Voici un exemple d'un extrait de fichier `pom.xml` utilisé dans ce projet :

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.2.5</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>car_rental</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>car_rental</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<!-- Les dépendances sont listées ici -->
	</dependencies>

	<build>
		<!-- Les plugins de construction sont listés ici -->
	</build>

</project>
```
## Guide de démarrage
Ce guide vous aidera à démarrer le projet localement en suivant les étapes ci-dessous.

### Installation des Dépendances Frontend (Angular)

Assurez-vous d'avoir Node.js installé sur votre système. Accédez au répertoire du projet frontend dans votre terminal et exécutez la commande suivante pour installer les dépendances :

```bash
npm install
```

### Démarrage du Serveur de Développement
Une fois l'installation des dépendances terminée, exécutez la commande suivante pour démarrer le serveur de développement Angular :

```bash
ng serve
```

### Accès à l'Application
Après avoir démarré le serveur, accédez à l'application dans votre navigateur en visitant l'URL suivante :

```arduino
http://localhost:4200
```

### Configuration de la Base de Données
Assurez-vous d'avoir MySQL installé et en cours d'exécution sur votre système. Configurez les détails de connexion à la base de données dans le fichier src/main/resources/application.properties de votre projet backend.

### Compilation et Exécution
Assurez-vous d'avoir Maven installé sur votre système. Accédez au répertoire du projet backend dans votre terminal et exécutez la commande suivante pour compiler et exécuter l'application Spring Boot :

```bash
mvn spring-boot:run
```
### Vérification
Une fois que l'application est démarrée, vous pouvez vérifier qu'elle fonctionne en accédant à l'URL suivante dans votre navigateur :

```arduino
http://localhost:8080
```
## Video Demonstration du Projet

## Contributeurs

Nous tenons à remercier les contributeurs suivants qui ont participé à ce projet :
 - Ayda Aboufaresse 
 - Houssameddine Razine
 - Khadija Ezzahidi
 - Ayoub Hakmaoui
 - Mohamed-elhabib Tablioua
