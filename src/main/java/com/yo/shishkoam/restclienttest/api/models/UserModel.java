package com.yo.shishkoam.restclienttest.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 22.02.2017
 */
public class UserModel {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("dailyLimit")
    @Expose
    private Integer dailyLimit;
    @SerializedName("operationLimit")
    @Expose
    private Integer operationLimit;
    @SerializedName("paymentMailNotify")
    @Expose
    private Boolean paymentMailNotify;
    @SerializedName("locale")
    @Expose
    private Object locale;
    @SerializedName("isTwoFactorAuth")
    @Expose
    private Boolean isTwoFactorAuth;
    @SerializedName("infoMailNotify")
    @Expose
    private Boolean infoMailNotify;
    @SerializedName("error")
    @Expose
    private Object error;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Integer dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public Integer getOperationLimit() {
        return operationLimit;
    }

    public void setOperationLimit(Integer operationLimit) {
        this.operationLimit = operationLimit;
    }

    public Boolean getPaymentMailNotify() {
        return paymentMailNotify;
    }

    public void setPaymentMailNotify(Boolean paymentMailNotify) {
        this.paymentMailNotify = paymentMailNotify;
    }

    public Object getLocale() {
        return locale;
    }

    public void setLocale(Object locale) {
        this.locale = locale;
    }

    public Boolean getIsTwoFactorAuth() {
        return isTwoFactorAuth;
    }

    public void setIsTwoFactorAuth(Boolean isTwoFactorAuth) {
        this.isTwoFactorAuth = isTwoFactorAuth;
    }

    public Boolean getInfoMailNotify() {
        return infoMailNotify;
    }

    public void setInfoMailNotify(Boolean infoMailNotify) {
        this.infoMailNotify = infoMailNotify;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

}