package com.jpa.tyre.service;

import com.jpa.tyre.model.Tyre;
import com.jpa.tyre.repository.TyreRepository;

import java.util.List;

public interface TyreService{
    List<Tyre> getAllTyres();
    Tyre getById(Long id);
    Tyre addTyre(Tyre tyre);
    Tyre updateTyre(Long id, Tyre newTyre);
    boolean deleteTyre(Long id);
}
