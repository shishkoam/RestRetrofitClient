
package com.yo.shishkoam.restclienttest.api.models.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 22.02.2017
 */
public class Item {

    @SerializedName("transactionId")
    @Expose
    private Integer transactionId;
    @SerializedName("service")
    @Expose
    private Service service;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("commission")
    @Expose
    private Double commission;
    @SerializedName("instrumentId")
    @Expose
    private Integer instrumentId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("datePost")
    @Expose
    private String datePost;
    @SerializedName("serviceKey")
    @Expose
    private String serviceKey;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Integer getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Integer instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

}
