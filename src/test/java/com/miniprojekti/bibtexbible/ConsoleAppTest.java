package com.miniprojekti.bibtexbible;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Teemu
 */
public class ConsoleAppTest {

    private App consoleApp;
    private ConsoleUI consoleUI;
    private ConsoleIO consoleIO;
    private ReferenceController refController;

    public ConsoleAppTest() {
        this.consoleUI = mock(ConsoleUI.class);
        this.consoleIO = mock(ConsoleIO.class);
        this.refController = mock(ReferenceController.class);

        this.consoleApp = new App(
                this.consoleUI,
                this.consoleIO,
                this.refController
        );
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 1000)
    public void testConsoleAppRunsAndExits() {
        when(this.consoleIO.readline()).thenReturn("0");
        this.consoleApp.runConsoleApp();
    }

    @Test(timeout = 1000)
    public void testConsoleAppDoesntCrashOnNonIntegerInput() {
        when(this.consoleIO.readline()).thenReturn("asdf").thenReturn("0");
        this.consoleApp.runConsoleApp();
    }

    @Test(timeout = 1000)
    public void testConsoleAppCallsCreateReference() {
        when(this.consoleIO.readline()).thenReturn("1").thenReturn("0");
        this.consoleApp.runConsoleApp();
        verify(this.refController).create();
    }

    @Test(timeout = 1000)
    public void testConsoleAppCallsListReference() {
        when(this.consoleIO.readline()).thenReturn("2").thenReturn("0");
        this.consoleApp.runConsoleApp();
        verify(this.refController).list();
    }

    @Test(timeout = 1000)
    public void testConsoleAppCallsDeleteReference() {
        when(this.consoleIO.readline()).thenReturn("4").thenReturn("0");
        this.consoleApp.runConsoleApp();
        verify(this.refController).delete();
    }
}
