```mysql
use hightechstore;

CREATE TABLE Marque
(
    uuid_marque CHAR(60) default (uuid()) not null,
    libelle     VARCHAR(50),
    PRIMARY KEY (uuid_marque)
);

CREATE TABLE Categorie
(
    uuid_categorie CHAR(60) default (uuid()) not null,
    libelle        VARCHAR(50),
    PRIMARY KEY (uuid_categorie)
);

CREATE TABLE Article
(
    uuid_article   CHAR(60) default (uuid()) not null,
    titre          VARCHAR(50),
    libelle        VARCHAR(50),
    prix           FLOAT,
    uuid_categorie CHAR(60)                  NOT NULL,
    uuid_marque    CHAR(60)                  NOT NULL,
    url_image      TEXT,
    quantite       INTEGER,
    PRIMARY KEY (uuid_article),
    FOREIGN KEY (uuid_categorie) REFERENCES Categorie (uuid_categorie),
    FOREIGN KEY (uuid_marque) REFERENCES Marque (uuid_marque)
);

CREATE TABLE Utilisateur
(
    uuid_utilisateur CHAR(60) default (uuid()) not null,
    mail             VARCHAR(50),
    mdp              VARCHAR(50),
    role_libelle     VARCHAR(50)               NOT NULL,
    PRIMARY KEY (uuid_utilisateur)
);

CREATE TABLE Commande
(
    uuid_commande    CHAR(60) not null,
    date_commande    DATE,
    uuid_utilisateur CHAR(60) NOT NULL,
    prix             FLOAT,
    PRIMARY KEY (uuid_commande),
    FOREIGN KEY (uuid_utilisateur) REFERENCES Utilisateur (uuid_utilisateur)
);

CREATE TABLE Contient_commande
(
    uuid_contient_commane CHAR(60) default (uuid()) not null,
    uuid_commande          CHAR(60),
    contenue               JSON,
    PRIMARY KEY (uuid_contient_commande),
    FOREIGN KEY (uuid_commande) REFERENCES Commande (uuid_commande)
);
```
