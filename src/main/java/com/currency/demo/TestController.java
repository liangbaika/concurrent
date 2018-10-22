package com.currency.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: LQ
 * @Date: 2018/10/22 21:35
 * @Description:  just   for  test
 */
@RestController
public class TestController {


    @RequestMapping("/test")
    public String test() {
        return "hello for  test";
    }
}
