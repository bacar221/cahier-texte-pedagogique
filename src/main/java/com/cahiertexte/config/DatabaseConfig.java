package com.cahiertexte.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Configuration et gestion de la base de données MySQL
 */
public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    
    // Configuration de la base de données
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "cahier_texte_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    
    private static Connection connection;

    /**
     * Initialise la base de données
     */
    public static void initialize() {
        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("Driver MySQL chargé");
            
            // Créer la base de données si elle n'existe pas
            createDatabase();
            
            // Se connecter à la base de données
            connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);
            logger.info("Connexion à la base de données établie");
            
            // Créer les tables
            createTables();
            
        } catch (ClassNotFoundException e) {
            logger.error("Driver MySQL non trouvé", e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            logger.error("Erreur de connexion à la base de données", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Crée la base de données
     */
    private static void createDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            String createDbQuery = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + 
                                   " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
            stmt.executeUpdate(createDbQuery);
            logger.info("Base de données créée ou vérifiée");
        }
    }

    /**
     * Crée les tables de la base de données
     */
    private static void createTables() throws SQLException {
        String[] tables = {
            createUtilisateursTable(),
            createEnseignantsTable(),
            createResponsablesClasseTable(),
            createClassesTable(),
            createMatieresTable(),
            createCoursTable(),
            createSeancesTable(),
            createValidationsTable()
        };
        
        try (Statement stmt = connection.createStatement()) {
            for (String table : tables) {
                stmt.execute(table);
            }
            logger.info("Tables créées ou vérifiées");
        }
    }

    private static String createUtilisateursTable() {
        return "CREATE TABLE IF NOT EXISTS utilisateurs (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "nom VARCHAR(100) NOT NULL," +
            "prenom VARCHAR(100) NOT NULL," +
            "email VARCHAR(255) UNIQUE NOT NULL," +
            "mot_de_passe VARCHAR(255) NOT NULL," +
            "role ENUM('CHEF_DEPARTEMENT', 'ENSEIGNANT', 'RESPONSABLE_CLASSE') NOT NULL," +
            "statut ENUM('ACTIF', 'INACTIF', 'EN_ATTENTE') DEFAULT 'EN_ATTENTE'," +
            "date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "date_modification TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
            ")";
    }

    private static String createEnseignantsTable() {
        return "CREATE TABLE IF NOT EXISTS enseignants (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "utilisateur_id INT NOT NULL UNIQUE," +
            "telephone VARCHAR(20)," +
            "specialite VARCHAR(100)," +
            "diplome VARCHAR(255)," +
            "date_embauche DATE," +
            "FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE" +
            ")";
    }

    private static String createResponsablesClasseTable() {
        return "CREATE TABLE IF NOT EXISTS responsables_classe (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "utilisateur_id INT NOT NULL UNIQUE," +
            "classe_id INT," +
            "telephone VARCHAR(20)," +
            "FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id) ON DELETE CASCADE" +
            ")";
    }

    private static String createClassesTable() {
        return "CREATE TABLE IF NOT EXISTS classes (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "nom_classe VARCHAR(100) NOT NULL UNIQUE," +
            "niveau VARCHAR(50) NOT NULL," +
            "responsable_id INT," +
            "effectif INT DEFAULT 0," +
            "date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY (responsable_id) REFERENCES responsables_classe(id)" +
            ")";
    }

    private static String createMatieresTable() {
        return "CREATE TABLE IF NOT EXISTS matieres (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "nom VARCHAR(100) NOT NULL UNIQUE," +
            "code VARCHAR(20) UNIQUE," +
            "description TEXT," +
            "coefficient INT DEFAULT 1" +
            ")";
    }

    private static String createCoursTable() {
        return "CREATE TABLE IF NOT EXISTS cours (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "matiere_id INT NOT NULL," +
            "enseignant_id INT NOT NULL," +
            "classe_id INT NOT NULL," +
            "jour_semaine VARCHAR(20)," +
            "heure_debut TIME," +
            "duree_minutes INT," +
            "date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY (matiere_id) REFERENCES matieres(id)," +
            "FOREIGN KEY (enseignant_id) REFERENCES enseignants(id)," +
            "FOREIGN KEY (classe_id) REFERENCES classes(id)" +
            ")";
    }

    private static String createSeancesTable() {
        return "CREATE TABLE IF NOT EXISTS seances (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "cours_id INT NOT NULL," +
            "date_seance DATE NOT NULL," +
            "heure_debut TIME NOT NULL," +
            "duree_minutes INT NOT NULL," +
            "contenu TEXT NOT NULL," +
            "observations TEXT," +
            "statut_validation ENUM('EN_ATTENTE', 'VALIDEE', 'REJETEE') DEFAULT 'EN_ATTENTE'," +
            "date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "date_validation TIMESTAMP NULL," +
            "FOREIGN KEY (cours_id) REFERENCES cours(id) ON DELETE CASCADE" +
            ")";
    }

    private static String createValidationsTable() {
        return "CREATE TABLE IF NOT EXISTS validations (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "seance_id INT NOT NULL UNIQUE," +
            "responsable_id INT NOT NULL," +
            "commentaire TEXT," +
            "decision ENUM('APPROUVEE', 'REJETEE') NOT NULL," +
            "date_validation TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY (seance_id) REFERENCES seances(id) ON DELETE CASCADE," +
            "FOREIGN KEY (responsable_id) REFERENCES responsables_classe(id)" +
            ")";
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}