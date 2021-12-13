package teste;

import br.teruya.daniel.KmenasProcess;
import br.teruya.daniel.Pino;
import org.junit.Assert;
import org.junit.Test;


public class KmeansProcessTest {


    @Test
    public void distancTest(){


        Pino a = new PinoTeste(10D,5D);
        Pino b = new PinoTeste(15D,10D);
        KmenasProcess  k = new KmenasProcess();
        Double dist = k.calculaDistancia(a,b);
        Assert.assertTrue(dist == 7.0710678118654755);


    }

}
