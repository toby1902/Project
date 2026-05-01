# Alpha Hotel

Application de gestion hoteliere developpee avec `Spring Boot 3`, `Java 17`, `PostgreSQL`, `Spring Security`, `JWT`, `Thymeleaf`, `Bootstrap 5` et `JavaMailSender`.

## 1. Fonctionnalites principales

- portail client avec accueil, galerie, a propos et formulaire de reservation
- recherche de chambres disponibles par date cote client et cote admin
- gestion des reservations : validation, modification, annulation
- check-in manuel et check-out manuel
- cloture automatique quotidienne des sejours echus
- paiements et calcul du reste a payer
- facture PDF
- gestion de stock PPN avec alerte critique
- notifications email :
  - nouvelle reservation vers l'admin
  - confirmation au client
  - rappel client la veille du depart
  - recapitulatif admin des departs du jour
  - notification admin en cas de cloture automatique

## 2. Prerequis

- `Java 17` ou version compatible superieure
- `Maven 3.9+`
- `PostgreSQL`
- acces SMTP `Brevo` si vous voulez envoyer de vrais emails

## 3. Configuration

Le fichier principal est :

- [application.properties](</c:/Users/lebit's/Desktop/JAVA WEB/src/main/resources/application.properties>)

Points a verifier :

- URL PostgreSQL
- identifiants PostgreSQL
- SMTP Brevo
- adresse expeditrice

Base attendue :

- `alpha_hotelDb`

Commande SQL de creation :

```sql
CREATE DATABASE "alpha_hotelDb";
```

## 4. Lancement du projet

Depuis le dossier `JAVA WEB` :

```powershell
mvn spring-boot:run
```

Compilation simple :

```powershell
mvn -q -DskipTests compile
```

Tests :

```powershell
mvn test
```

## 5. Comptes de demonstration

- `admin@alphahotel.com / Admin@123`
- `direction@alphahotel.com / Direction@123`

## 6. URLs utiles

### Interface web

- accueil client : `http://localhost:8080/`
- recherche disponibilites client : `http://localhost:8080/reservations/disponibilites`
- formulaire reservation : `http://localhost:8080/reservations/nouvelle`
- connexion admin : `http://localhost:8080/auth/login`
- dashboard admin : `http://localhost:8080/admin/dashboard`
- recherche disponibilites admin : `http://localhost:8080/admin/chambres/disponibles`

### API REST

- `POST /api/auth/login`
- `GET /api/chambres`
- `POST /api/reservations`
- `GET /api/admin/reservations`
- `POST /api/admin/reservations/{id}/valider`
- `POST /api/admin/reservations/{id}/check-in`
- `POST /api/admin/reservations/{id}/check-out`
- `POST /api/admin/reservations/{id}/paiements`
- `GET /api/admin/reservations/{id}/facture`
- `GET /api/admin/reservations/{id}/facture/pdf`
- `GET /api/admin/stocks`
- `GET /api/admin/stocks/alertes`
- `POST /api/admin/stocks`

## 7. Scenario de demonstration conseille

### Cote client

1. Ouvrir `http://localhost:8080/`
2. Aller sur `Disponibilites`
3. Saisir une date d'arrivee et une date de depart
4. Choisir une chambre disponible
5. Soumettre la reservation avec l'avance

### Cote admin

1. Ouvrir `http://localhost:8080/auth/login`
2. Se connecter avec le compte admin
3. Voir la reservation en attente
4. Cliquer sur `Valider`
5. Effectuer `Check-in`
6. Enregistrer un paiement complementaire
7. Generer la facture PDF
8. Effectuer `Check-out`

## 8. Automatisations metier

- rappel client la veille du depart : `18:00`
- recapitulatif admin des departs du jour : `07:00`
- cloture automatique des sejours echus : `00:10`

Ces horaires sont configurables dans `application.properties` :

```properties
app.scheduler.admin-depart-summary-cron=0 0 7 * * *
app.scheduler.depart-reminder-cron=0 0 18 * * *
app.scheduler.auto-checkout-cron=0 10 0 * * *
```

## 9. Structure du projet

- `controller` : MVC et API REST
- `service` : logique metier
- `repository` : acces JPA
- `model` : entites
- `dto` : objets de formulaire et de transfert
- `templates` : vues Thymeleaf
- `static` : CSS et images

## 10. Niveau actuel du projet

Le projet est deja tres complet pour un projet de stage :

- socle fonctionnel termine
- interface MVC exploitable
- securite admin
- envoi d'emails reels
- automatisations quotidiennes
- dashboard receptionnel

Ameliorations possibles a plus long terme :

- pagination
- audit des actions admin
- statistiques graphiques
- upload d'images de chambres
- historique detaille des mouvements de stock
- export Excel
- couverture de tests plus large
