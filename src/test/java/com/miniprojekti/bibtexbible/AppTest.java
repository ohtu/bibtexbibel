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
public class AppTest {

    private App consoleApp;
    private ConsoleUI consoleUI;
    private ConsoleIO consoleIO;
    private ReferenceController refController;

    public AppTest() { 
        this.consoleIO = mock(ConsoleIO.class);
        this.consoleUI = mock(ConsoleUI.class);
        this.refController = mock(ReferenceController.class);

        this.consoleApp = new App(
                this.consoleUI,
                this.refController
        );
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
        when(consoleUI.selectMenuOption()).thenReturn(1).thenReturn(0);
        when(consoleUI.selectReferenceType()).thenReturn(0);
        this.consoleApp.runConsoleApp();
        verify(this.refController).create();
    }

    @Test(timeout = 1000)
    public void testConsoleAppCallsListReference() {
        when(consoleUI.selectMenuOption()).thenReturn(2).thenReturn(0);
        this.consoleApp.runConsoleApp();
        verify(this.refController).list();
    }

    @Test(timeout = 1000)
    public void testConsoleAppCallsDeleteReference() {
        when(consoleUI.selectMenuOption()).thenReturn(3).thenReturn(0);
        this.consoleApp.runConsoleApp();
        verify(this.refController).delete();
    }
    
    @Test(timeout = 1000)
    public void testConsoleAppCallsExport() {
        when(consoleUI.selectMenuOption()).thenReturn(4).thenReturn(0);
        this.consoleApp.runConsoleApp();
        verify(this.refController).export();
    }
    
    @Test(timeout = 1000)
    public void testConsoleAppCallsImportBibtex() {
        when(consoleUI.selectMenuOption()).thenReturn(5).thenReturn(0);
        this.consoleApp.runConsoleApp();
        verify(this.refController).importBibtex();
    }
}
