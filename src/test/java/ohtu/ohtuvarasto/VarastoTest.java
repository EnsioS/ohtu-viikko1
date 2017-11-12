package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusKonstruktori2() {
        varasto = new Varasto(10, 0);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void oikeaTilavuusNegatiivisellaKonstruktorinSyotteella() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void oikeaTilavuusNegatiivisellaKonstruktorin2Syotteella() {
        varasto = new Varasto(-1, 0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void oikeaSaldoNegatiivisellaKonstruktorin2Syotteella() {
        varasto = new Varasto(1, -5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-2);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiLisataSaldoaYliTilavuuden() {
        varasto.lisaaVarastoon(20);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void eiOtetaNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-3);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void yliSaldonPyytaminenOttaaSsaldonVerran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(30);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void yliSaldonPyytaminenNollaaSsaldon() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(30);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}
