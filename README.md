# Banque API

Ce projet expose une API REST de gestion de comptes bancaires avec Spring Boot.

## Composants

- `Compte` représente un compte avec son identifiant, son solde, sa date de création et son type.
- `TypeCompte` définit les types `COURANT` et `EPARGNE`.
- `CompteRepository` fournit les opérations de lecture et d’écriture avec Spring Data JPA.
- `CompteController` expose les opérations CRUD sous `/banque/comptes` en JSON et XML.
- `Lab8Application` démarre l’application et crée trois comptes de démonstration au premier lancement.
- H2 stocke les données en mémoire et propose une console de consultation.
- Swagger UI documente et permet d’essayer les routes de l’API.

## Lancement

Avec Java 21 et Maven, démarrer le projet depuis ce dossier :

```bash
mvn spring-boot:run
```

L’application écoute sur `http://localhost:8082`.

## Liens utiles

- API : `http://localhost:8082/banque/comptes`
- Documentation Swagger : `http://localhost:8082/swagger-ui.html`
- Console H2 : `http://localhost:8082/h2-console`

Pour la console H2, utiliser l’URL JDBC `jdbc:h2:mem:banque`, l’utilisateur `sa` et un mot de passe vide.
