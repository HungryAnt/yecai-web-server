/*
 * Copyright (C) 2015 Ant, Inc. All Rights Reserved.
 */
package com.antsoft.framework.utils;

/**
 * Created by sunlin05 on 2014/7/30.
 */
public class PageUtility {
    public static int getOffset(int pageNo, int pageSize) {
        if (pageNo == 0) {
            pageNo = 1;
        }
        return pageSize * (pageNo - 1);
    }
}
