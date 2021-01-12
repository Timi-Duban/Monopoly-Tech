# Monopoly-Tech
Student project from Polytech Montpellier IG4
Canton Axel, Duban Timi, Mensah Rogerio, Ognard Alexia

Vous trouverez ici le code de notre application dans le dossier "PrjetSE", et les diagrammes réalisés dans le dossier "Use case".

Concernant les diagrammes, ceux du Login sont les plus généraux, et les diagrammes des use cases implémentés se basent sur ceux du login.

Concernant le code, il faut avoir une base de données MySQL en local pour l'exploiter. Il faudra renseigner son login et mot de passe du schema MySQL dans la classe ConnectionToDBMySQL (/PrjetSE/src/server/PL/ConnectionToDBMySQL.java), et pour pouvoir exploiter les images des items du shop il faudra renseigner l'URL du dossier source dans le string ROOT_URL de la classe ShopView (/PrjetSE/src/client/UI/ShopView.java).
Ensuite il suffit de lancer le MainServer et le MainClient.