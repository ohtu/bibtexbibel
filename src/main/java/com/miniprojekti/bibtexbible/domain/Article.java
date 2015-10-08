package com.miniprojekti.bibtexbible.domain;

import java.util.Map;

/**
 *
 * @author asjuvone
 */
public class Article extends Reference {

    public Article() {
        super();
        setPropertyDescriptions();
    }

    public Article(String author, String title, String year, String journal, String volume) {
        super();
        setPropertyDescriptions();
        setProperty("author", author);
        setProperty("title", title);
        setProperty("year", year);
        setProperty("journal", journal);
        setProperty("volume", volume);
    }

    @Override
    public boolean setProperty(String label, String value) {
        if (!getPropertyDescriptions().containsKey(label)) {
            // If label is not a property of Article
            return false;
        }
        super.setProperty(label, value);
        return true;
    }

    private void setPropertyDescriptions() {
        Map<String, String> propertyDescriptions = super.getPropertyDescriptions();
        propertyDescriptions.put("author", "Author of the Title");
        propertyDescriptions.put("title", "The tile of the publication");
        propertyDescriptions.put("year", "Publication year");
        propertyDescriptions.put("pages", "Pages in Journal where the Article is");
        propertyDescriptions.put("journal", "Journal where the Article was published");
        propertyDescriptions.put("volume", "Volume of the Journal");
        propertyDescriptions.put("number", "Number of the Journal");
        propertyDescriptions.put("month", "Month of the publication");
    }

}
