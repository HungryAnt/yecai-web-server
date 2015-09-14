/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;

/**
 * Created by sunlin05 on 2015/2/25.
 */
public class XmlUtility {
    public static <T> String toXmlString(T obj, Class<T> type) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            jaxbContext.createMarshaller().marshal(obj, byteArrayOutputStream);
            return byteArrayOutputStream.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
