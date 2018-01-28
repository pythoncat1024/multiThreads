package com.cat.multi.sql;

import java.util.Objects;

/**
 * Created by cat on 2018/1/28.
 */
public class UriBean {

    private String url;
    private String destPah;

    public UriBean(String url, String destPah) {
        this.url = url;
        this.destPah = destPah;
    }

    public String getUrl() {
        return url;
    }

    public String getDestPah() {
        return destPah;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UriBean uriBean = (UriBean) o;
        return Objects.equals(url, uriBean.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
//        System.err.println("X-toString");
        return "UriBean{" +
                "url='" + url + '\'' +
                ", destPah='" + destPah + '\'' +
                '}';
    }
}
