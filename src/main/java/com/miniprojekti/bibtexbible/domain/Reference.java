package com.miniprojekti.bibtexbible.domain;

import static com.miniprojekti.misc.Tool.getType;
import static com.miniprojekti.misc.Tool.replaceScandisForBibTex;
import java.util.HashMap;

public abstract class Reference {

    private String id;
    private final HashMap<String, String> propertyDescriptions;
    private final HashMap<String, String> propertyValues;

    public Reference() {
        this.propertyDescriptions = new HashMap<>();
        this.propertyValues = new HashMap<>();
    }

    /**
     * Returns all the properties with name and description of the Book model.
     * The returned HashMap can be used in generating output labels to guide
     * user before entering input. Also used to check which are allowed labels
     * for this type
     */
    public HashMap<String, String> getPropertyDescriptions() {
        return propertyDescriptions;
    }

    public boolean setProperty(String label, String value) {
        this.propertyValues.put(label, value);
        return true; // boolean, jotta sub luokat voi tarkistaa että label sallittu
    }

    /*
     * ID yleensä muotoa "Tolk1964" (Tolkien, 1964 julkaisu)
     */
    public String getID() {
        if (id == null) {
            if (getProperty("author") == null) {
                return "empty";
            }
            id = getProperty("author").substring(0, 4);
            id += getProperty("year");
        }
        return id;
    }

    /**
     * We may run into duplicate IDs - in this case the ID of a Reference may
     * need to be changed
     */
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
            if (getProperty(label) == null) {
                continue;
            }
            s += label + " = " + "\"" + getProperty(label) + "\"";
            s += ", \r\n";
        }
        s += "}";
        return replaceScandisForBibTex(s);
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
        for (String label : getPropertyDescriptions().keySet()) {
            String value = getProperty(label);
            if (value != null) {
                s += label + " = " + value + ", ";
            }
        }
        return s;
    }

}
