package com.arcosoft.arcLogDish.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by princegupta on 05/11/17.
 */
@RestController
@RequestMapping("/health")
public class HealthResource {

    @RequestMapping(value = "/",  method= RequestMethod.GET)
    public boolean isAlive(){
        return true;
    }
}


