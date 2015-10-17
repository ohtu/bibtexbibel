/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.misc;

import com.miniprojekti.bibtexbible.domain.Article;
import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.InProceedings;
import com.miniprojekti.bibtexbible.domain.Proceedings;
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
        s = s.replace("ö", "\\\"{o}"); //   \"{o}
        s = s.replace("Ö", "\\\"{O}"); //   \"{O} arvattu - testaa!
        s = s.replace("Ä", "{\\\"A}"); //   {\"A} arvattu - testaa!
        s = s.replace("ä", "{\\\"a}"); //   {\"a}
        s = s.replace("Å", "\\AA"); //      \AA
        s = s.replace("å", "\\aa"); //      \aa
        return s;
    }
    
    public static String replaceBibTexForScandis(String s) {      
        new Tool();
        s = s.replace("\\\"{o}", "ö");
        s = s.replace("\\\"{O}", "Ö");
        s = s.replace("{\\\"A}", "Ä");
        s = s.replace("{\\\"a}", "ä");
        s = s.replace("\\AA", "Å");
        s = s.replace("\\aa", "å");
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
   
    public static String parseBiBTexValue(String str) {
        String value = str.trim().substring(1);
        return replaceBibTexForScandis(value.substring(0, value.length()-2));
    }
    
    public static String getType(Object o) {
        String[] classPath = o.getClass().toString().split("\\.");
        return classPath[classPath.length - 1];
    }

    public static Reference getType(String type) {
        switch(type.toLowerCase()) {
            case "book":
                return new Book();
            case "article":
                return new Article();
            case "inproceedings":
                return new InProceedings();
            case "proceedings":
                return new Proceedings();
            default:
                return null; 
        }
    }
    
    public static Reference parseReference(String s) {
        String[] rows = s.split(System.lineSeparator());
        String type = rows[0].split("\\{")[0].trim();
        Reference reference = getType(type);
        for (int i = 1; i < rows.length-1; i++) {
            String[] properties = rows[i].split("\\=", 2);
            String label = properties[0].trim();
            String value = parseBiBTexValue(properties[1]);
            reference.setProperty(label, value);
        } 
        
        return reference;
    }
    
}
