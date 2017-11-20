package com.arcosoft.arcLogDish.finders;

import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by princegupta on 05/11/17.
 */
public interface Finder {
    Map<String,List<String>> find(String query) throws IOException, ParseException;
}
