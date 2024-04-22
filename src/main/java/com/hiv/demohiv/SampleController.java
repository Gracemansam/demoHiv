package com.hiv.demohiv;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

        @RequestMapping("/hello")
        public String hello() {
            return "Hello from plugin";
        }
}