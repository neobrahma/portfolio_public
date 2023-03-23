# portfolio_public

Technologies utilisées :
Kotlin
Compose
Navigation compose
Coroutine
Flow
Hilt
datastore
room

Archi :
MVVM
Clean archi

Le projet se compose de 2 modules
Module UI qui contient des customs views, shapes et theme
Module app qui est l'application

Le module app se compose de 4 packages :

# DI
contient tt les modules pour l'injection de dependance

# presentation
Contient les differents ecrans, il y a 4 ecrans,
le splashscreen, 
le tree, c'est une representation de mon CV sous la forme d'un "arbre GIT", dans lequel on peut cliquer sur les clients/projets pour acceder au detail.
filter, ou on pourra filter l'arbre en fonction de la stack technique
information, contenant diverses informations et des camemberts par rapport au stack technique par categorie

chaque ecran se compose generalement d'une activity, un view model, 1 ou plusieur *Screen (fonction composable pour definir les écrans), un mapper qui convertir un objet domain en objet UI
