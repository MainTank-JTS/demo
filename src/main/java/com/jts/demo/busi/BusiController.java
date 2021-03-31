package com.jts.demo.busi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author jts
 */
@Slf4j
@RestController
@RequestMapping("/busi")
public class BusiController {

    private final BusiService busiService;

    public BusiController(BusiService busiService) {
        this.busiService = busiService;
    }

    @GetMapping(path = "/index")
    public String busiIndex(String param){
        log.info("invocked BusiController.busiIndex[{}]",param);
        return busiService.busiIndex(param);
    }

}
