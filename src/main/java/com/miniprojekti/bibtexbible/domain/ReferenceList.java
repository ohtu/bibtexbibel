package com.miniprojekti.bibtexbible.domain;

import java.util.ArrayList;
import java.util.List;

public class ReferenceList {

    private List<Reference> lista;

    public ReferenceList() {
        lista = new ArrayList<Reference>();
    }

    public void add(Reference reference) {
        lista.add(reference);
    }

    public void delete(Reference reference) {
        lista.remove(reference);
    }

    public List<Reference> list() {
        return lista;
    }

}
