package br.teruya.daniel;

import java.util.Objects;

public class Centroide implements Pino {

    Double x;
    Double y;

    String label;
    String classificacao;

    public Centroide(Double x, Double y, String classificacao) {
        this.x = x;
        this.y = y;
        this.classificacao = classificacao;
    }

    @Override
    public Double geX() {
        return x;
    }

    @Override
    public Double geY() {
        return y;
    }

    @Override
    public String getLabel() {
        return this.classificacao;
    }

    @Override
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String getClassificacao() {
        return this.classificacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Centroide centroide = (Centroide) o;
        return x.equals(centroide.x) && y.equals(centroide.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    @Override
    public String toString() {
        return "Centroide{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
