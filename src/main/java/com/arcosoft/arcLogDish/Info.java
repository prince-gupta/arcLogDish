package com.arcosoft.arcLogDish;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by princegupta on 15/11/17.
 */
@Component
public class Info {

    @Value("${endpoints.beans.id}")
    private String id;
    @Value("${info.app.name}")
    private String name;
    @Value("${dish.doc.path}")
    private String dataPath;
    @Value("${info.app.node.info.ip}")
    private String host;

    private boolean running = true;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDataPath() {
        return dataPath;
    }

    public String getHost() {
        return host;
    }

    public boolean isRunning() {
        return running;
    }
}
