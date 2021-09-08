package com.algorithm.entity;

import java.io.Serializable;
import java.util.List;

public class DataResponse<T> implements Serializable {
    private static final long serialVersionUID = -3647556529780646682L;

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
