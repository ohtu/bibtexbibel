/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.domain;

import java.util.HashMap;

/**
 *
 * @author asjuvone
 */
public class Proceedings extends Reference {
     
    public Proceedings() {
        super();
        setPropertyDescriptions();
    }
    
    public Proceedings(String title, String year) {
        super();
        setPropertyDescriptions();
        setProperty("title", title);
        setProperty("year", year);
    }

    @Override
    public boolean setProperty(String label, String value) {
        if (!getPropertyDescriptions().containsKey(label)) {
            // If label is not a property of Book
            return false;
        }
        super.setProperty(label, value);
        return true;
    }
    

    
    private void setPropertyDescriptions() {
        HashMap<String, String> propertyDescriptions = super.getPropertyDescriptions();
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
        propertyDescriptions.put("note", "NOTE ??????????????????");
        propertyDescriptions.put("key", "KEY ????????????????");
    }
}
