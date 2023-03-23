# portfolio

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
