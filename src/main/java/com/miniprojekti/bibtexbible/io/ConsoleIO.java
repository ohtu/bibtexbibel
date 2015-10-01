package com.miniprojekti.bibtexbible.io;

import java.util.Scanner;

public class ConsoleIO implements IO {

    private final Scanner scanner;

    public ConsoleIO() {
        scanner = new Scanner(System.in);
    }

    public ConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void write(String output) {
        System.out.println(output);
    }

    @Override
    public String readline() {
        return scanner.nextLine();
    }
    
    @Override
    public int readInteger() {
        while (true) {
            try {
                return scanner.nextInt();  
            } catch (Exception e) {
                write("Non integer. Try again.");
            } 
        }
    }
}
