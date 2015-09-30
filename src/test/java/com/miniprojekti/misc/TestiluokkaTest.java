package com.miniprojekti.misc;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jim@saartia.fi
 */
public class TestiluokkaTest {

    private Testiluokka testiLuokka;

    @Before
    public void setUp() {
        testiLuokka = new Testiluokka();
    }

    @Test
    public void testiMetodiPalauttaaLuvunYksi() {
        assertEquals(1, testiLuokka.testiMetodi());
    }

}
