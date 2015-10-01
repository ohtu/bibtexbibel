package com.miniprojekti.bibtexbible.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReferenceList {

    private HashMap<String, Reference> lista;

    public ReferenceList() {
        lista = new HashMap<>();
    }

    public void add(Reference reference) {
        if (lista.containsKey(reference.getID())) {
            if (lista.get(reference.getID()) == reference) {
                return; // On lisätty jo aiemmin
            }
            // Duplicate IDs on different references
            changeID(reference); 
        }
        lista.put(reference.getID(), reference);
    }
    
    private void changeID(Reference reference) {
        int addon = 2;
        while (lista.containsKey(reference.getID() + addon)) {
            addon++;
        }
        String newID = reference.getID() + addon;
        reference.setID(newID);
    }

    public void delete(Reference reference) {
        lista.remove(reference.getID());
    }

    public List<Reference> list() {
        ArrayList<Reference> ar = new ArrayList<>();
        for (Reference ref : lista.values()) {
            ar.add(ref);
        }
        return ar;
    }

}
