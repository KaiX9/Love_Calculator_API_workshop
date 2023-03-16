package sg.edu.nus.iss.lovecalculator.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Lc implements Serializable {

    private String fname;

    private String sname;
    
    private String percentage;
    
    private String result;

    private String refId;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static Lc create(String json) throws IOException {
        Lc l = new Lc();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            l.setFname(o.getString("fname"));
            l.setSname(o.getString("sname"));
            l.setPercentage(o.getString("percentage"));
            l.setResult(o.getString("result"));
        }

        return l;
    }
    
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("fname", this.getFname())
            .add("sname", this.getSname())
            .add("percentage", this.getPercentage())
            .add("result", this.getResult())
            .add("refId", this.getRefId())
            .build();
    }
}
