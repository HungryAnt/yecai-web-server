package com.antsoft.yecai.model;

/**
 * Created by ant on 2015/9/30.
 */
public class TimestampPair {
    private long clientTimestampInS;
    private long serverTimestampIns;

    public long getClientTimestampInS() {
        return clientTimestampInS;
    }

    public void setClientTimestampInS(long clientTimestampInS) {
        this.clientTimestampInS = clientTimestampInS;
    }

    public long getServerTimestampIns() {
        return serverTimestampIns;
    }

    public void setServerTimestampIns(long serverTimestampIns) {
        this.serverTimestampIns = serverTimestampIns;
    }
}
