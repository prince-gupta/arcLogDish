package com.arcosoft.arcLogDish.indexer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by princegupta on 05/11/17.
 */
@Component
public abstract class Indexer {

    public abstract void index();

    @Value("${dish.re_indexing.enable}")
    boolean reIndexingEnabled;

    @PostConstruct
    @Scheduled(cron = "${dish.re_indexing.expression}")
    public void reIndex(){
        if(reIndexingEnabled)
            index();
    }
}
