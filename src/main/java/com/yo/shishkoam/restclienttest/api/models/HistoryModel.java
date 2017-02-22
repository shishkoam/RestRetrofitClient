package com.yo.shishkoam.restclienttest.api.models;

import java.lang.*;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yo.shishkoam.restclienttest.api.models.history.Item;

/**
 * Created by User on 22.02.2017
 */
public class HistoryModel {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("totalFilteredCount")
    @Expose
    private Integer totalFilteredCount;
    @SerializedName("totalFilteredSum")
    @Expose
    private Integer totalFilteredSum;
    @SerializedName("pageFilteredCount")
    @Expose
    private Integer pageFilteredCount;
    @SerializedName("pageFilteredSum")
    @Expose
    private Integer pageFilteredSum;
    @SerializedName("currentPageNumber")
    @Expose
    private Integer currentPageNumber;
    @SerializedName("numberOfPages")
    @Expose
    private Integer numberOfPages;
    @SerializedName("error")
    @Expose
    private java.lang.Error error;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalFilteredCount() {
        return totalFilteredCount;
    }

    public void setTotalFilteredCount(Integer totalFilteredCount) {
        this.totalFilteredCount = totalFilteredCount;
    }

    public Integer getTotalFilteredSum() {
        return totalFilteredSum;
    }

    public void setTotalFilteredSum(Integer totalFilteredSum) {
        this.totalFilteredSum = totalFilteredSum;
    }

    public Integer getPageFilteredCount() {
        return pageFilteredCount;
    }

    public void setPageFilteredCount(Integer pageFilteredCount) {
        this.pageFilteredCount = pageFilteredCount;
    }

    public Integer getPageFilteredSum() {
        return pageFilteredSum;
    }

    public void setPageFilteredSum(Integer pageFilteredSum) {
        this.pageFilteredSum = pageFilteredSum;
    }

    public Integer getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(Integer currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public java.lang.Error getError() {
        return error;
    }

    public void setError(java.lang.Error error) {
        this.error = error;
    }

}
