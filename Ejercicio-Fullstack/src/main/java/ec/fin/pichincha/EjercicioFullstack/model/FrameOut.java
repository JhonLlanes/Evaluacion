package ec.fin.pichincha.EjercicioFullstack.model;

import java.util.Map;

public class FrameOut {

    private String code;
    private String menssaje;

    public Object object;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMenssaje() {
        return menssaje;
    }

    public void setMenssaje(String menssaje) {
        this.menssaje = menssaje;
    }

    public FrameOut(String code, String menssaje) {
        this.code = code;
        this.menssaje = menssaje;
    }

    public FrameOut() {
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
