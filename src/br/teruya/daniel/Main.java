package br.teruya.daniel;

import teste.PinoTeste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        long inicio = Calendar.getInstance().getTimeInMillis(); // só para contar o tempo de processamento

        //gerando pinos aleatorios só para teste
        List<Pino> pinos = gerarPinos();

        //gera classificação apenas para teste
        List<String> classficacao = gerarClassificacao();

        Kmeans kmeans = new Kmeans(4,1000,pinos);
        kmeans.setClassificacoes(classficacao);//opcional

        //inicio do processametno
        Map<String, List<Pino>> a = kmeans.processar();

        System.out.println("---------------------");

        //printando o resultado
        for(String classificacao: a.keySet()){
            System.out.println(classificacao);
            for(Pino p : a.get(classificacao)){
                System.out.println(" "+(p.geX())+","+(p.geY())+" ");
            }
            System.out.println();
        }

        long fim = Calendar.getInstance().getTimeInMillis();
        fim = fim - inicio;
        System.out.println("Tempo de processamento "+ fim  );



    }

    private static List<String> gerarClassificacao() {
        List<String> classficacao = new ArrayList<String>();
        classficacao.add("Vermelho");
        classficacao.add("Verde");
        classficacao.add("Azul");
        classficacao.add("Roxo");
        return classficacao;
    }

    private static List<Pino> gerarPinos() {
        List<Pino> pinos = new ArrayList<Pino>();


        for(int i = 0; i<15; i++){
            Double x = -10 + (50 - (-10))*Math.random();
            x = Math.floor(x);
            Double y = -10 + (50 - (-10))*Math.random();
            y = Math.floor(y);
            pinos.add(new PinoTeste(x,y));

        }
        return pinos;
    }
}


