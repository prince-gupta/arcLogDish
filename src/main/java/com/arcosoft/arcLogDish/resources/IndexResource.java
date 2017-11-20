package com.arcosoft.arcLogDish.resources;

import com.arcosoft.arcLogDish.indexer.Indexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by princegupta on 05/11/17.
 */
@RestController
@RequestMapping("/index")
public class IndexResource {

    @Autowired
    Indexer indexer;

    @RequestMapping(value = "/reIndex", method = RequestMethod.GET)
    public String reIndex(){
        indexer.index();
        return "re-indexed";
    }
}
