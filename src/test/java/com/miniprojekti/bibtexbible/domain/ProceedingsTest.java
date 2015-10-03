/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.domain;

import com.miniprojekti.misc.Tool;
import static com.miniprojekti.misc.Tool.replaceScandisForBibTex;
import java.util.HashMap;
import java.util.HashSet;
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
public class ProceedingsTest {
   
    Proceedings emptyProceedings;
    Proceedings typicalProceedings;
    Proceedings fullyDescribedProceedings;
    Set<String> labels;

    public ProceedingsTest() {
    }

    @Before
    public void setUp() {
        emptyProceedings = new Proceedings();
        typicalProceedings = new Proceedings(
                "Taloyhtiön vuosikokous",
                "2015"
        );
        fullyDescribedProceedings = new Proceedings();
        for (String label : fullyDescribedProceedings.getPropertyDescriptions().keySet()) {
            fullyDescribedProceedings.setProperty(label, label + "x");
            // testissä esim. address arvo pitäisi olla "addressx"
        }
        labels = emptyProceedings.getPropertyDescriptions().keySet();
    }
    
    /*
    * Allowed labels for proceedings should be the same regardless
    * of constructor used, set properties, etc.
    */
    @Test
    public void testProceedingsLabelsAreTheSame() {
        Set<String> labels2 = typicalProceedings.getPropertyDescriptions().keySet();
        Set<String> labels3 = fullyDescribedProceedings.getPropertyDescriptions().keySet();
        assertTrue(labels.size() == labels2.size());
        assertTrue(labels2.size() == labels3.size());
        for (String label : labels) {
            assertTrue(labels2.contains(label));
            assertTrue(labels3.contains(label));
        }
    }
    
    @Test
    public void testConstructorSetsPropertyValuesCorrectly() {
        assertTrue(typicalProceedings.getProperty("title").equals("Taloyhtiön vuosikokous"));
        assertTrue(typicalProceedings.getProperty("year").equals("2015"));
        // varmistetaan vielä ettei muita valueita löydy
        int count = 0; // kuinka monta valueta typicalproceedingsilta löytyy
        for (String label : labels) {
            // varmistetaan samalla ettei emptyproceedingsilta löydy mitään
            assertTrue(emptyProceedings.getProperty(label) == null);
            if (typicalProceedings.getProperty(label) != null) count++;
        }
        assertTrue(count==2);
    }
    
    @Test
    public void testSetProperty() {
        for (String label : labels) {
            String value = fullyDescribedProceedings.getProperty(label);
            assertTrue(value.equals(label + "x"));
        }
    }
    
    @Test
    public void testSetPropertyWhenOverwriting() {
        fullyDescribedProceedings.setProperty("title", "Uolevi");
        assertTrue(fullyDescribedProceedings.getProperty("title").equals("Uolevi"));
    }

    @Test
    public void testGetPropertiesReturnsHashMapWithCorrecNumberOfProperties() {
        HashMap<String, String> properties = emptyProceedings.getPropertyDescriptions();
        assertEquals(true, properties instanceof HashMap);
        assertEquals(12, properties.size());
    }
    
    @Test
    public void testToString() {
        assertTrue(typicalProceedings.toString().contains("Taloyht"));
        assertTrue(typicalProceedings.toString().contains("2015"));
    }
    
    @Test
    public void testToBibTex() {
        testProceedingsToBibTex(typicalProceedings, 2+2);
        testProceedingsToBibTex(fullyDescribedProceedings, labels.size()+2);
        // test empty proceedings to bibtex?
    }
    
    private void testProceedingsToBibTex(Proceedings proceedings, int valueCount) {
        String b = proceedings.toBibTex();
        String[] split = b.split("\r\n");
        assertTrue(split.length == valueCount);
        assertTrue(split[0].startsWith("@Proceedings{")); // any ID is ok
        HashSet<String> labelsFound = new HashSet<>();
        for (int i=1; i<split.length-1; i++) {
            String[] rivi = split[i].split("\\=", 2);
            assertTrue(rivi.length == 2);
            String label = rivi[0].trim();
            String expected = proceedings.getProperty(label);
            expected = replaceScandisForBibTex(expected);
            String found = rivi[1].trim();
            found = found.substring(1);
            found = found.substring(0, found.length()-2); // lopusta pois " ja ,
            labelsFound.add(label);
            assertTrue(found.equals(expected));
        }
        assertTrue(labelsFound.size() == valueCount-2);
        assertTrue(b.endsWith("}"));
    }
}
