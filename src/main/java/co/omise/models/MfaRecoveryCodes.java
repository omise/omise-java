package co.omise.models;

import java.util.List;

public class MfaRecoveryCodes extends Model {
    private List<String> codes;

    public List<String> getCodes() {
        return this.codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}