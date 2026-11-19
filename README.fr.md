# NovelBlog API

API REST sécurisée développée avec Spring Boot pour la gestion d'un blog (articles, catégories, commentaires et gestion des utilisateurs).

## 🛠️ Stack Technique

* **Langage :** Java 17+
* **Framework :** Spring Boot 3
* **Sécurité :** Spring Security (Authentification Stateless via JWT)
* **Base de données :** Spring Data JPA / Hibernate, H2 (profil `dev`)
* **Outils :** Maven, Git

## ✨ Fonctionnalités (V1)

* **Authentification & Autorisation :** Inscription, connexion avec génération de token JWT, contrôle d'accès basé sur les rôles (`USER`, `ADMIN`).
* **Gestion des Articles (`Posts`) :** Consultation publique, création/modification/suppression réservées aux administrateurs.
* **Catégories & Commentaires :** Organisation des articles par thèmes et système de commentaires liés aux utilisateurs.
* **Initialisation automatique :** Création sécurisée d'un compte administrateur au démarrage et chargement de données de test en mode `dev`.

## 🚀 Installation et Lancement

### 1. Prérequis
* JDK 17 ou supérieur
* Maven 3.8+

### 2. Variables d'environnement requises
L'application applique un démarrage sécurisé (*fail-fast*) et nécessite les variables suivantes avant le lancement :

```bash
JWT_SECRET=votre_cle_secrete_en_base64_assez_longue
ADMIN_USERNAME=adminTest
ADMIN_PASSWORD=votre_mot_de_passe_12_caracteres_min
ADMIN_EMAIL=admin@example.com
```

### 3. Démarrer l'application (Profil Dev)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## 📌 Principaux Endpoints

| Méthode | Endpoint | Accès | Description |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Public | Authentification et récupération du token JWT |
| `POST` | `/api/users` | Public | Création d'un compte utilisateur |
| `GET` | `/api/posts` | Public | Récupérer la liste des articles |
| `POST` | `/api/posts` | `ADMIN` | Créer un nouvel article |
| `POST` | `/api/comments` | Authentifié | Ajouter un commentaire |

## 🔒 Choix d'architecture & Limites connues (V1)

* **Cycle de vie des tokens :** Pour cette V1, l'authentification repose sur un JWT simple à durée de vie courte sans système de *refresh token* ni liste noire côté serveur, afin de conserver une architecture *stateless* légère. La mise en place d'un mécanisme de *refresh tokens* et d'invalidation lors du changement de mot de passe est prévue pour la V2.
* **Base de données & Console H2 :** La base embarquée H2 et sa console web sont activées uniquement pour le profil `dev` et devront être désactivées/remplacées par une base de données relationnelle persistante (ex. PostgreSQL ou MySQL) en production.
* **Propriétés JPA :** Les options `spring.jpa.show-sql=true` et `spring.jpa.hibernate.ddl-auto=update` sont configurées pour faciliter le développement et devront être revues (ex. passage à `validate` avec un outil de migration comme Flyway ou Liquibase) avant la mise en production.
* **Configuration CORS :** Actuellement configurée pour le développement frontend local (`http://localhost:3000`), elle devra être mise à jour pour autoriser uniquement l'URL réelle du frontend en production.
