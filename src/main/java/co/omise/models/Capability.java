package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * Capability object
 *
 * @see <a href="https://www.omise.co/capability-api">Capability API</a>
 */
public class Capability extends Model {
    private List<String> banks;
    private String country;
    private String location;
    @JsonProperty("payment_methods")
    private List<PaymentMethod> paymentMethods;
    @JsonProperty("zero_interest_installments")
    private boolean zeroInterestInstallments;

    public List<String> getBanks() {
        return this.banks;
    }

    public void setBanks(List<String> banks) {
        this.banks = banks;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return this.paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public boolean isZeroInterestInstallments() {
        return this.zeroInterestInstallments;
    }

    public void setZeroInterestInstallments(boolean zeroInterestInstallments) {
        this.zeroInterestInstallments = zeroInterestInstallments;
    }

    public static class GetRequestBuilder extends RequestBuilder<Capability> {

        @Override
        protected String method() {
            return GET;
        }

        @Override
        protected HttpUrl path() {
            return buildUrl(Endpoint.API, "capability");
        }

        @Override
        protected ResponseType<Capability> type() {
            return new ResponseType<>(Capability.class);
        }
    }
}