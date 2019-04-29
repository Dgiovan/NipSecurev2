package com.gio.mscuentas.Models;

import android.widget.ImageView;

public class countModel {


    private Integer icon;
    private String nameCount;
    private String passworCount;

   /* public countModel(Integer icon, String nameCount, String passworCount) {
        this.icon = icon;
        this.nameCount = nameCount;
        this.passworCount = passworCount;
    }*/

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getNameCount() {
        return nameCount;
    }

    public void setNameCount(String nameCount) {
        this.nameCount = nameCount;
    }

    public String getPassworCount() {
        return passworCount;
    }

    public void setPassworCount(String passworCount) {
        this.passworCount = passworCount;
    }


}
