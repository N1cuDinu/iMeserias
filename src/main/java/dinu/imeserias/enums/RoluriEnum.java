package dinu.imeserias.enums;

public enum RoluriEnum {
    ADMIN,
    MESERIAS,
    UTILIZATOR;
    public String getNumeRol(){
        return name();
    }
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
