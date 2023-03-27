# portfolio

Application permettant de visualiser mon CV sous la forme d'un "arbre git",
Se compose de 4 ecrans :

-Splashscreen : recupere le backend pour les enregistrer dans une database room.

-Tree : representation de mon CV

| Companies | Company | Client | Project |
|---|----|---|---|
| ![1](https://user-images.githubusercontent.com/96651172/227859899-9f6166a9-21a6-45b9-ba99-67e601b346e0.png) | ![2](https://user-images.githubusercontent.com/96651172/227859938-6b51e2bd-b46c-45ba-ace0-cab5ed0289c8.png) | ![3](https://user-images.githubusercontent.com/96651172/227859992-9b8ecd84-786a-41df-bf2c-ffe1cf6e2b55.png) | ![4](https://user-images.githubusercontent.com/96651172/227860047-ef7304eb-41ce-4ef4-aa4c-3e14c2d4f1c5.png) |


-Filter : possibilité de filtrer le CV par stack technique

-Information : diverses informations + statistique des stacks par categories
| Filter | Information |
|----|----|
| ![6](https://user-images.githubusercontent.com/96651172/227860453-3aadd4ab-c6cd-4eb3-ac08-9463af7cda39.png) | ![7](https://user-images.githubusercontent.com/96651172/227860413-16102f64-dd7a-4018-82ef-f22b4df14004.png) |

Technologies utilisées : Kotlin, Compose, Navigation compose, Coroutine, Flow, Hilt, datastore, room.

Archi : MVVM, Clean archi.

Le projet se compose de 2 modules

Module UI qui contient des customs views, shapes et theme

Module app qui est l'application

# module app

se compose de 4 packages

## DI
contient tt les modules pour l'injection de dependance

## presentation
Contient les differents ecrans, il y a 4 ecrans,

le splashscreen, 

le tree, c'est une representation de mon CV sous la forme d'un "arbre GIT", dans lequel on peut cliquer sur les clients/projets pour acceder au detail.
filter, ou on pourra filter l'arbre en fonction de la stack technique

information, contenant diverses informations et des camemberts par rapport au stack technique par categorie

chaque ecran se compose generalement d'une activity, un view model, 1 ou plusieur *Screen (fonction composable pour definir les écrans), un mapper qui convertir un objet domain en objet UI.

C'est le viewModel qui appelera un usecase defini dans le package domain, convertira le resultat via le mapper et l'envera a la fonction composable pour actualiser l'écran.

## domain
contient les differents usecases, un package repository qui contient les contrats d'interface.

## data
contient les implementations des contrats d'interface, des dataSources et leur implementations, la gestion de la database via room et des datastore.

# TODO

J'ai prévu de réaliser un backend, surement en PHP au debut, avant de peut etre en faire un en kotlin ou autres langages pour decouvrir de nouvelles technologies.

Donc pour l'instant les informations sont "mocker" en dur dans l'application, dans le package data.mock, quand j'aurai mon backend, je rajouterai retrofit qui sera injecter dans mon dataSource via hilt

Dans le CheckoutPortfolioUseCase, j'ai commencé à mettre une logique pour verifier s'il a internet, afin de recuperer pour la premiere fois toutes les informations du portofolio, qui seront stocké en DB, et ensuite, il y aura un test sur le numero de version back pour savoir si l'application doit faire un update de la DB. Si je n'ai pas internet, je dois afficher une popup.

Sur l'ecran du filtre, je dois rajouter une tab bar avec un bouton pour reset les filtres selectionnés.
WARNING : j'ai un probleme sur cette ecran, je n'ai pas encore identifier la raison, mais il arrive qu'apres avoir cliquer sur un item, l'écran devient noir et retourne sur l'ecran tree.

Sur l'ecran tree, faut que je verifie quelque chose, mais il arrive que mon arbre soit a l'envers en fonction des filtres choisis

Concernant les icones "Android", les icones seront stocké sur dans le web et non dans l'application comme maintenant, et seront chargé via une librairie equivalente à Picasso mais pour Compose.

Apres cela, j'ai prevu de transformer mon projet sous une archi modulaire avec du deeplinking, et realiser mes TUs en derniers.
Puis decouvrir Bitrise pour l'integration continue et la publication de mon application sur le store.
