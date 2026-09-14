package com.jpa.tyre.service.impl;

import com.jpa.tyre.model.Tyre;
import com.jpa.tyre.repository.TyreRepository;
import com.jpa.tyre.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TyreServiceImpl implements TyreService {
    private final TyreRepository tyreRepository;
    @Override
    public List<Tyre> getAllTyres() {
        return tyreRepository.findAll();
    }

    @Override
    public Tyre getById(Long id) {
       Tyre tyre= tyreCheckById(id);
       if (tyre!=null){
           return tyre;
       }
       return null;
    }

    @Override
    public Tyre addTyre(Tyre tyre) {
        return tyreRepository.save(tyre);
    }

    @Override
    public Tyre updateTyre(Long id, Tyre newTyre) {
       Tyre tyre= tyreCheckById(id);
       if (tyre!=null){
           tyre.setName(newTyre.getName());
           tyre.setProfile(newTyre.getProfile());
           tyre.setPrice(newTyre.getPrice());
           tyre.setManufacturer(newTyre.getManufacturer());
           tyreRepository.save(tyre);
           return tyre;
       }
       return null;
    }

    @Override
    public boolean deleteTyre(Long id) {
        Tyre tyre= tyreCheckById(id);
        if (tyre!=null){
            tyreRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
    private Tyre tyreCheckById(Long id){
        return tyreRepository.findById(id).orElse(null);
    }
}
