http://localhost:8082/h2-console/
JDBC URL: jdbc:h2:mem:customer-db

http://localhost:8082/swagger-ui/index.html
http://localhost:8082/v3/api-docs

erreur par defaut de graphqli

model mapper utilise get set pour cela on a changer le record a une class

l'avantage de graphql au rest c'est la projection + json loop dans les relation

tous les requete graphql passe par le meme endpoint qui est /graphql

tous les methodes sont post mem les query , pour cela il fait la difference dans le body (payload)

pour les exeptions graph envoi un message par default qui doit etre personnaliser (creer un intercepteur)

jaxwbs et cxf pour soap

class de configuration pour publier le web service

http://localhost:8082/services/CustomerService?wsdl pour la documentation XML

soapUi pour tester


creation de proto file + compilation proto file

la compilation ce fait par le plugin ajouter qui vas chercher depuis <include>src/main/resources</include>
puis mettre le resultat du compillation dans <outputDirectory>src/main/java</outputDirectory>
pour cela (compilation) mvn lifecycle validate et package et clean

pour grpc on a besoin d'un serveur grpc , pour cela on a ajouter une dependace devh qui nous permet d'ajouter
une annotation pour lancer le serveur grpc @GrpcService

pour tester grpc , en peut utiliser BlameRpc
