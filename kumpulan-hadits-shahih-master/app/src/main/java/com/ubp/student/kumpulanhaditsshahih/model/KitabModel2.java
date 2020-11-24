package com.ubp.student.kumpulanhaditsshahih.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Dizzay on 6/8/2017.
 */

public class KitabModel2 extends SugarRecord implements Serializable {

    int haditsKode;
    int kitabKode;
    String kitab;

    public KitabModel2() {
    }

    public KitabModel2(int haditsKode, String kitab, int kitabKode) {
        this.haditsKode = haditsKode;
        this.kitab = kitab;
        this.kitabKode = kitabKode;
    }

    public int getHaditsKode() {
        return haditsKode;
    }

    public void setHaditsKode(int haditsKode) {
        this.haditsKode = haditsKode;
    }

    public int getKitabKode() {
        return kitabKode;
    }

    public void setKitabKode(int kitabKode) {
        this.kitabKode = kitabKode;
    }

    public String getKitab() {
        return kitab;
    }

    public void setKitab(String kitab) {
        this.kitab = kitab;
    }
}
