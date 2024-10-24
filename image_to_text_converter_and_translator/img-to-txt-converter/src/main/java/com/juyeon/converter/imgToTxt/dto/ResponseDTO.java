package com.juyeon.converter.imgToTxt.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDTO {
    private String url;
    private String original_text;
    private String translated_text;
}
