package com.juyeon.converter.imgToTxt;

import com.juyeon.converter.imgToTxt.dto.RequestDTO;
import com.juyeon.converter.imgToTxt.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestTemplateService {
    private final RestTemplate restTemplate;

    // 요청보낼 URL
    private final String FAST_API_SERVER_URL = "http://localhost:8000/convert";

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    public ResponseDTO translateText(RequestDTO requestDTO) {
        // 1. HttpHeader
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 2. RequestBody
        // HttpEntity = header + body
        HttpEntity<RequestDTO> entity = new HttpEntity<>(requestDTO, headers);

        // 3. HTTP 메소드
        // 4. 응답 받을 타입
        try {
            ResponseEntity<ResponseDTO> response = restTemplate.exchange(
                    FAST_API_SERVER_URL,    // 요청 URL
                    HttpMethod.POST,        // HTTP 요청 메소드
                    entity,                 // 요청 entity(헤더 + 본문)
                    ResponseDTO.class       // 응답 본문을 반환할 타입(JSON -> ResponseDTO)
            );

            log.info("=== 변환 서비스 응답 데이터 ===");
            log.info("변환 결과: {}", response.getBody());

            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
}
