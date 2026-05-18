# Cahier de Texte Pédagogique

## 🎯 Description

Application desktop Java professionnelle pour la gestion numérique du cahier de texte avec génération automatique de fiches de suivi pédagogique.

## 🚀 Caractéristiques

### Authentification & Sécurité
- ✅ Authentification sécurisée avec BCrypt
- ✅ Gestion des rôles (Chef de département, Enseignant, Responsable de classe)
- ✅ Validation des sessions
- ✅ Logout sécurisé

### Fonctionnalités Principales

#### Chef de Département
- Gestion des enseignants (CRUD complet)
- Gestion des responsables de classe
- Gestion des classes et matières
- Assignation des cours
- Validation des comptes
- Génération des fiches PDF
- Dashboard statistique
- Consultation globale des séances

#### Enseignant
- Visualisation de ses cours
- Ajout/modification/suppression de séances
- Gestion des contenus et observations
- Consultation de l'historique
- Génération de rapports
- Export PDF

#### Responsable de Classe
- Consultation du cahier de texte
- Validation/rejet des séances
- Ajout de commentaires
- Suivi de la progression
- Filtrage des données

### Interface Utilisateur
- Design moderne et professionnel
- Interface responsive
- Animations fluides
- Thème moderne avec palette couleurs professionnelle
- Dashboard administratif
- Tableaux stylisés
- Icônes professionnelles

### Génération de Documents
- Génération PDF avec iText
- Fiches de suivi pédagogique
- Rapports d'enseignant
- Statistiques formatées
- Mise en page professionnelle

## 📋 Prérequis

- Java 17 ou supérieur
- Maven 3.8+
- MySQL 8.0+
- IDE : IntelliJ IDEA ou VS Code

## 🛠️ Installation

### 1. Cloner le repository
```bash
git clone https://github.com/bacar221/cahier-texte-pedagogique.git
cd cahier-texte-pedagogique
```

### 2. Configuration MySQL

**Créer la base de données :**
```bash
mysql -u root -p < src/main/resources/database/schema.sql
```

### 3. Configuration de l'application

Modifier `src/main/resources/config/database.properties` :
```properties
db.host=localhost
db.port=3306
db.name=cahier_texte_pedagogique
db.user=root
db.password=votre_mot_de_passe
```

### 4. Build Maven
```bash
mvn clean install
```

### 5. Lancer l'application
```bash
mvn javafx:run
```

Ou avec JAR :
```bash
mvn clean package
java -jar target/cahier-texte-pedagogique-1.0.0.jar
```

## 👤 Comptes de Test

### Chef de Département
- Email : `admin@school.com`
- Mot de passe : `admin123`

### Enseignant
- Email : `prof@school.com`
- Mot de passe : `prof123`

### Responsable de Classe
- Email : `responsable@school.com`
- Mot de passe : `resp123`

## 📁 Structure du Projet

```
src/main/java/com/cahiertexte/
├── app/                  # Point d'entrée de l'application
├── model/                # Modèles de données
├── controller/           # Contrôleurs
├── service/              # Logique métier
├── repository/           # Accès aux données
├── dao/                  # Data Access Objects
├── database/             # Gestion base de données
├── utils/                # Utilitaires
├── security/             # Sécurité et authentification
├── view/                 # Vues et composants UI
└── config/               # Configuration

src/main/resources/
├── fxml/                 # Fichiers FXML (vues)
├── css/                  # Feuilles de style
├── config/               # Fichiers de configuration
└── database/             # Scripts SQL
```

## 🏗️ Architecture

- **Pattern MVC** : Séparation claire entre Modèle, Vue et Contrôleur
- **Principes SOLID** : Code maintenable et extensible
- **Programmation OOP** : Classes bien structurées et hiérarchisées
- **Couche service** : Logique métier centralisée
- **Repository Pattern** : Abstraction de l'accès aux données

## 📊 Base de Données

### Tables principales
- `utilisateurs` : Gestion des utilisateurs
- `enseignants` : Profils des enseignants
- `responsables_classe` : Responsables de classes
- `classes` : Informations des classes
- `cours` : Définition des cours
- `seances` : Séances pédagogiques
- `validations` : Historique des validations

## 🧪 Tests Unitaires

```bash
mvn test
```

Tests disponibles pour :
- Authentification
- CRUD utilisateurs
- CRUD séances
- Génération PDF
- Validations métier

## 📦 Dépendances Principales

- **JavaFX 21.0.1** : Interface graphique
- **MySQL Connector 8.0.33** : Base de données
- **iText 7.2.5** : Génération PDF
- **JBCrypt 0.4** : Hashage sécurisé
- **SLF4J/Logback** : Logging
- **JUnit 5** : Framework de test

## 🔒 Sécurité

- Mots de passe hashés avec BCrypt
- Validation des entrées utilisateur
- Gestion des sessions
- Requêtes préparées (protection SQL Injection)
- Contrôle d'accès par rôle

## 📝 Documentation

- Voir `docs/ARCHITECTURE.md` pour détails architecture
- Voir `docs/USER_GUIDE.md` pour guide utilisateur
- Voir `docs/DEVELOPER_GUIDE.md` pour guide développeur

## 🤝 Contribution

Pull requests bienvenues. Pour les modifications importantes, ouvrir d'abord une issue.

## 📄 Licence

Projet académique - Tous droits réservés © 2026

## 👨‍💻 Auteur

bacar221

## 📞 Support

Pour toute question ou problème, créer une issue sur GitHub.

---

**Développé pour une soutenance universitaire professionnelle**