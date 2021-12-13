package teste;

import br.teruya.daniel.Pino;

public class PinoTeste implements Pino {

    Double x;
    Double y;

    String label;
    String classificacao;

    public PinoTeste(Double x, Double y) {
        this.x = x;
        this.y = y;
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
        return label;
    }

    @Override
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public String getClassificacao() {
        return this.classificacao;
    }
}
