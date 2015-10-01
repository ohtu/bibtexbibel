package com.miniprojekti.bibtexbible.ui;

import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConsoleUITest {
    
    ConsoleUI consoleUI;
    
    @Before
    public void setUp() {
        consoleUI = new ConsoleUI();
    }
    
    @Test
    public void getIntroReturnsStringThatContainsBibtexbible() {
       String introLabel = consoleUI.getConsoleIntro();
       assertTrue(introLabel.toLowerCase().contains("bibtexbible"));
    }
    
    @Test
    public void getStartMenuReturnsStringThatContainsAllOptions() {
       String menuLabel = consoleUI.getConsoleMenu();
       assertTrue(menuLabel.toLowerCase().contains("new"));
       assertTrue(menuLabel.toLowerCase().contains("list"));
       assertTrue(menuLabel.toLowerCase().contains("delete"));
       assertTrue(menuLabel.toLowerCase().contains("exit"));
    }
    
    @Test
    public void getAuthorLabelReturnsStringThatContainsAuthor() {
       String authorLabel = consoleUI.getAuthorLabel();
       assertTrue(authorLabel.toLowerCase().contains("author"));
    }
    
    @Test
    public void getTitleLabelReturnsStringThatContainsTitle() {
       String titleLabel = consoleUI.getTitleLabel();
       assertTrue(titleLabel.toLowerCase().contains("title"));
    }
    
    @Test
    public void getYearLabelReturnsStringThatContainsYear() {
       String yearLabel = consoleUI.getYearLabel();
       assertTrue(yearLabel.toLowerCase().contains("year"));
    }
    
    @Test
    public void getPublisherLabelReturnsStringThatContainsPublisher() {
       String publisherLabel = consoleUI.getPublisherLabel();
       assertTrue(publisherLabel.toLowerCase().contains("publisher"));
    }
    
    @Test
    public void getBookTitleLabelReturnsStringThatContainsBookTitle() {
       String bookTitleLabel = consoleUI.getBookTitleLabel();
       assertTrue(bookTitleLabel.toLowerCase().contains("book title"));
    }
    
    @Test
    public void getAddressLabelReturnsStringThatContainsAddress() {
       String addressLabel = consoleUI.getAddressLabel();
       assertTrue(addressLabel.toLowerCase().contains("address"));
    }
}
