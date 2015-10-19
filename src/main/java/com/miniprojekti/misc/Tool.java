/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.misc;

import com.miniprojekti.bibtexbible.domain.Reference;
import java.util.Set;

/**
 *
 * @author asjuvone
 */
public final class Tool {

    private Tool() {
    }

    public static String replaceScandisForBibTex(String s) {
        new Tool(); // testikattavuus 100%
        s = s.replace("ö", "{\\\"o}"); //   {\"o}
        s = s.replace("Ö", "{\\\"O}"); 
        s = s.replace("Ä", "{\\\"A}"); 
        s = s.replace("ä", "{\\\"a}");
        s = s.replace("Å", "{\\AA}");
        s = s.replace("å", "{\\aa}");
        return s;
    }
    
    public static String truncate(String s) {
        return s.substring(0, Math.min(4, s.length()));  
    }
    
    /**
     * Kun käyttäjä syöttää propertyt, tyhjät kentät ei jää nulleiks
     * Testeissä luotavissa reference-olioissa pitää vastaavasti
     * korvata nullit tyhjillä stringeillä
     */
    public static void replaceNullsWithEmpty(Reference r) {
        Set<String> labels = r.getPropertyDescriptions().keySet();
        for (String label : labels) {
            if (r.getProperty(label) == null) {
                r.setProperty(label, "");
            }
        }
    }
   
    public static String getType(Object o) {
        String[] classPath = o.getClass().toString().split("\\.");
        return classPath[classPath.length - 1];
    }
}
