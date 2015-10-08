package com.miniprojekti.bibtexbible.ui;

import com.miniprojekti.bibtexbible.domain.Reference;
import java.util.List;

public interface UI {
    
    public void init();
    public void exit();
    
    public int selectMenuOption();
    public int selectReferenceType();
    public int selectReferenceToDelete(List<Reference> references);
    public void printReferences(List<Reference> refrences);
    public String askFilename();
    public void printFilename(String filename);
}
