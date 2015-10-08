package com.miniprojekti.bibtexbible.domain;

import java.util.Map;

/**
 *
 * @author asjuvone
 */
public class Proceedings extends Reference {

    public Proceedings() {
        super();
        setPropertyDescriptions();
    }
    // pakolliset title year

    private void setPropertyDescriptions() {
        Map<String, String> propertyDescriptions = super.getPropertyDescriptions();
        propertyDescriptions.put("title", "The tile of the publication");
        propertyDescriptions.put("year", "Publication year");
        propertyDescriptions.put("editor", "Editor of the proceedings");
        propertyDescriptions.put("address", "Address of the Publisher");
        propertyDescriptions.put("volume", "Volume of the Proceedings");
        propertyDescriptions.put("number", "Number of the Proceedings");
        propertyDescriptions.put("series", "Which series is this Proceedings a part of");
        propertyDescriptions.put("publisher", "Publisher of the Proceedings");
        propertyDescriptions.put("organization", "Conference organizer");
        propertyDescriptions.put("month", "Month of the publication");
    }
}
