package com.laungcisin.utils;

import java.io.Serializable;

public class PageDataRequestParam implements Serializable {
    private Integer page = 1;
    private Integer limit = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
