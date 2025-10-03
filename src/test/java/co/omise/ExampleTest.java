package co.omise;

import co.omise.models.*;
import co.omise.requests.Request;
import co.omise.requests.Requester;
import co.omise.requests.ResponseType;
import co.omise.models.schedules.Schedule;
import co.omise.models.schedules.Occurrence;
import okhttp3.OkHttpClient;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;

public class ExampleTest {

    @Test
    public void coversAllExampleScenarios() throws Exception {
        Client stubClient = new Client(new MockRequester());
        Example example = new Example(stubClient);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream sink = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sink));
        try {
            example.retrieveAccount();
            example.retrieveBalance();
            example.destroyCard();
            example.listCards();
            example.retrieveCard();
            example.updateCard();
            example.captureCharge();
            example.partialCaptureCharge();
            example.chargeWithCard();
            example.chargeWithAuthentication(AuthenticationType.THREE_DS);
            example.chargeWithCustomer();
            example.chargeWithToken();
            example.createPartialCaptureCharge();
            example.listCharges();
            example.retrieveCharge();
            example.reverseCharge();
            example.updateCharge();
            example.attachCardToCustomer();
            example.createCustomerSimple();
            example.updateCustomer();
            example.destroyCustomer();
            example.listAllDisputes();
            example.listClosedDiputes();
            example.listOpenDiputes();
            example.listPendingDiputes();
            example.retrieveDispute();
            example.updateDispute();
            example.listEvents();
            example.retrieveEvent();
            example.retrieveCustomer();
            example.listCustomers();
            example.createTransfer();
            example.createTransferWithRecipient();
            example.destroyTransfer();
            example.listTransfers();
            example.retrieveTransfer();
            example.updateTransfer();
            example.createRecipient();
            example.destroyRecipient();
            example.listRecipients();
            example.retrieveRecipient();
            example.updateRecipient();
            example.createRefund();
            example.listRefunds();
            example.retrieveRefund();
            example.createToken();
            example.retrieveToken();
            example.listTransactions();
            example.retrieveTransaction();
            example.createLink();
            example.retrieveLink();
            example.listLinks();
            example.createSource();
            example.createSourceInstallment();
            example.retrieveSearch();
            example.retrieveSchedule();
            example.listSchedule();
            example.listChargeSchedule();
            example.listCustomerSchedule();
            example.listTransferSchedule();
            example.listRecipientSchedule();
            example.createSchedule();
            example.destroySchedule();
            example.retrieveOccurrence();
            example.listOccurrence();
            example.retrieveReceipt();
            example.listReceipt();
            example.getForex();
            example.getCapapabilities();
        } finally {
            System.setOut(originalOut);
        }

        Example realExample = new Example();
        Method clientMethod = Example.class.getDeclaredMethod("client");
        clientMethod.setAccessible(true);
        Client actualClient = (Client) clientMethod.invoke(realExample);
        assertNotNull(actualClient);
    }

    private static final class MockRequester implements Requester {
        private final OkHttpClient httpClient = new OkHttpClient();

        @Override
        public <T extends OmiseObjectBase, R extends Request<T>> T sendRequest(R request) {
            ResponseType<T> responseType = request.getType();

            if (responseType.isClassType()) {
                return responseType.getClassType().cast(createStubForClass(responseType.getClassType()));
            }

            if (responseType.isTypeReference()) {
                return castFromType(responseType.getTypeReference().getType());
            }

            throw new UnsupportedOperationException("Unhandled response type: " + responseType);
        }

        @SuppressWarnings("unchecked")
        private <T extends OmiseObjectBase> T castFromType(Type type) {
            Object stub = createStubForType(type);
            return (T) stub;
        }

        private OmiseObjectBase createStubForClass(Class<?> clazz) {
            if (clazz.equals(Account.class)) {
                return withId(new Account(), "acct_test");
            }
            if (clazz.equals(Balance.class)) {
                Balance balance = withId(new Balance(), "bal_test");
                balance.setTransferable(12345L);
                return balance;
            }
            if (clazz.equals(Card.class)) {
                Card card = withId(new Card(), "card_test");
                card.setLastDigits("4242");
                return card;
            }
            if (clazz.equals(Charge.class)) {
                Charge charge = withId(new Charge(), "chrg_test");
                charge.setAmount(1000L);
                charge.setDescription("desc");
                charge.setReversed(true);
                charge.setAuthorizeUri("https://example.com/auth");
                return charge;
            }
            if (clazz.equals(Token.class)) {
                Token token = withId(new Token(), "tokn_test");
                Card card = withId(new Card(), "card_token");
                card.setLastDigits("1111");
                token.setCard(card);
                return token;
            }
            if (clazz.equals(Customer.class)) {
                Customer customer = withId(new Customer(), "cust_test");
                customer.setEmail("user@example.com");
                return customer;
            }
            if (clazz.equals(Dispute.class)) {
                Dispute dispute = withId(new Dispute(), "dspt_test");
                dispute.setAmount(5000L);
                dispute.setMessage("message");
                return dispute;
            }
            if (clazz.equals(Event.class)) {
                Event event = withId(new Event(), "evnt_test");
                event.setKey("charge.completed");
                return event;
            }
            if (clazz.equals(Transfer.class)) {
                Transfer transfer = withId(new Transfer(), "trsf_test");
                transfer.setAmount(2000L);
                return transfer;
            }
            if (clazz.equals(Recipient.class)) {
                Recipient recipient = withId(new Recipient(), "recp_test");
                recipient.setEmail("recipient@example.com");
                return recipient;
            }
            if (clazz.equals(Refund.class)) {
                Refund refund = withId(new Refund(), "rfnd_test");
                refund.setAmount(3000L);
                return refund;
            }
            if (clazz.equals(Transaction.class)) {
                Transaction transaction = withId(new Transaction(), "trxn_test");
                transaction.setAmount(4000L);
                return transaction;
            }
            if (clazz.equals(Link.class)) {
                return withId(new Link(), "link_test");
            }
            if (clazz.equals(Source.class)) {
                return withId(new Source(), "src_test");
            }
            if (clazz.equals(Schedule.class)) {
                return withId(new Schedule(), "schd_test");
            }
            if (clazz.equals(Occurrence.class)) {
                return withId(new Occurrence(), "occu_test");
            }
            if (clazz.equals(Receipt.class)) {
                return withId(new Receipt(), "rcpt_test");
            }
            if (clazz.equals(Forex.class)) {
                Forex forex = withId(new Forex(), "forx_test");
                forex.setRate(1.23d);
                return forex;
            }
            if (clazz.equals(Capability.class)) {
                Capability capability = withId(new Capability(), "cap_test");
                capability.setZeroInterestInstallments(true);
                return capability;
            }

            throw new UnsupportedOperationException("Unhandled class: " + clazz.getName());
        }

        private OmiseObjectBase createStubForType(Type type) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type rawType = parameterizedType.getRawType();

                if (rawType == ScopedList.class) {
                    Type elementType = parameterizedType.getActualTypeArguments()[0];
                    Class<?> element = elementType instanceof Class
                            ? (Class<?>) elementType
                            : Charge.class;
                    ScopedList<Model> scopedList = new ScopedList<>();
                    scopedList.setTotal(1);
                    scopedList.setData(Collections.singletonList((Model) createStubForClass(element)));
                    return scopedList;
                }

                if (rawType == SearchResult.class) {
                    SearchResult<Model> searchResult = new SearchResult<>();
                    searchResult.setTotal(1);
                    searchResult.setData(Collections.singletonList((Model) createStubForClass(Charge.class)));
                    return searchResult;
                }
            }

            throw new UnsupportedOperationException("Unhandled type: " + type.getTypeName());
        }

        private <T extends Model> T withId(T model, String id) {
            model.setId(id);
            return model;
        }

        @Override
        public OkHttpClient getHttpClient() {
            return httpClient;
        }
    }
}
