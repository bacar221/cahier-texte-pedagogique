package com.cahiertexte.app;

import javafx.application.JavaFXMod;
import javafx.stage.Stage;
import com.cahiertexte.config.DatabaseConfig;
import com.cahiertexte.controller.LoginController;
import com.cahiertexte.utils.SceneManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application principale du cahier de texte pédagogique
 * Point d'entrée de l'application JavaFX
 */
public class Application extends JavaFXMod {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        try {
            primaryStage = stage;
            
            // Initialiser la base de données
            DatabaseConfig.initialize();
            logger.info("Base de données initialisée");
            
            // Configurer la scène
            SceneManager.setPrimaryStage(stage);
            SceneManager.loadScene("login");
            
            // Configurer la fenêtre
            stage.setTitle("Cahier de Texte Pédagogique");
            stage.setWidth(1200);
            stage.setHeight(700);
            stage.setOnCloseRequest(e -> onApplicationClose());
            
            stage.show();
            logger.info("Application démarrée");
            
        } catch (Exception e) {
            logger.error("Erreur au démarrage de l'application", e);
            System.exit(1);
        }
    }

    /**
     * Gère la fermeture de l'application
     */
    private void onApplicationClose() {
        try {
            DatabaseConfig.closeConnection();
            logger.info("Connexion base de données fermée");
        } catch (Exception e) {
            logger.error("Erreur lors de la fermeture", e);
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}