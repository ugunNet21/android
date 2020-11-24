package com.ubp.student.kumpulanhaditsshahih.clients.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by Dizzay on 7/20/2017.
 */
@Table
public class HaditsModel extends SugarRecord implements Serializable {

    @SerializedName("id_bab")
    @Expose
    private Integer idBab;
    @SerializedName("isi")
    @Expose
    private String isi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    private boolean isFavorite;

    public Integer getIdBab() {
        return idBab;
    }

    public void setIdBab(Integer idBab) {
        this.idBab = idBab;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
