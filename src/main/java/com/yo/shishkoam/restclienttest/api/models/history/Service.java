
package com.yo.shishkoam.restclienttest.api.models.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 22.02.2017
 */
public class Service {

    @SerializedName("serviceKey")
    @Expose
    private String serviceKey;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("amountMin")
    @Expose
    private Integer amountMin;
    @SerializedName("amountMax")
    @Expose
    private Integer amountMax;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("templateKey")
    @Expose
    private String templateKey;
    @SerializedName("pageTitle")
    @Expose
    private String pageTitle;
    @SerializedName("pageDescription")
    @Expose
    private String pageDescription;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("categoryKey")
    @Expose
    private String categoryKey;

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(Integer amountMin) {
        this.amountMin = amountMin;
    }

    public Integer getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(Integer amountMax) {
        this.amountMax = amountMax;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplateKey() {
        return templateKey;
    }

    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

}
