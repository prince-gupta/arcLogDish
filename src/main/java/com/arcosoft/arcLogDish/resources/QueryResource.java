package com.arcosoft.arcLogDish.resources;

import com.arcosoft.arcLogDish.finders.Finder;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by princegupta on 05/11/17.
 */
@RestController
@RequestMapping("/query")
public class QueryResource {

    @Autowired
    Finder finder;
    @RequestMapping(method = RequestMethod.POST)
    public Map<String,List<String>> query(@RequestParam String queryString) throws IOException, ParseException {
        return finder.find(queryString);
    }
}
