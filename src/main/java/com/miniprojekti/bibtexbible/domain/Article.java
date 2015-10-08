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
    // pakolliset String author, String title, String year, String journal, String volume) {

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
