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
public class ImamModel extends SugarRecord implements Serializable {

    @SerializedName("nama_imam")
    @Expose
    private String namaImam;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getNamaImam() {
        return namaImam;
    }

    public void setNamaImam(String namaImam) {
        this.namaImam = namaImam;
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
