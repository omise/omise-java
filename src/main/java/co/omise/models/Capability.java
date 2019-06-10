package co.omise.models;

import co.omise.Endpoint;
import co.omise.requests.RequestBuilder;
import co.omise.requests.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import okhttp3.HttpUrl;

import java.util.List;

/**
 * Represents Omise Capability object and contains its {@link RequestBuilder}
 *
 * @see <a href="https://www.omise.co/capability-api">Capability API</a>
 */
public class Capability extends Model {
    private List<String> banks;
    @JsonProperty("payment_methods")
    private List<PaymentMethod> paymentMethods;
    @JsonProperty("zero_interest_installments")
    private boolean zeroInterestInstallments;

    public Capability() {
    }

    public List<String> getBanks() {
        return banks;
    }

    public void setBanks(List<String> banks) {
        this.banks = banks;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public boolean isZeroInterestInstallments() {
        return zeroInterestInstallments;
    }

    public void setZeroInterestInstallments(boolean zeroInterestInstallments) {
        this.zeroInterestInstallments = zeroInterestInstallments;
    }

    public static class GetRequestBuilder extends RequestBuilder<Capability> {

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
