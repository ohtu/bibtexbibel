package com.miniprojekti.bibtexbible.ui;

public class ConsoleUI implements UI {

    /**
     * Returns console intro as text
     *
     * @return String
     */
    @Override
    public String getConsoleIntro() {
        return "* * * * * * * * * * * * * * * * * * * * * * * * *\n"
                + " BIBTEXBIBLE - BibTex reference manager\n"
                + "* * * * * * * * * * * * * * * * * * * * * * * * *";
    }

    /**
     * Returns create objects menu with options as text
     *
     * @return String
     */
    @Override
    public String getConsoleMenu() {
        return "Choose a command:\n"
                + "1) Add a new reference\n"
                + "2) List all references\n"
                + "3) Update existing reference\n"
                + "4) Delete reference\n"
                + "0) Exit";
    }
    
    @Override
    public String getConsoleCreateMenu() {
        return "Create reference with entry type:\n"
                + "1) Book\n"
                + "0) Return";
    }

    /**
     * Returns author label
     *
     * @return String
     */
    public String getAuthorLabel() {
        return "Author:";
    }

    /**
     * Returns title label
     *
     * @return String
     */
    public String getTitleLabel() {
        return "Title:";
    }

    /**
     * Returns year label
     *
     * @return String
     */
    public String getYearLabel() {
        return "Year:";
    }

    /**
     * Returns publisher label
     *
     * @return String
     */
    public String getPublisherLabel() {
        return "Publisher:";
    }

    /**
     * Returns book title label
     *
     * @return String
     */
    public String getBookTitleLabel() {
        return "Book title:";
    }

    /**
     * Returns adress label
     *
     * @return String
     */
    public String getAddressLabel() {
        return "Address:";
    }
}
