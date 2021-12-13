package br.teruya.daniel;

import java.util.*;

public class KmenasProcess {




    List<Pino> pinos;

    List<Pino> centroids;

    int qtdClassficacao;

    int qtdReprocessaemento = 0;
    private Double media;


    public KmenasProcess() {
        qtdReprocessaemento = 10;
    }

    public KmenasProcess(List<Pino> pinos, List<Pino> centroids,int qtdReprocessaemento) {
        this.pinos = pinos;
        this.centroids = centroids;
        this.qtdReprocessaemento = qtdReprocessaemento;

        qtdClassficacao = centroids.size();


    }

    public void processar(){
        classificarPinos();


        List<Pino> pinoBk;
        int qtdReprocessada = 0;
        do {
            recarregarCentroid();
            qtdReprocessada++;
            pinoBk = new ArrayList<>(this.pinos);
            System.out.println("Executando o reprocessamento: "+qtdReprocessada+" de "+qtdReprocessaemento);
           pinos =  classificarPinos();
        }while ( continuaProc(pinoBk,qtdReprocessada));

    }

    private boolean continuaProc(List<Pino> pinoBk, int qtdReprocessada) {
        boolean mesmaclas = mesmaClassificacao(pinoBk);
        boolean quantidadeProcesamento = qtdReprocessada < qtdReprocessaemento;

        if(mesmaclas == false && quantidadeProcesamento ){
            return true;
        }else {
            System.out.println("mesma classificacao");
            return false;
        }

    }

    private boolean mesmaClassificacao(List<Pino> pinoBk) {
        Collections.sort(pinoBk,Comparator.comparing(pino -> {return pino.geY()*pino.geY()+pino.geX(); }));
        Collections.sort(pinos,Comparator.comparing(pino -> {return pino.geY()*pino.geY()+pino.geX(); }));

        for(int i = 0 ; i<pinoBk.size(); i++){
            if(!pinos.get(i).getClassificacao().equals(pinoBk.get(i).getClassificacao())){
                return false;
            }
        }
        return true;
    }


    public List<Pino> classificarPinos(){
        System.out.println("Classificando pinos");
        Double somaMedia = 0D;
        for(Pino pino : pinos){
            Double min = Double.MAX_VALUE;
            Pino minP = null;
            for(Pino centro : centroids){

                Double distancia = calculaDistancia(pino,centro);

                if(minP == null){
                    minP = centro;
                }

                if(min > distancia){
                    min = distancia;
                    minP = centro;
                }

            }
            somaMedia += min;
            System.out.println("Pino "+ pino.getLabel()+" pertence a "+minP.getClassificacao());
            pino.setClassificacao(minP.getClassificacao());
        }
        Double size =  Double.parseDouble(pinos.size()+"");
        this.media = somaMedia/size;
        return pinos;
    }

    private void recarregarCentroid() {
        Map<String, List<Pino>> mapa = new HashMap<>();

        for(Pino pino: pinos){
            List<Pino> p = mapa.get(pino.getClassificacao());
            if(p == null){
                p = new ArrayList<>();
            }
            p.add(pino);
            mapa.put(pino.getClassificacao(),p);
        }

        this.centroids = new ArrayList<>();

        for(String classificacao: mapa.keySet()){

            List<Pino> pin = mapa.get(classificacao);
            Double menX = calculaMediaX(pin);
            Double menY = calculaMediaY(pin);

            Pino centroide = new Centroide(menX,menY,classificacao);
            centroids.add(centroide);

        }



    }

    private Double calculaMediaY(List<Pino> pin) {
        Double soma = 0D;
        for(Pino p: pin){
            soma += p.geY();
        }
        Double tamanho = Double.parseDouble(pin.size()+"");

        return soma/tamanho;

    }


    private Double calculaMediaX(List<Pino> pin) {
        Double soma = 0D;
        for(Pino p: pin){
            soma += p.geX();
        }
        Double tamanho = Double.parseDouble(pin.size()+"");

        return soma/tamanho;

    }

    public Double calculaDistancia(Pino pino, Pino centro) {
        Double x = Math.pow(pino.geX()-centro.geX(),2);
        Double y = Math.pow(pino.geY()-centro.geY(),2);
        return Math.sqrt(x+y);
    }

    public List<Pino> getPinos() {
        return pinos;
    }

    public void setPinos(List<Pino> pinos) {
        this.pinos = pinos;
    }

    public List<Pino> getCentroids() {
        return centroids;
    }

    public void setCentroids(List<Pino> centroids) {
        this.centroids = centroids;
    }

    public int getQtdClassficacao() {
        return qtdClassficacao;
    }

    public void setQtdClassficacao(int qtdClassficacao) {
        this.qtdClassficacao = qtdClassficacao;
    }

    public int getQtdReprocessaemento() {
        return qtdReprocessaemento;
    }

    public void setQtdReprocessaemento(int qtdReprocessaemento) {
        this.qtdReprocessaemento = qtdReprocessaemento;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Map<String, List<Pino>> getResultado() {
        Map<String, List<Pino>> resultado = new HashMap<>();

        for(Pino p : pinos){
            List<Pino> pinei = resultado.get(p.getClassificacao());
            if(pinei == null){
                pinei = new ArrayList<>();
            }

            pinei.add(p);
            resultado.put(p.getClassificacao(),pinei);

        }

        return resultado;



    }
}
