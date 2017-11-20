package com.arcosoft.arcLogDish.resources;

import com.arcosoft.arcLogDish.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by princegupta on 15/11/17.
 */

@RestController
@RequestMapping("/info")
public class InfoResource {

    @Autowired
    Info info;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Info getInfo() {
        return info;
    }
}
