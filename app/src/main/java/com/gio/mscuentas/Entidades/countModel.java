package com.gio.mscuentas.Entidades;


import java.io.Serializable;

public class countModel implements Serializable {


    private String icon;
    private String nameCount;
    private String passworCount;
    private String hidenPasword ="***********" ;

    public countModel(String icon, String nameCount, String passworCount,String hidenPasword) {

        this.icon = icon;
        this.nameCount = nameCount;
        this.passworCount = passworCount;
        this.hidenPasword = hidenPasword;
    }

    public String getHidenPasword() {
        return hidenPasword;
    }

    public void setHidenPasword(String hidenPasword) {
        this.hidenPasword = hidenPasword;
    }

    public countModel()
    {}
    public void ChangeTexts(String text)
    {
        hidenPasword = text;
    }
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
