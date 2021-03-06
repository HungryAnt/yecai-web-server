package com.antsoft.yecai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ant on 2015/9/19.
 */
@Service
public class GameService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${gameDeployPath}")
    private String gameDeployPath;

    public void restart() {
        try {
            String cmd = String.format(
                    "cd %s && sh stop_server.sh && sh start_server.sh", gameDeployPath);
            logger.info("begin run cmd: {}", cmd);
            Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", cmd}, null, null);
        } catch (IOException e) {
            logger.error("restart failed", e);
            throw new RuntimeException(e);
        }
    }
}
