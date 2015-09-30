package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.ui.UI;
import java.util.List;

public class ReferenceController {
    
    private UI ui;
    
    public ReferenceController(UI ui) {
        this.ui = ui;
    }
    
    public boolean create() {
        return true;
    }
    
    public List<Reference> list() {
        return null;
    }
    
    public boolean update() {
        return true;
    }
    
    public boolean delete() {
        return true;
    }
}
