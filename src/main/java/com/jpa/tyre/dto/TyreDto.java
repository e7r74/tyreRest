package com.jpa.tyre.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TyreDto {
    private Long id;
    private String tyreName;
    private String tyreProfile;
    private int price;
    private String tyreManufacturer;
}
