package com.antsoft.framework.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunlin05 on 2014/7/14.
 */
public class PageResult<T> {
    private Page<T> page;

    public static <T> PageResult<T> createResponse(List<T> result, int count, int pageNo, int pageSize) {
        PageResult<T> pageResult = new PageResult<T>();
        pageResult.setResults(count, pageNo, pageSize, result);
        return pageResult;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public void setResults(int totalCount, int pageNo, int pageSize, List<T> result) {
        if (page == null) {
            page = new Page<T>();
        }
        page.setTotalCount(totalCount);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setResult((result == null) ? new ArrayList<T>() : result);
    }

    public static class Page<T> {
        private int totalCount;
        private int pageNo;
        private int pageSize;
        private List<T> result;

        private Page() {
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<T> getResult() {
            return result;
        }

        public void setResult(List<T> result) {
            this.result = result;
        }
    }
}
