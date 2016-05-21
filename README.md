# Projet - GeomPaint #
à rendre avant le 26/05/2016 à 12h00 - à faire par groupe de 4!

__________________________________________________________________________________________________________


On se propose de concevoir et implémenter un logiciel qui permet de visualiser et de manipuler des figures
colorées.

Les figures géométriques proposées sont : cercles, polygones quelconques, rectangles, triangles. Vous pouvez ajouter par la suite d’autres figures géométriques (ellipses, losanges, carrés, …)

On vous demande d’utiliser les notions de programmation objet efficacement pour ce projet. Par exemple,

• Les figures géométriques héritent d’une classe abstraite FigureGeom qui contient les propriétés
communes à l’ensemble des figures.
• Les classes UnPolygone et UnCercle héritent de la calsse FigureGeom
• On hérite de la classe UnPolygone, les classes UnTriangle et UnRectangle.
Les opérations qu’on peut réaliser sur les figures sont :
• Sélectionner une figure,
• Changer la couleur de la figure sélectionnée,
• Translater la figure sélectionnée,
• Modifier la forme et l’emplacement de la figure sélectionnée en redéfinissant les points de saisie de cette figure.

Il faut noter que Le nombre de points permettant de saisir une figure dépend de la figure : par exemple, il faut 4 points pour saisir un quadrilatère, mais seulement 2 pour saisir un rectangle (les deux extrémités d'une diagonale).

Chaque figure sera caractérisée par une méthode qui retourne le nombre de points de saisie. On mémorisera toutes les figures comme un tableau de points de mémorisation qui peuvent être différents des points de saisie :

• polygone : n points de saisie et n points de mémorisation
• rectangle : 2 points de saisie (extrémités d'une diagonale) et 4 points de mémorisation
• triangle : 3 points de saisie et 3 points de mémorisation
• cercle : 2 points de saisie (centre et point quelconque sur le cercle) et 2 points de mémorisation (centre et point à droite du centre sur le diamètre horizontal)

![Sans titre 3.jpg](https://bitbucket.org/repo/MBk6dA/images/365000253-Sans%20titre%203.jpg)

Pour dessiner une figure, l’utilisateur choisit une forme géométrique parmi les formes possibles, il place les points de sélection. Une fois tous les points sont placés, la figure s’affiche (il est possible d’envisager un affichage progressif).

Quand l’utilisateur clique sur le contour d’une figure, cette dernière sera sélectionnée (les points de sélection apparaîtront). L’utilisateur peut par la suite déplacer la figure, rendre la figure pleine ou non pleine, changer la couleur, dupliquer la figure, ou la supprimer.

**L’utilisation de l’approche MVC vue en cours (Observer/Observable) est fortement recommandée et sera
prise en compte lors de l’évaluation.

La qualité de l’interface utilisateur sera prise en compte lors de l’évaluation.**

## Travail demandé ##

On vous demande d’implémenter le projet IHM - Java selon les consignes données. Comme on ne vous détaille
pas la liste des classes, la phase de conception est très importante : il faut bien étudier la meilleure façon pour organiser vos classes et vos structures. Il faut faire un diagramme de classe assez complet.

Le programme doit être complet et fonctionnel. La qualité de la programmation est primordiale : votre projet doit être bien structuré, et les notions d’objets doivent être bien utilisées (héritage, classes abstraites, interfaces, etc.). La qualité de la conception et l’organisation du programme, le choix des structures de données et des algorithmes, le bon choix d’auditeurs, seront pris en compte et vont fortement influencer l’évaluation de votre projet. Une mauvaise organisation du projet ou une mauvaise programmation sera fortement sanctionnée. La qualité de l’interface graphique, l’interface utilisateur et l’ergonomie du logiciel seront prises en compte lors de l’évaluation.

C’est un travail de groupe. Il faut montrer que les différents membres du groupe sont effectivement impliqués dans le travail. Cela doit être visible, entre autres, dans la description des tâches réalisées dans le carnet de bord (Il faut tenir à jour un carnet de bord qui donne des informations détaillées sur le travail du groupe : comment vous avez organisé le travail, qui a fait quoi, sur quelle base vous avez fait la distribution du travail, etc.)

En résumé, c’est un projet à faire par groupe de 4 personnes. La qualité de la conception, de la programmation et de la gestion de l’interface graphique et l’interaction avec l’utilisateur seront prises en compte. Le travail et la synergie du groupe seront pris en compte dans l’évaluation.

Vous devez faire une démonstration de votre projet à votre enseignant pendant la dernière séance du cours.

## Modalités du retour du Projet ## 

**A rendre :**

• Le diagramme de classe est à rendre sur papier au plus tard le 23/05/2016 (dans le casier de votre enseignant au secrétariat)

• Un journal de bord (fichier texte .txt ou fichier .pdf) : qui donne des informations détaillées sur le travail du groupe : comment vous avez organisé le travail, qui a fait quoi, sur quelle base vous avez fait la distribution du travail.

• Les codes source de votre programme Ces fichiers doivent être rendus via l’ENT (ARCHE, site web du cours). Mettez l’ensemble des fichiers dans un fichier compressé format zip. Ce fichier doit porter le nom au format suivant :
   Nom1_Nom2_Nom3_Nom4.zip
   Exemple : Dupont_Durand_Durant_Dupond.zip

Toute autre forme de soumission différente de ce qui est indiqué ci-dessus sera tout simplement ignorée et
donc sanctionnée par un zéro.
Tous vos fichiers .java doivent contenir vos noms, prénoms (en commentaire javadoc).