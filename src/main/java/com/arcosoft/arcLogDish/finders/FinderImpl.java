package com.arcosoft.arcLogDish.finders;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by princegupta on 05/11/17.
 */
@Component
public class FinderImpl implements Finder {

    @Value("${dish.index.path}")
    String indexPath;

    @Value("${endpoints.beans.id}")
    String dishId;

    @Override
    public Map<String,List<String>> find(String queryString) throws IOException, ParseException {
        String field = "contents";
        int hitsPerPage = Integer.MAX_VALUE;

        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer();

        QueryParser parser = new QueryParser(field, analyzer);
        String line = queryString;

        line = line.trim();

        Query query = parser.parse(line);
        System.out.println("Searching for: " + query.toString(field));

        Map resultMap = doPagingSearch(searcher, query, hitsPerPage);

        reader.close();
        return resultMap;
    }

    /**
     * This demonstrates a typical paging search scenario, where the search engine presents
     * pages of size n to the user. The user can then go to the next page if interested in
     * the next hits.
     * <p>
     * When the query is executed for the first time, then only enough results are collected
     * to fill 5 result pages. If the user wants to page beyond this limit, then the query
     * is executed another time and all hits are collected.
     */
    public Map<String, List<String>> doPagingSearch(IndexSearcher searcher, Query query,
                                                    int hitsPerPage) throws IOException {

        // Collect enough docs to show 5 pages
        TopDocs results = searcher.search(query, 5 * hitsPerPage);
        ScoreDoc[] hits = results.scoreDocs;

        int numTotalHits = Math.toIntExact(results.totalHits);
        System.out.println(numTotalHits + " total matching documents");

        int start = 0;
        int end = Math.min(numTotalHits, hitsPerPage);

        if (end > hits.length) {
            System.out.println("Only results 1 - " + hits.length + " of " + numTotalHits + " total matching documents collected.");
            hits = searcher.search(query, numTotalHits).scoreDocs;
        }


        List<String> resultList = new ArrayList<>();
        for (int i = start; i < hits.length; i++) {
            Document doc = searcher.doc(hits[i].doc);
            String path = doc.get("path");
            if (path != null) {
                String result = (i + 1) + ". " + path;
                System.out.println(result);
                resultList.add(result);

                String title = doc.get("title");
                if (title != null) {
                    System.out.println("   Title: " + doc.get("title"));
                }
            } else {
                System.out.println((i + 1) + ". " + "No path for this document");
            }

        }
        if(hits.length == 0){
            resultList.add("No Result Found !!");
        }
        Map<String, List<String>> resultMap = new HashMap<>();
        resultMap.put(dishId, resultList);
        return resultMap;
    }
}
