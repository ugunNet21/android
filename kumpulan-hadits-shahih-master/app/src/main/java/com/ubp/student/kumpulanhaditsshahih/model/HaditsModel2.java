package com.ubp.student.kumpulanhaditsshahih.model;

import com.orm.SugarRecord;

/**
 * Created by Dizzay on 6/9/2017.
 */

public class HaditsModel2 extends SugarRecord {

    int haditsKode;
    int kitabKode;
    int babKode;
    int no;
    String hadits;

    public HaditsModel2() {
    }

    public HaditsModel2(int haditsKode, int kitabKode, int babKode, int no, String hadits) {
        this.haditsKode = haditsKode;
        this.kitabKode = kitabKode;
        this.babKode = babKode;
        this.no = no;
        this.hadits = hadits;
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

    public int getBabKode() {
        return babKode;
    }

    public void setBabKode(int babKode) {
        this.babKode = babKode;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getHadits() {
        return hadits;
    }

    public void setHadits(String hadits) {
        this.hadits = hadits;
    }
}
