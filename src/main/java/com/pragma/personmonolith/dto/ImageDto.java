package com.pragma.personmonolith.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Data @Builder @Getter @Setter @Generated
public class ImageDto {

    private String id;
    private String image;
    private String imageName;

    @NotNull
    private Integer personId;
}
