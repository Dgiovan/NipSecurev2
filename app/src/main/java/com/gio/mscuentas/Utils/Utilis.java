package com.gio.mscuentas.Utils;

public class Utilis {
    //Constantes

    public static final String TABLA_CUENTAS= "cuenta";
    public static final String FIELD_ICON="icon";
    public static final String FIELD_NAME="name";
    public static final String FIELD_PASSWORD="password";

    public static final String CREAR_TABLA_CUENTAS ="CREATE TABLE "+TABLA_CUENTAS+" ("+FIELD_ICON+" INTEGER,"+FIELD_NAME+" TEXT,"+FIELD_PASSWORD+" TEXT)";
}
