package com.mycabuser.retrofitmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyPromoModel {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("code_amount")
    @Expose
    private String codeAmount;
    @SerializedName("remaining_amount")
    @Expose
    private Integer remainingAmount;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCodeAmount() {
        return codeAmount;
    }

    public void setCodeAmount(String codeAmount) {
        this.codeAmount = codeAmount;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
}
