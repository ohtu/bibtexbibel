package com.miniprojekti.bibtexbible.ui;

import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;

public class ConsoleUITest {
    
    IO io;
    ConsoleUI ui;
    
    @Before
    public void setUp() {
        io = mock(ConsoleIO.class);
        ui = new ConsoleUI(io);
    }
    
    @Test
    public void initPrintsIntroText() {
        ui.init();
        verify(io, times(1)).write(anyString());
    }
    
    @Test
    public void exitPrintsExitText() {
        ui.exit();
        verify(io, times(1)).write(anyString());
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
    public void selectMenuOptionDoesNotReturnInputsLessThanZeroOrMoreThanFour() {
        when(io.readInteger())
            .thenReturn(-1)
            .thenReturn(0)
            .thenReturn(5)
            .thenReturn(4);
        assertEquals(0, ui.selectMenuOption());
        assertEquals(4, ui.selectMenuOption());
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
    public void selectReferenceTypeDoesNotReturnInputsLessThanZeroOrMoreThanThree() {
        when(io.readInteger())
            .thenReturn(-1)
            .thenReturn(0)
            .thenReturn(4)
            .thenReturn(3);
        assertEquals(0, ui.selectReferenceType());
        assertEquals(3, ui.selectReferenceType());
    }
     
    @Test
    public void selectReferenceToDeleteReturnsValidIndex() {
        List<Reference> references = new ArrayList<>();
        references.add(new Book());
        references.add(new Book());
        references.add(new Book());
        when(io.readInteger())
            .thenReturn(1)
            .thenReturn(2)
            .thenReturn(3);
        assertEquals(0, ui.selectReferenceToDelete(references));
        assertEquals(1, ui.selectReferenceToDelete(references));
        assertEquals(2, ui.selectReferenceToDelete(references));
    }
    
    @Test
    public void selectReferenceToDeleteDoesNotReturnInvalidIndex() {
        List<Reference> references = new ArrayList<>();
        references.add(new Book());
        references.add(new Book());
        references.add(new Book());
        when(io.readInteger())
            .thenReturn(0)
            .thenReturn(1)
            .thenReturn(4)
            .thenReturn(3);
        assertEquals(0, ui.selectReferenceToDelete(references));
        assertEquals(2, ui.selectReferenceToDelete(references));
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
    
}
