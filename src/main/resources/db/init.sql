-- Database initialization script
-- Create database
CREATE DATABASE IF NOT EXISTS cahier_texte_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cahier_texte_db;

-- Table users
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role ENUM('CHEF_DEPARTEMENT', 'ENSEIGNANT', 'RESPONSABLE_CLASSE') NOT NULL,
    statut ENUM('ACTIF', 'INACTIF', 'EN_ATTENTE') DEFAULT 'EN_ATTENTE',
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modification TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email),
    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table classes
CREATE TABLE IF NOT EXISTS classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_classe VARCHAR(100) NOT NULL,
    niveau VARCHAR(50) NOT NULL,
    responsable_id INT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (responsable_id) REFERENCES users(id) ON DELETE SET NULL,
    UNIQUE KEY unique_classe (nom_classe, niveau),
    INDEX idx_responsable (responsable_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table subjects
CREATE TABLE IF NOT EXISTS matieres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_matiere VARCHAR(100) NOT NULL,
    description TEXT,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_matiere (nom_matiere)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table courses
CREATE TABLE IF NOT EXISTS cours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_cours VARCHAR(150) NOT NULL,
    enseignant_id INT NOT NULL,
    classe_id INT NOT NULL,
    matiere_id INT NOT NULL,
    volume_horaire INT DEFAULT 0,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (enseignant_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (classe_id) REFERENCES classes(id) ON DELETE CASCADE,
    FOREIGN KEY (matiere_id) REFERENCES matieres(id) ON DELETE CASCADE,
    INDEX idx_enseignant (enseignant_id),
    INDEX idx_classe (classe_id),
    INDEX idx_matiere (matiere_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table sessions
CREATE TABLE IF NOT EXISTS seances (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cours_id INT NOT NULL,
    date_seance DATE NOT NULL,
    heure_debut TIME NOT NULL,
    duree_minutes INT NOT NULL,
    contenu TEXT NOT NULL,
    observations TEXT,
    statut_validation ENUM('EN_ATTENTE', 'VALIDEE', 'REJETEE') DEFAULT 'EN_ATTENTE',
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_modification TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (cours_id) REFERENCES cours(id) ON DELETE CASCADE,
    INDEX idx_cours (cours_id),
    INDEX idx_date (date_seance),
    INDEX idx_statut (statut_validation)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Table validations
CREATE TABLE IF NOT EXISTS validations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seance_id INT NOT NULL,
    responsable_id INT NOT NULL,
    statut_validation ENUM('VALIDEE', 'REJETEE') NOT NULL,
    commentaire TEXT,
    date_validation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (seance_id) REFERENCES seances(id) ON DELETE CASCADE,
    FOREIGN KEY (responsable_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_seance (seance_id),
    INDEX idx_responsable (responsable_id),
    UNIQUE KEY unique_validation (seance_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert default users
INSERT INTO users (nom, prenom, email, mot_de_passe, role, statut) VALUES
('Admin', 'Système', 'admin@school.com', '$2a$10$OwX2n8PKl6m4L7v5LX5Q7uGxGJmjG8L2cV3Z5K0Z5K0Z5K0Z5K0Z5', 'CHEF_DEPARTEMENT', 'ACTIF'),
('Dupont', 'Jean', 'prof@school.com', '$2a$10$OwX2n8PKl6m4L7v5LX5Q7uGxGJmjG8L2cV3Z5K0Z5K0Z5K0Z5K0Z5', 'ENSEIGNANT', 'ACTIF'),
('Martin', 'Pierre', 'responsable@school.com', '$2a$10$OwX2n8PKl6m4L7v5LX5Q7uGxGJmjG8L2cV3Z5K0Z5K0Z5K0Z5K0Z5', 'RESPONSABLE_CLASSE', 'ACTIF');

-- Insert default subjects
INSERT INTO matieres (nom_matiere, description) VALUES
('Mathématiques', 'Cours de mathématiques'),
('Français', 'Cours de français'),
('Histoire-Géographie', 'Cours d\'histoire et géographie'),
('Sciences Physiques', 'Cours de physique et chimie'),
('Biologie', 'Cours de biologie'),
('Anglais', 'Cours d\'anglais');

-- Insert default classes
INSERT INTO classes (nom_classe, niveau, responsable_id) VALUES
('3ème A', 'Troisième', 3),
('3ème B', 'Troisième', 3),
('2nde A', 'Seconde', NULL),
('1ère S', 'Première', NULL);

-- Insert default courses
INSERT INTO cours (nom_cours, enseignant_id, classe_id, matiere_id, volume_horaire) VALUES
('Mathématiques 3ème A', 2, 1, 1, 36),
('Français 3ème B', 2, 2, 2, 36),
('Histoire 3ème A', 2, 1, 3, 18),
('Sciences 2nde A', 2, 3, 4, 36);

-- Insert sample sessions
INSERT INTO seances (cours_id, date_seance, heure_debut, duree_minutes, contenu, observations, statut_validation) VALUES
(1, '2026-05-18', '08:00:00', 60, 'Étude des équations du premier degré', 'Les élèves ont bien compris le concept', 'VALIDEE'),
(1, '2026-05-19', '08:00:00', 60, 'Exercices pratiques sur les équations', 'À continuer la semaine prochaine', 'EN_ATTENTE'),
(2, '2026-05-18', '09:00:00', 60, 'Grammaire : Les pronoms personnels', 'Bonne participation de la classe', 'VALIDEE'),
(3, '2026-05-20', '10:00:00', 60, 'Histoire : La Révolution Française', 'Intérêt marqué des élèves', 'EN_ATTENTE');