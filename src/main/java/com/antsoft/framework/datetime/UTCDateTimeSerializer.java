/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.datetime;

import com.antsoft.framework.utils.AntConstant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.IOException;

/**
 * Created by sunlin05 on 2015/2/4.
 */
public class UTCDateTimeSerializer extends JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime dateTime,
                          JsonGenerator jsonGenerator,
                          SerializerProvider provider) throws IOException {
        String dateTimeAsString = dateTime.withZone(DateTimeZone.UTC).toString(AntConstant.DATETIME_FORMAT);
        jsonGenerator.writeString(dateTimeAsString);
    }
}
