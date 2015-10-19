package com.miniprojekti.bibtexbible.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReferenceList {

    private final HashMap<String, Reference> lista;

    public ReferenceList() {
        lista = new HashMap<>();
    }

    public void add(Reference reference) {
        int addon = 1;
        String key = reference.getID();
        if (lista.containsKey(key) && lista.get(key).equals(reference)) {
            return; // On lisätty jo aiemmin
        }
        while (lista.containsKey(reference.getID()) || reference.getID() == null || reference.getID().isEmpty()) {
            // id collision -> uusi id
            reference.setID(key + addon);
            addon++;
        }
        lista.put(reference.getID(), reference);
    }

    public void delete(String key) {
        if (lista.containsKey(key)) {
            lista.remove(key);
        }
    }
    
    public Reference getReference(String key) {
        return lista.get(key);
    }

    public void delete(int index) {
        if (index >= 0 && index < lista.size() && lista.size() > 0) {
            String key = "";
            int i = 0;
            for (String s : lista.keySet()) {
                if (index == i) {
                    key = s;
                    break;
                }
                i++;
            }
            if (!key.isEmpty()) {
                lista.remove(key);
            }
        }
    }

    public List<Reference> list() {
        ArrayList<Reference> ar = new ArrayList<>();
        for (Reference ref : lista.values()) {
            ar.add(ref);
        }
        return ar;
    }

    public void clear() {
        lista.clear();
    }

}
