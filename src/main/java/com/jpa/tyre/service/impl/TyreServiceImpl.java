package com.jpa.tyre.service.impl;

import com.jpa.tyre.dto.TyreDto;
import com.jpa.tyre.mappers.TyreMapper;
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
    private final TyreMapper tyreMapper;
    @Override
    public List<TyreDto> getAllTyres() {
        List<Tyre> tyres =tyreRepository.findAll();
       return tyreMapper.toDtoList(tyres);
    }

    @Override
    public TyreDto getById(Long id) {
       Tyre tyre= tyreCheckById(id);
       if (tyre!=null){
           return tyreMapper.toDto(tyre);
       }
       return null;
    }

    @Override
    public TyreDto addTyre(TyreDto tyreDto) {
        Tyre tyre= tyreMapper.toEntity(tyreDto);
        tyreRepository.save(tyre);
        return tyreMapper.toDto(tyre);
    }

    @Override
    public TyreDto updateTyre(Long id, TyreDto newTyreDto) {
       Tyre tyre= tyreCheckById(id);
       if (tyre!=null){
           tyre.setName(newTyreDto.getTyreName());
           tyre.setProfile(newTyreDto.getTyreProfile());
           tyre.setPrice(newTyreDto.getPrice());
           tyre.setManufacturer(newTyreDto.getTyreManufacturer());
           tyreRepository.save(tyre);
           return tyreMapper.toDto(tyre);
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



//    Without mapper interface
//    private Tyre toEntity(TyreDto tyreDto){
//        Tyre tyre=Tyre.builder()
//                .id(tyreDto.getId())
//                .name(tyreDto.getTyreName())
//                .profile(tyreDto.getTyreProfile())
//                .price(tyreDto.getPrice())
//                .manufacturer(tyreDto.getTyreManufacturer())
//                .build();
//        return tyre;
//    }
//    private TyreDto toDto(Tyre tyre){
//        TyreDto tyreDto= TyreDto.builder()
//                .id(tyre.getId())
//                .tyreName(tyre.getName())
//                .tyreProfile(tyre.getProfile())
//                .price(tyre.getPrice())
//                .tyreManufacturer(tyre.getManufacturer())
//                .build();
//        return tyreDto;
//    }

}
