/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.io;

/**
 *
 * @author Teemu
 */
import java.util.ArrayList;

public class StubIO implements IO {

    private String[] input;
    private int index;
    private ArrayList<String> prints;

    public StubIO(String... inputs) {
        this.input = inputs;
        this.index=0;
        prints = new ArrayList<String>();
    }
/*
    public void print(String toPrint) {
        prints.add(toPrint);
    }

    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(input[index++]);
    }

    public ArrayList<String> getPrints() {
        return prints;
    }

    public String readLine(String prompt) {
        print(prompt);
        if (index < input.length) {
            return input[index++];
        }
        return "";
    }*/

    public ArrayList<String> getPrints() {
//        System.out.println(this.prints.toString());
        return this.prints;
    }
    
    @Override
    public String readline() {
        return this.input[this.index++];
    }

    @Override
    public int readInteger() {
        return Integer.parseInt(this.input[index++]);
    }

    @Override
    public void write(String output) {
        this.prints.add(output);
//        System.out.println(output);
    }
}