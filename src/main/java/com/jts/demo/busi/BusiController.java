package com.jts.demo.busi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/busi")
public class BusiController {

    @GetMapping(path = "/index")
    public String busiIndex(String param){
        log.info("invocked BusiController.busiIndex[{}]",param);
        return "busiIndex_"+param;
    }

}
