### Elijah Dabo
### BTS SIO SLAM
### 24/01/25

# Projet : Gestion des Arrêts Ferroviaires

## Compte Rendu
Je travaille sur un projet pour gérer des arrêts ferroviaires et voici les fichiers principaux que j'ai ajoutés :

- **TypeArret.java** : définit les types d’arrêts, comme TERMINUS ou INTERMÉDIAIRE.
- **EasyTrainDAO.java** : modifié pour gérer la persistance des données liées aux arrêts.
- **AjoutArretView.fxml** : interface utilisateur pour ajouter de nouveaux arrêts.
- **AjoutArretController.java** : contient la logique pour gérer les interactions de l’interface.
- **TestArretDAO.java** : utilisé pour effectuer des tests unitaires et vérifier le bon fonctionnement.

L’application permet :
- D’ajouter des arrêts avec validation du **nom unique**.
- D’afficher un **message de confirmation** si tout est correct.

J'ai aussi mis à jour la base de données pour ajouter une colonne **type_arret**, afin de stocker le type de chaque arrêt (TERMINUS ou INTERMÉDIAIRE).

- ALTER TABLE arret ADD COLUMN type_arret ENUM('TERMINUS', 'INTERMEDIAIRE') NOT NULL DEFAULT 'INTERMEDIAIRE'

---

## Problèmes actuels

Malheureusement, le projet ne fonctionne pas encore complètement à cause de plusieurs problèmes :

1. **Classe Arret.java**  
   - Elle n’a pas été mise à jour pour intégrer la gestion de TypeArret.  

2. **module-info.java**  
   - Les nouvelles dépendances nécessaires pour JavaFX ne sont pas encore ajoutées.  

3. **Base de données**  
   - La configuration de la colonne **type_arret** n’est pas correcte, ce qui cause des erreurs.  

4. **Références circulaires**  
   - Certaines classes se référencent entre elles de manière incorrecte, ce qui provoque des erreurs de compilation.
