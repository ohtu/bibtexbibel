package com.miniprojekti.bibtexbible.io;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author teemu
 */
public class ConsoleIO implements IO {
    
    private Scanner reader;
    
    @Override
    public String readline(int prompt) {
        // tee jotain
        return reader.nextLine();
    }
}
