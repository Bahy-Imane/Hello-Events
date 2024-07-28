# Hello-Events

Hello-Events est une application web de réservation de billets pour des événements, développée avec Spring Boot 3.3.2.

## Aperçu du Projet

Cette application permet aux utilisateurs de parcourir des événements, de réserver des billets et de gérer leurs comptes. Elle offre également une interface d'administration pour la gestion des événements et des comptes utilisateurs.

### Fonctionnalités

#### Pour les Clients :
- Affichage des événements sur la page d'accueil
- Inscription et authentification des utilisateurs
- Gestion du profil utilisateur
- Recherche et filtrage des événements
- Réservation de billets
- Consultation des informations sur l'équipe et les valeurs de l'entreprise
- Soumission de formulaire de contact

#### Pour les Administrateurs :
- Tableau de bord avec les activités des clients et les données des événements
- Gestion des comptes utilisateurs
- Gestion des événements (opérations CRUD)
- Traitement des soumissions du formulaire de contact

## Technologies Utilisées

- Spring Boot 3.3.2
- Spring MVC
- Spring Security 6.3.1 avec JWT
- Spring Data JPA
- JUnit pour les tests
- Pattern DTO
- Lombok pour réduire le code répétitif
- MySQL 8.3.0 comme base de données
- Docker pour la conteneurisation

## Démarrage

### Prérequis

- JDK 17 ou ultérieur
- Maven 3.6.3 ou ultérieur
- MySQL 8.3.0
- Docker (pour la conteneurisation)

### Configuration et Installation

1. Clonez le dépôt : https://github.com/Bahy-Imane/Hello-Events.git
2. Naviguez vers le répertoire du projet : cd Hello-Events
3. Configurez la connexion à la base de données dans `src/main/resources/application.properties`.
4. Construisez le projet : mvn clean install
5. Lancez l'application : mvn spring-boot:run
