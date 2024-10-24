package com.juyeon.apirequest.section01;

import com.juyeon.apirequest.section01.dto.RequestDTO;
import com.juyeon.apirequest.section01.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/*
* Spring에서 외부 API 요청 및 처리
*
* 대표적인 라이브러리
* - HttpClient
* - RestTemplate
* - WebClient
* - OpenFeign
*
* 주의할 점
* - request와 response가 외부 서버와 맞게 설정되어 있는지 확인
* */
@RestController
@RequestMapping("/translate")
@Slf4j
public class TransactionController {
    private final RestTemplateService restTemplateService;
    private final WebClientService webClientService;

    // 생성자 주입
    public TransactionController(RestTemplateService restTemplateService, WebClientService webClientService) {
        this.restTemplateService = restTemplateService;
        this.webClientService = webClientService;
    }

    @GetMapping("/test")
    public String test() {
        log.info("/test called");
        return "요청 성공";
    }

    @PostMapping("/resttemplate")
    public ResponseDTO translateByRestTemplate(@RequestBody RequestDTO requestDTO) {
        log.info("/resttemplate called");
        log.info("text: {}, lang: {}", requestDTO.getText(), requestDTO.getLang());

        ResponseDTO result = restTemplateService.translateText(requestDTO);

        return result;
    }

    @PostMapping("/webclient")
    public ResponseDTO translateByWebClient(@RequestBody RequestDTO requestDTO) {
        log.info("/webclient called");
        log.info("text: {}, lang: {}", requestDTO.getText(), requestDTO.getLang());

        ResponseDTO result = webClientService.translateText(requestDTO);

        return result;
    }
}
