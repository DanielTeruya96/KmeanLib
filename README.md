# K-Means implementação em java
Implementação de K-means com pontos no plano cartesiano. O principal desse codigo é receber uma lista de Pino(x,y do plano cartesiano) e devolver o cluster, e a lista dos Pinos que estao dentro do cluster.

## O programa tem como entrada :
1. Pinos: Lista de pinos( x,y dos pontos cartesianos) para serem processados
2. K: A quantidade de cluster que deseja 
3. qtdRepeticao: Quantidade de vezes que o algoritimo vai gerar centroids aleatorios para encontrar os clusters
4. classificacoes: Lista de classificações caso queira colocar nome aos clusters(opcional)

## Saida:
- Clusters: um map com as classificações e os pontos pertecentes a essas classificações

## Esse codigo foi testado apenas com o java8

##Modo de usar:
- Implemente a interface Pino.java, para que retorne os valores de x, e y do plano cartesiano, uma label para identificar o Pino, e uma String classificacao, faça com que o valor a ser setado no metodo setClassificacao, seja devolvido no metodo getClassificacao
- Preencha uma lista de Pino e instancie uma classe de Kmeans com os parametros: k quantidade de cluster, qtdRepeticao quantidade de vezes que sera gerado centroide aleatorio e a lista de pinos
- set uma lista de Strings caso queria dar nome aos cluster
- set setQtdReprocessamentoCentroid, caso queria alterar o limite de reprocessamento dos centroids(padrão é 10), o reprocessamento do centroid nao ocorrera caso nao mude as classificações dos pinos
- Por fim invoque o metodo processar
- A classe Main.java implementa um exmplo de uso







***
##Glossario
- Pino: nome dado ao ponto(x,y) no codigo é uma interface 
- Cluster: um agrupamento de Pino
- Centroide: centro do cluster

***




