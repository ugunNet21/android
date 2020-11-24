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
public class KitabModel extends SugarRecord implements Serializable {

    @SerializedName("id_imam")
    @Expose
    private Integer idImam;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getIdImam() {
        return idImam;
    }

    public void setIdImam(Integer idImam) {
        this.idImam = idImam;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

}
