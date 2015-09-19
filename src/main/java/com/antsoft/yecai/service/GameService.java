package com.antsoft.yecai.service;

import com.mangofactory.swagger.core.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ant on 2015/9/19.
 */
@Service
public class GameService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("gameDeployPath")
    private String gameDeployPath;

    public void restart() {
        try {
            Runtime.getRuntime().exec(
                    String.format(
                            "cd \"%s\" && sh stop_server.sh && sh start_server.sh", gameDeployPath));
        } catch (IOException e) {
            logger.error("restart failed", e);
        }
    }
}
