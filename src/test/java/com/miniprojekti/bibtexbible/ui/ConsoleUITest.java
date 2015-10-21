package com.miniprojekti.bibtexbible.ui;

import com.miniprojekti.bibtexbible.logic.ReferenceController;
import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.io.StubIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConsoleUITest {

    IO io;
    ConsoleUI ui;
    ReferenceController rc;

    @Before
    public void setUp() {
        rc = new ReferenceController();
        io = mock(ConsoleIO.class);
        ui = new ConsoleUI(io, rc);
    }

    @Test
    public void selectMenuOptionReturnsInputsBetweenZeroAndFour() {
        when(io.readInteger())
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4);

        assertEquals(0, ui.selectMenuOption());
        assertEquals(1, ui.selectMenuOption());
        assertEquals(2, ui.selectMenuOption());
        assertEquals(3, ui.selectMenuOption());
        assertEquals(4, ui.selectMenuOption());
    }

    @Test
    public void selectMenuOptionDoesNotReturnInputsLessThanZeroOrMoreThanFive() {
        when(io.readInteger())
                .thenReturn(-1)
                .thenReturn(0)
                .thenReturn(6)
                .thenReturn(5);

        assertEquals(0, ui.selectMenuOption());
        assertEquals(5, ui.selectMenuOption());
    }

    @Test
    public void selectReferenceTypeReturnsInputBetweenZeroAndThree() {
        when(io.readInteger())
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);

        assertEquals(0, ui.selectReferenceType());
        assertEquals(1, ui.selectReferenceType());
        assertEquals(2, ui.selectReferenceType());
        assertEquals(3, ui.selectReferenceType());
    }

    @Test
    public void selectReferenceTypeDoesNotReturnInputsLessThanZeroOrMoreThanFour() {
        when(io.readInteger())
                .thenReturn(-1)
                .thenReturn(0)
                .thenReturn(5)
                .thenReturn(4);

        assertEquals(0, ui.selectReferenceType());
        assertEquals(4, ui.selectReferenceType());
    }

    @Test
    public void routerCanExit() {
        when(io.readInteger())
                .thenReturn(0);
        assertEquals(false, ui.run());
    }

    @Test
    public void selectReferenceToDeleteReturnsValidIndexIfReferencesExist() {
        List<Reference> references = new ArrayList<>();
        references.add(new Book());
        when(io.readInteger()).thenReturn(1);
        assertEquals(0, ui.selectReferenceToDelete(references));
    }

    @Test
    public void selectReferenceToDeleteReturnsInvalidIndexIfReferenceListIsEmpty() {
        List<Reference> references = new ArrayList<>();
        when(io.readInteger()).thenReturn(1);
        assertEquals(-1, ui.selectReferenceToDelete(references));
    }

    @Test
    public void printReferencesPrintsAllReferences() {
        List<Reference> references = new ArrayList<>();
        references.add(new Book());
        references.add(new Book());
        references.add(new Book());
        ui.printReferences(references);
        verify(io, times(3)).write(anyString());
    }

    @Test
    public void testRunPrintsInstructions() {
        ui.run();
        verify(io, times(3)).write(anyString());
    }

    @Test
    public void printNoReferencesWhenListIsEmpty() {
        StubIO stubb = new StubIO();
        ArrayList<String> prints = new ArrayList<>(Arrays.asList("No references."));
        ui.printReferences(new ArrayList<Reference>());
        verify(io, times(1)).write("No references.");
    }

    @Test
    public void askFilenameAsksForFilenameUntilNonEmptyInputIsGiven() {
        when(io.readline())
                .thenReturn("")
                .thenReturn("")
                .thenReturn("lol");

        assertEquals("lol", ui.askFilename());
    }

    @Test
    public void printFilenameCallsIOWrite() {
        ui.printLine("lol");
        verify(io).write(anyString());
    }

    @Test
    public void printLineCallsIoWrite() {
        ui.printLine("lol");
        verify(io).write(anyString());
    }

    @Test
    public void setPropertiesForBookAssignsPropertiesForTheBook() {
        String author = "Dostojevski, Fyodor";
        String title = "Crime and Punishment";
        String year = "1866";
        String publisher = "The Russian Messenger";

        when(io.readline())
                .thenReturn(author)
                .thenReturn("") // editor
                .thenReturn(title)
                .thenReturn(year)
                .thenReturn(publisher)
                .thenReturn("")
                .thenReturn("")
                .thenReturn("")
                .thenReturn("");
        Reference reference = new Book();
        ui.setProperties(reference);

        assertEquals(author, reference.getProperty("author"));
        assertEquals(title, reference.getProperty("title"));
        assertEquals(year, reference.getProperty("year"));
        assertEquals(publisher, reference.getProperty("publisher"));
    }
}
