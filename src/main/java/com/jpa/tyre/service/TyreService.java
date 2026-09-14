package com.jpa.tyre.service;

import com.jpa.tyre.dto.TyreDto;
import com.jpa.tyre.model.Tyre;
import com.jpa.tyre.repository.TyreRepository;

import java.util.List;

public interface TyreService{
    List<TyreDto> getAllTyres();
    TyreDto getById(Long id);
    TyreDto addTyre(TyreDto tyreDto);
    TyreDto updateTyre(Long id, TyreDto newTyreDto);
    boolean deleteTyre(Long id);
}
