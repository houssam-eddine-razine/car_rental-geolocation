# Application de géolocalisation

Dans le monde en constante évolution de l'ingénierie et de la technologie, il est essentiel pour les étudiants en sciences et en ingénierie de relever des défis concrets. Ce projet de fin d'année représente une opportunité précieuse de développer nos compétences techniques, de renforcer notre capacité à travailler en équipe et de découvrir les exigences du marché.

Pour cela, notre projet de fin d'année consiste en la réalisation d'une application de gestion de flotte et de géolocalisation des véhicules.

## Table des matières

- [Introduction générale](#introduction-générale)
- [Architecture de l’Application](#Architecture-de-l-Application)
- [Docker](#Docker)

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
