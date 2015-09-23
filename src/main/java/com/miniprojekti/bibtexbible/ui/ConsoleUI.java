package com.miniprojekti.bibtexbible.ui;

public class ConsoleUI implements UI {
    
    /**
     * Returns intro text
     * @return String
     */
    public String getIntro() {
        return "* * * * * * * * * * * * * * * * * * * * * * * * *\n" +
               " BIBTEXBIBLE - BibTex reference manager\n" +
               "* * * * * * * * * * * * * * * * * * * * * * * * *";
    }
        
    /**
     * Returns the star menu options
     * @return String
     */
    public String getStartMenu() {
        return "Choose a command:\n" +
               "1) Add a new reference\n" +
               "2) List all references\n" +
               "3) Update existing reference\n"+
               "4) Delete reference\n" +
               "0) Exit";
    }
       
    /**
     * Returns author label
     * @return String
     */
    public String getAuthorLabel() {
        return "Author:";
    }
    
    /**
     * Returns title label
     * @return String
     */
    public String getTitleLabel() {
        return "Title:";
    }
    
    /**
     * Returns year label
     * @return String
     */
    public String getYearLabel() {
        return "Year:";
    }
        
    /**
     * Returns publisher label
     * @return String
     */
    public String getPublisherLabel() {
        return "Publisher:";
    }
    
    /**
     * Returns book title label
     * @return String
     */
    public String getBookTitleLabel() {
        return "Book title:";
    }
    
    /**
     * Returns adress label
     * @return String
     */
    public String getAddressLabel() {
        return "Address:";
    }
    
}
