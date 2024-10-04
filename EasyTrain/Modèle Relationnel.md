---
title: Modèle Relationnel

---

# Mission EasyTrain

Elijah Dabo BTS SIO SLAM 04/10/2024

## Modèle Relationnel
### Utilisateur

- **Champs** :
  - `id` : int(3)
  - `login` : varchar(20)
  - `mdp` : varchar(256)
  - `nom` : varchar(30)
  - `prenom` : varchar(30)
  - `date_embauche` : datetime
  - `role` : enum('ADMIN', 'EMPLOYE')

- **Clé primaire** : `id`
- **Clé étrangère** : -
- **Champ unique** : `login`

---

### Arrêt

- **Champs** :
  - `id` : int(3)
  - `nom` : varchar(30)

- **Clé primaire** : `id`
- **Clé étrangère** : -
- **Champ unique** : -

---

### Trajet

- **Champs** :
  - `code` : varchar(30)
  - `temps_depart` : datetime
  - `temps_arrivee` : datetime
  - `arret_depart_id` : int(3)
  - `arret_arrivee_id` : int(3)

- **Clé primaire** : `code`
- **Clé étrangère** : 
  - `arret_depart_id` référence à `Arrêt(id)`
  - `arret_arrivee_id` référence à `Arrêt(id)`
- **Champ unique** : -

