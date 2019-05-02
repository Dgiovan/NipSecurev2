package com.gio.mscuentas.Entidades;


import java.io.Serializable;

public class countModel implements Serializable {


    private String icon;
    private String nameCount;
    private String passworCount;

    public countModel(String icon, String nameCount, String passworCount) {
        this.icon = icon;
        this.nameCount = nameCount;
        this.passworCount = passworCount;
    }

    public countModel()
    {}
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
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
