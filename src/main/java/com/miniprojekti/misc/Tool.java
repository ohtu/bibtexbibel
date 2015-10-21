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

    public static String scandisToBibtex(String s) {
        s = s.replace("ö", "{\\\"o}"); //   {\"o}
        s = s.replace("Ö", "{\\\"O}"); 
        s = s.replace("Ä", "{\\\"A}"); 
        s = s.replace("ä", "{\\\"a}");
        s = s.replace("Å", "{\\AA}");
        s = s.replace("å", "{\\aa}");
        return s;
    }
    
    public static String bibtexToScandis(String s) {
        s = s.replace("{\\\"o}", "ö"); //   {\"o}
        s = s.replace("{\\\"O}", "Ö"); 
        s = s.replace("{\\\"A}", "Ä"); 
        s = s.replace("{\\\"a}", "ä");
        s = s.replace("{\\AA}", "Å");
        s = s.replace("{\\aa}", "å");
        return s;
    }
    
    public static String safeSubstring(String s, int startIndex) {
        return safeSubstring(s, startIndex, s.length());
    }
    public static String safeSubstring(String s, int startIndex, int desiredLength) {
        if (s.isEmpty()) return s;
        return s.substring(startIndex, Math.min(desiredLength, s.length()));  
    }
    
    /**
     * Kun käyttäjä syöttää propertyt, tyhjät kentät ei jää nulleiks
     * Testeissä luotavissa reference-olioissa pitää vastaavasti
     * korvata nullit tyhjillä stringeillä
     */
    public static void addMissingProperties(Reference r) {
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
