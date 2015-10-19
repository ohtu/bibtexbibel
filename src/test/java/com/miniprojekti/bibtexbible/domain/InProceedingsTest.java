/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.domain;

import com.miniprojekti.misc.Tool;
import static com.miniprojekti.misc.Tool.replaceNullsWithEmpty;
import static com.miniprojekti.misc.Tool.scandisToBibtex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author asjuvone
 */
public class InProceedingsTest {

    InProceedings emptyInProceedings;
    InProceedings typicalInProceedings;
    InProceedings fullyDescribedInProceedings;
    Set<String> labels;

    public InProceedingsTest() {
    }

    @Before
    public void setUp() {
        emptyInProceedings = new InProceedings();
        labels = emptyInProceedings.getPropertyDescriptions().keySet();
        typicalInProceedings = new InProceedings();
        typicalInProceedings.setProperty("title", "Taloyhtiön vuosikokous");
        typicalInProceedings.setProperty("year", "2015");
        replaceNullsWithEmpty(emptyInProceedings);
        replaceNullsWithEmpty(typicalInProceedings);
        fullyDescribedInProceedings = new InProceedings();
        for (String label : fullyDescribedInProceedings.getPropertyDescriptions().keySet()) {
            fullyDescribedInProceedings.setProperty(label, label + "x");
            // testissä esim. address arvo pitäisi olla "addressx"
        }
        labels = emptyInProceedings.getPropertyDescriptions().keySet();
    }

    /*
     * Allowed labels for inProceedings should be the same regardless
     * of constructor used, set properties, etc.
     */
    @Test
    public void testInProceedingsLabelsAreTheSame() {
        Set<String> labels2 = typicalInProceedings.getPropertyDescriptions().keySet();
        Set<String> labels3 = fullyDescribedInProceedings.getPropertyDescriptions().keySet();
        assertTrue(labels.size() == labels2.size());
        assertTrue(labels2.size() == labels3.size());
        for (String label : labels) {
            assertTrue(labels2.contains(label));
            assertTrue(labels3.contains(label));
        }
    }

    @Test
    public void testConstructorSetsPropertyValuesCorrectlyTypicalInProceedings() {
        assertTrue(typicalInProceedings.getProperty("title").equals("Taloyhtiön vuosikokous"));
        assertTrue(typicalInProceedings.getProperty("year").equals("2015"));
        // varmistetaan vielä ettei muita valueita löydy
        int count = 0; // kuinka monta valueta typicalinProceedingsilta löytyy
        for (String label : labels) {
            if (!typicalInProceedings.getProperty(label).isEmpty()) {
                count++;
            }
        }
        assertTrue(count == 2);
    }
    
    @Test
    public void testConstructorSetsPropertyValuesCorrectlyEmptyInProceedings() {
        for (String label : labels) {
            assertTrue(emptyInProceedings.getProperty(label).isEmpty());
        }
    }
        

    @Test
    public void testSetProperty() {
        for (String label : labels) {
            String value = fullyDescribedInProceedings.getProperty(label);
            assertTrue(value.equals(label + "x"));
        }
    }

    @Test
    public void testSetPropertyWhenOverwriting() {
        fullyDescribedInProceedings.setProperty("title", "Uolevi");
        assertTrue(fullyDescribedInProceedings.getProperty("title").equals("Uolevi"));
    }

    @Test
    public void testSetPropertyWrongLabel() {
        typicalInProceedings.setProperty("dummy1337", "testi");
        assertNull(fullyDescribedInProceedings.getProperty("dummy1337"));
    }

    @Test
    public void testGetPropertiesReturnsHashMapWithCorrecNumberOfProperties() {
        Map<String, String> properties = emptyInProceedings.getPropertyDescriptions();
        assertEquals(true, properties instanceof HashMap);
        assertEquals(12, properties.size());
    }

    @Test
    public void testToString() {
        assertTrue(typicalInProceedings.toString().contains("Taloyht"));
        assertTrue(typicalInProceedings.toString().contains("2015"));
    }

    @Test
    public void testToBibTex() {
        testInProceedingsToBibTex(typicalInProceedings, 2 + 2);
        testInProceedingsToBibTex(fullyDescribedInProceedings, labels.size() + 2);
        // test empty inProceedings to bibtex?
    }

    private void testInProceedingsToBibTex(InProceedings inProceedings, int valueCount) {
        String b = inProceedings.toBibTex();
        String[] split = b.split("\r\n");
        assertTrue(split.length == valueCount);
        assertTrue(split[0].startsWith("@InProceedings{")); // any ID is ok
        HashSet<String> labelsFound = new HashSet<>();
        for (int i = 1; i < split.length - 1; i++) {
            String[] rivi = split[i].split("\\=", 2);
            assertTrue(rivi.length == 2);
            String label = rivi[0].trim();
            String expected = inProceedings.getProperty(label);
            expected = scandisToBibtex(expected);
            String found = rivi[1].trim();
            found = found.substring(1);
            found = found.substring(0, found.length() - 2); // lopusta pois " ja ,
            labelsFound.add(label);
            assertTrue(found.equals(expected));
        }
        assertTrue(labelsFound.size() == valueCount - 2);
        assertTrue(b.endsWith("}"));
    }
}