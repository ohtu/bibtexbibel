package com.miniprojekti.bibtexbible.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ReferenceList {

    private final HashMap<String, Reference> lista;

    public ReferenceList() {
        lista = new HashMap<>();
    }

    public void add(Reference reference) {
        while (lista.containsKey(reference.getID()) || reference.getID() == null) {
            // täytyy generoida uusi id
            String key = reference.getID();
            reference.setID("Ref" + new Random().nextInt(1000));
        }
        lista.put(reference.getID(), reference);
    }

    public void delete(String key) {
        if (lista.containsKey(key)) {
            lista.remove(key);
        }
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

}
