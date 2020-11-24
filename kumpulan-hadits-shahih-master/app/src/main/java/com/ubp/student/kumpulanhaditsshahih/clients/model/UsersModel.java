package com.ubp.student.kumpulanhaditsshahih.clients.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Dizzay on 7/22/2017.
 */

@Table
public class UsersModel extends SugarRecord {

    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("android_version")
    @Expose
    private String androidVersion;
    @SerializedName("app_version")
    @Expose
    private String appVersion;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("last_activity_at")
    @Expose
    private String lastActivityAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(String lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
