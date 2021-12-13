package br.teruya.daniel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kmeans {

    int k; //quantidade de cluster
    int qtdRepeticao = 100;
    int qtdReprocessamentoCentroid = 10;

    List<Pino> dados;
    List<Centroide> centroidesAleatorios = new ArrayList<>();
    List<String> classificacoes;


    public Kmeans(int k, int qtdRepeticao, List<Pino> dados) {
        this.k = k;
        this.qtdRepeticao = qtdRepeticao;
        this.dados = dados;
    }

    public Map<String,List<Pino>> processar(){
        Double maxY = getMaxY();
        Double minY = getMinY();
        Double maxX = getMaxX();
        Double minX = getMinX();


        List<KmenasProcess> processes = new ArrayList<>();
        Double minK = Double.MAX_VALUE;
        Map<String, List<Pino>> aMenor = null;
        for(int i = 0; i< qtdRepeticao ; i++) {
            System.out.println("executando repeticao "+i+" de "+qtdRepeticao);
            List<Pino> centroides = geraCentroidesAleatorio(maxX, minX, maxY, minY);
            KmenasProcess kmenasProcess = new KmenasProcess(new ArrayList<>(dados), centroides, qtdReprocessamentoCentroid);
            System.out.println("Iniciando processamento de kmeans");
            kmenasProcess.processar();

            Map<String, List<Pino>> a = kmenasProcess.getResultado();

            for(String classificacao: a.keySet()){
                System.out.print(classificacao);
                for(Pino p : a.get(classificacao)){
                    System.out.println(" "+p.geX()+";  "+p.geY()+" ");
                }
                System.out.println();
            }
            System.out.println("media "+kmenasProcess.getMedia());
            if(kmenasProcess.getMedia() < minK ){
                minK =kmenasProcess.getMedia();
                aMenor = a;


            }


            processes.add(kmenasProcess);
            System.out.println("finalizando repeticao "+i+" de "+qtdRepeticao);
        }

        System.out.println("A menor média para o centroid foi "+minK);
        return aMenor;
    }

    private Double getMinX() {
        Double mim = Double.MAX_VALUE;
        for(Pino p:dados){
            if(p.geX() < mim){
                mim = p.geX();
            }
        }

        if(mim > 0){
            mim -= mim;
        }else{
            mim *=2;
        }


        return mim;

    }

    private Double getMinY() {
        Double mim = Double.MAX_VALUE;
        for(Pino p:dados){
            if(p.geY() < mim){
                mim = p.geY();
            }
        }
        if(mim > 0){
            mim -= mim;
        }else{
            mim *=2;
        }


        return mim;

    }

    private List<Pino> geraCentroidesAleatorio(Double maxX, Double minX, Double maxY, Double minY) {
        System.out.println("Gerando centroid aleatorios");
        List<Pino> centroides = new ArrayList<>();

        for(int i = 0; i< k; i++){
            Centroide c;
            do{
                Double x =  minX + (maxX - minX)*Math.random();
                System.out.println(" X excolhido "+x);
                Double y =  minY + (maxY - minY)*Math.random();
                System.out.println("Y excolhido "+y);

                c = new Centroide(x,y, getClassificacoes().get(i));
            }while (centroidesAleatorios.contains(c));
            System.out.println("Centroid gerado"+c.toString());
            centroidesAleatorios.add(c);
            centroides.add(c);
        }
        System.out.println("Fim da geracao de centroides");
        return centroides;



    }

    private Double getMaxX() {
        Double max = Double.MIN_VALUE;
        for(Pino p:dados){
            if(p.geX() > max){
                max = p.geX();
            }
        }

        if(max < 0){
            max += max;
        }else{
            max *=2;
        }

        return max;
    }

    private Double getMaxY() {
        Double max = Double.MIN_VALUE;
        for(Pino p:dados){
            if(p.geY() > max){
                max = p.geY();
            }
        }
        if(max < 0){
            max += max;
        }else{
            max *=2;
        }
        return max;

    }

    public List<String> getClassificacoes() {
        if(this.classificacoes == null){
            classificacoes = new ArrayList<>();
            for( int i = 0;i<k;i++){
                classificacoes.add(new String(i+""));
            }
        }
        return classificacoes;
    }

    public void setClassificacoes(List<String> classificacoes) {
        this.classificacoes = classificacoes;
    }


    public int getQtdReprocessamentoCentroid() {
        return qtdReprocessamentoCentroid;
    }

    public void setQtdReprocessamentoCentroid(int qtdReprocessamentoCentroid) {
        this.qtdReprocessamentoCentroid = qtdReprocessamentoCentroid;
    }
}
