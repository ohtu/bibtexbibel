/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.misc;

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
    
    public static String getType(Object o) {
        String[] classPath = o.getClass().toString().split("\\.");
        return classPath[classPath.length-1];
    }
}
