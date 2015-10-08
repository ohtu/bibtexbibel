/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.domain;

import java.util.Map;

/**
 *
 * @author asjuvone
 */
public class InProceedings extends Reference {
    
    public InProceedings() {
        super();
        setPropertyDescriptions();
    }
    // pakolliset author title booktitle year

    private void setPropertyDescriptions() {
        Map<String, String> propertyDescriptions = super.getPropertyDescriptions();
        propertyDescriptions.put("author", "Author of the title");
        propertyDescriptions.put("title", "The title of the publication");
        propertyDescriptions.put("booktitle", "The booktitle");
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
