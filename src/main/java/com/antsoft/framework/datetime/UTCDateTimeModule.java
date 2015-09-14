/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.datetime;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.time.DateTime;

/**
 * Created by sunlin05 on 2015/2/4.
 */
public class UTCDateTimeModule extends SimpleModule {
    public UTCDateTimeModule() {
        super();
        addSerializer(DateTime.class, new UTCDateTimeSerializer());
        addDeserializer(DateTime.class, new UTCDateTimeDeserializer());
    }
}
