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
        this.index = 0;
        prints = new ArrayList<String>();
    }

    public ArrayList<String> getPrints() {
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
    }
}
