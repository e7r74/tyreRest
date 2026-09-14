package com.jpa.tyre.mappers;

import com.jpa.tyre.dto.TyreDto;
import com.jpa.tyre.model.Tyre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TyreMapper {
    @Mapping(source = "name", target = "tyreName")
    @Mapping(source = "profile", target="tyreProfile")
    @Mapping(source = "manufacturer", target = "tyreManufacturer")
    TyreDto toDto(Tyre tyre);


    @Mapping(source = "tyreName", target = "name")
    @Mapping(source = "tyreProfile",target = "profile")
    @Mapping(source = "tyreManufacturer", target = "manufacturer")
    Tyre toEntity(TyreDto tyreDto);

    List<TyreDto> toDtoList(List<Tyre> tyres);
}
