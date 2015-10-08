/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.repository;

import com.miniprojekti.bibtexbible.domain.Reference;
import java.util.List;

public interface ReferenceRepository{
    public void addReference();
    public void saveReference();
    public void updateReference(Long id);
    public List<Reference> listReferences();
}
