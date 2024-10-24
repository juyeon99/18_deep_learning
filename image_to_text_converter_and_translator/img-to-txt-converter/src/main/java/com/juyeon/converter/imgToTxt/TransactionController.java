package com.juyeon.converter.imgToTxt;

import com.juyeon.converter.imgToTxt.dto.RequestDTO;
import com.juyeon.converter.imgToTxt.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/convert")
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

        ResponseDTO result = restTemplateService.translateText(requestDTO);

        return result;
    }

    @PostMapping("/webclient")
    public ResponseDTO translateByWebClient(@RequestBody RequestDTO requestDTO) {
        log.info("/webclient called");

        ResponseDTO result = webClientService.translateText(requestDTO);

        return result;
    }
}
