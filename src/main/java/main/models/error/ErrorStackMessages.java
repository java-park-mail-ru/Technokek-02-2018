package main.models.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ErrorStackMessages {

    private List<ErrorMessage> global;
    private HashMap<String, List<ErrorMessage>> fields;


    public ErrorStackMessages() {
        this.global = new ArrayList<>();
        this.fields  = new HashMap<>();
    }

    public void addGlobalError(ErrorMessage err) {
        if (err != null) {
            this.global.add(err);
        }
    }

    public void addFieldError(String fieldName, ErrorMessage err) {
        if (!this.fields.containsKey(fieldName)) {
            this.fields.put(fieldName, new ArrayList<>());
        }
        this.fields.get(fieldName).add(err);
    }

    public ErrorStackMessages(List<ErrorMessage> global, HashMap<String, List<ErrorMessage>> fields) {
        this.global = global;
        this.fields = fields;
    }

    public List<ErrorMessage> getGlobal() {
        return global;
    }

    public void setGlobal(List<ErrorMessage> global) {
        this.global = global;
    }

    public HashMap<String, List<ErrorMessage>> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, List<ErrorMessage>> fields) {
        this.fields = fields;
    }
}
