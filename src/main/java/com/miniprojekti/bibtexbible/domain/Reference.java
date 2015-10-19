package com.miniprojekti.bibtexbible.domain;

import static com.miniprojekti.misc.Tool.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Reference {

    private String id;
    private final Map<String, String> propertyDescriptions;
    private final Map<String, String> propertyValues;
    private final List<String> requiredValues;

    public Reference() {
        this.propertyDescriptions = new LinkedHashMap<>();
        this.propertyValues = new HashMap<>();
        this.requiredValues = new ArrayList<>();
    }

    /**
     * Returns all the properties with name and description of the Book model.
     * The returned HashMap can be used in generating output labels to guide
     * user before entering input. Also used to check which are allowed labels
     * for this type
     *
     * @return HashMap, which contains descriptions for labels
     */
    public Map<String, String> getPropertyDescriptions() {
        return propertyDescriptions;
    }
    
    public boolean setProperty(String label, String value) {
        if (!getPropertyDescriptions().containsKey(label)) {
            return false; // incorrect label
        }
        this.propertyValues.put(label, value);
        return true;
    }

    public String getID() {
        if (id == null) {
            id = "";
            if (getProperty("author") != null) {
                id += safeSubstring(getProperty("author"), 0, 4); // truncate antaa ekat 4 merkkiä
            } else if (getProperty("title") != null) {
                id += safeSubstring(getProperty("title"), 0, 4);
            }
            id += getProperty("year");
        }
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getProperty(String label) {
        return this.propertyValues.get(label);
    }

    public String toBibTex() {
        String s = "@" + getType(this) + "{";
        s += getID();
        s += ", \r\n";
        for (String label : getPropertyDescriptions().keySet()) {
            String value = getProperty(label);
            if (value.isEmpty()) continue;
            s += label + " = " + "\"" + value + "\"" + ", \r\n";
        }
        s += "}";
        return scandisToBibtex(s);
    }

    /**
     * Equals compares everything except ID
     *
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Reference comp = (Reference) obj;
        for (String label : getPropertyDescriptions().keySet()) {
            if (!(comp.getProperty(label) + "").equals("" + this.getProperty(label))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String s = "@" + getType(this) + ": ";
        s += "Key" + " = " + getID() + ", ";
        for (String label : getPropertyDescriptions().keySet()) {
            String value = getProperty(label);
            if (value != null) {
                s += label + " = " + value + ", ";
            }
        }
        return s;
    }

}
