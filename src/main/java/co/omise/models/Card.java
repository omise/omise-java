package co.omise.models;

import org.joda.time.YearMonth;

public class Card extends Model {
    private String country;
    private String city;
    private String bank;
    private String postalCode;
    private String financing;
    private String lastDigits;
    private String brand;
    private YearMonth expiration;
    private String fingerprint;
    private String name;
    private boolean securityCodeCheck;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFinancing() {
        return financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public YearMonth getExpiration() {
        return expiration;
    }

    public void setExpiration(YearMonth expiration) {
        this.expiration = expiration;
    }

    public int getExpirationYear() {
        return getExpiration().getYear();
    }

    public void setExpirationYear(int year) {
        YearMonth expiration = getExpiration();
        if (expiration == null) {
            expiration = YearMonth.now();
        }

        setExpiration(expiration.withYear(year));
    }

    public int getExpirationMonth() {
        return getExpiration().getMonthOfYear();
    }

    public void setExpirationMonth(int month) {
        YearMonth expiration = getExpiration();
        if (expiration == null) {
            expiration = YearMonth.now();
        }

        setExpiration(expiration.withMonthOfYear(month));
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSecurityCodeCheck() {
        return securityCodeCheck;
    }

    public void setSecurityCodeCheck(boolean securityCodeCheck) {
        this.securityCodeCheck = securityCodeCheck;
    }
}
