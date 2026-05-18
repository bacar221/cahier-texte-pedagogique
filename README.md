# 📚 Cahier de Texte Pédagogique

## Application de Gestion Numérique du Cahier de Texte

### 🎯 Objectif
Application desktop profesionnelle pour la gestion numérique du cahier de texte pédagogique avec génération automatique de fiches de suivi pédagogique.

### 🛠️ Technologies Utilisées
- **Langage** : Java 17+
- **Interface** : JavaFX 21 + FXML + CSS
- **Base de données** : MySQL 8.0
- **Gestion des dépendances** : Maven
- **PDF** : iText 7
- **Sécurité** : BCrypt
- **Tests** : JUnit 4
- **Logs** : SLF4J + Logback

### 👥 Rôles Utilisateurs
1. **Chef de Département** : Gestion complète
2. **Enseignant** : Gestion des séances
3. **Responsable de Classe** : Validation des séances

### 🚀 Installation

#### Prérequis
- JDK 17+
- Maven 3.8+
- MySQL 8.0+

#### Étapes
1. Cloner le repository
```bash
git clone https://github.com/bacar221/cahier-texte-pedagogique.git
cd cahier-texte-pedagogique
```

2. Configurer la base de données
```bash
mysql -u root -p < database/schema.sql
mysql -u root -p < database/data.sql
```

3. Mettre à jour la configuration
```
Modifier : src/main/resources/database.properties
```

4. Compiler le projet
```bash
mvn clean compile
```

5. Lancer l'application
```bash
mvn javafx:run
```

### 📋 Comptes de Test

**Chef de Département**
- Email : admin@school.com
- Mot de passe : admin123

**Enseignant**
- Email : prof@school.com
- Mot de passe : prof123

**Responsable de Classe**
- Email : responsable@school.com
- Mot de passe : resp123

### 📂 Structure du Projet
```
src/main/java/com/pedagogie/
├── app/                    # Point d'entrée
├── controller/             # Contrôleurs MVC
├── model/                  # Modèles de données
├── service/                # Couche métier
├── repository/             # Accès aux données
├── dao/                    # Data Access Objects
├── database/               # Gestion BD
├── security/               # Authentification
├── utils/                  # Utilitaires
├── config/                 # Configuration
└── exception/              # Exceptions personnalisées
```

### ✨ Fonctionnalités Principales

#### Chef de Département
- ✅ Gestion des utilisateurs
- ✅ Gestion des classes et matières
- ✅ Assigner des cours
- ✅ Valider les comptes
- ✅ Voir les statistiques globales
- ✅ Générer les fiches PDF
- ✅ Consulter toutes les séances

#### Enseignant
- ✅ Voir ses cours
- ✅ Ajouter une séance
- ✅ Modifier une séance non validée
- ✅ Consulter l'historique
- ✅ Générer ses rapports PDF
- ✅ Export des données

#### Responsable de Classe
- ✅ Consulter le cahier de texte
- ✅ Valider ou rejeter une séance
- ✅ Ajouter des commentaires
- ✅ Voir l'avancement du programme

### 📊 Fonctionnalités Avancées
- 🔐 Authentification sécurisée (BCrypt)
- 🎨 Interface moderne et responsive
- 📄 Génération PDF automatique
- 🔍 Recherche dynamique et filtrage
- 📈 Tableaux avec pagination et tri
- 🔔 Notifications et alertes
- 📝 Logs système complets
- 🌙 Support pour mode sombre
- 📊 Graphiques statistiques
- 💾 Sauvegarde automatique

### 🧪 Tests Unitaires
```bash
mvn test
```

### 📦 Générer le JAR exécutable
```bash
mvn package
java -jar target/cahier-texte-pedagogique-1.0.0.jar
```

### 📖 Documentation
- `docs/ARCHITECTURE.md` : Architecture du projet
- `docs/API.md` : Documentation API
- `docs/MANUAL.md` : Manuel utilisateur
- `docs/DIAGRAMMES.md` : Diagrammes UML

### 👨‍💼 Auteur
Bacar221

### 📜 Licence
MIT License

### 📞 Support
Pour toute question ou problème, ouvrir une issue sur GitHub.
