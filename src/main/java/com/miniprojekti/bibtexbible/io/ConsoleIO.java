package com.miniprojekti.bibtexbible.io;

import java.util.Scanner;

public class ConsoleIO implements IO {

    private final Scanner scanner;

    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
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
                return Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                write("Non integer. Try again.");
            }
        }
    }
}
