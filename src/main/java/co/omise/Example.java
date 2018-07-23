package co.omise;

import co.omise.models.*;
import co.omise.requests.Request;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.lang.reflect.Type;

final class Example {
    private static final String OMISE_SKEY = "skey_test_123";

    void retrieveAccount() throws IOException, OmiseException, ClientException {
        Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
        Account account = client().sendRequest(getAccountRequest, Account.class);
        System.out.printf("account id: %s", account.getId());
    }

    void retrieveBalance() throws IOException, OmiseException, ClientException {
        Request<Balance> getBalanceRequest = new Balance.GetRequestBuilder().build();
        Balance balance = client().sendRequest(getBalanceRequest, Balance.class);
        System.out.printf("available balance: %d", balance.getAvailable());
    }

    void destroyCard() throws IOException, OmiseException, ClientException {
        Card card = client().customer("cust_test_4xsjvylia03ur542vn6")
                .cards().destroy("card_test_4xsjw0t21xaxnuzi9gs");
        System.out.printf("destroyed card: %s", card.getId());
    }

    void listCards() throws IOException, OmiseException, ClientException {
        ScopedList<Card> cards = client().customer("cust_test_4xsjvylia03ur542vn6")
                .cards().list();
        System.out.printf("returned cards: %d", cards.getData().size());
        System.out.printf("total no. of cards: %d", cards.getTotal());
    }

    void retrieveCard() throws IOException, OmiseException, ClientException {
        Request<Card> request =
                new Card.GetRequestBuilder("card_test_4xsjw0t21xaxnuzi9gs", "cust_test_4xsjvylia03ur542vn6")
                        .build();
        Card card = client().sendRequest(request, Card.class);
        System.out.printf("card last digits: %s", card.getLastDigits());
    }

    void updateCard() throws IOException, OmiseException, ClientException {
        Request<Card> request =
                new Card.UpdateRequestBuilder(
                        "card_test_4xsjw0t21xaxnuzi9gs", "cust_test_4xsjvylia03ur542vn6")
                        .expirationMonth(11)
                        .expirationYear(2017)
                        .name("Somchai Prasert")
                        .postalCode("10310")
                        .build();
        Card card = client().sendRequest(request, Card.class);
        System.out.printf("updated card: %s", card.getId());
    }

    void captureCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> captureChargeRequest =
                new Charge.CaptureRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz")
                        .build();
        Charge charge = client().sendRequest(captureChargeRequest, Charge.class);

        System.out.printf("captured charge: %s", charge.getId());
    }

    void chargeWithCard() throws IOException, OmiseException, ClientException {
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .customer("cust_test_4xtrb759599jsxlhkrb")
                        .card("card_test_4xtsoy2nbfs7ujngyyq")
                        .build();
        Charge charge = client().sendRequest(createChargeRequest, Charge.class);
        System.out.printf("created charge: %s", charge.getId());
    }

    void chargeWithCustomer() throws IOException, OmiseException, ClientException {
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .customer("cust_test_4xtrb759599jsxlhkrb")
                        .build();
        Charge charge = client().sendRequest(createChargeRequest, Charge.class);

        System.out.printf("created charge: %s", charge.getId());
    }

    void chargeWithToken() throws IOException, OmiseException, ClientException {
        Request<Token> request = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Somchai Prasert")
                        .number("4242424242424242")
                        .expirationMonth(10)
                        .expirationYear(2022)
                        .city("Bangkok")
                        .postalCode("10320")
                        .securityCode("123"))
                .build();
        Token token = client().sendRequest(request, Token.class);

        System.out.println("created token: " + token.getId());

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .card(token.getId())
                        .build();
        Charge charge = client().sendRequest(createChargeRequest, Charge.class);

        System.out.printf("created charge: %s", charge.getId());
    }

    void listCharges() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Charge>> listChargeRequest = new Charge.ListRequestBuilder().build();
        ScopedList<Charge> charges = client().sendRequest(listChargeRequest, new TypeReference<ScopedList<Charge>>() {
        });

        System.out.printf("returned charges: %d", charges.getData().size());
        System.out.printf("total no. of charges: %d", charges.getTotal());
    }

    void retrieveCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz").build();
        Charge charge = client().sendRequest(getChargeRequest, Charge.class);

        System.out.printf("charge amount: %d", charge.getAmount());
    }

    void reverseCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> reverseChargeRequest =
                new Charge.ReverseRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz").build();
        Charge charge = client().sendRequest(reverseChargeRequest, Charge.class);

        System.out.printf("charge reversal: %s", Boolean.toString(charge.isReversed()));
    }

    void updateCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> updateChargeRequest =
                new Charge.UpdateRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz")
                        .description("updated description")
                        .build();

        Charge charge = client().sendRequest(updateChargeRequest, Charge.class);

        System.out.printf("updated description: %s", charge.getDescription());
    }

    void attachCardToCustomer() throws IOException, OmiseException, ClientException {
        Customer customer = client().customers()
                .update("cust_test_4xtrb759599jsxlhkrb", new Customer.Update()
                        .card("tokn_test_4xs9408a642a1htto8z"));
        System.out.printf("updated customer: %s", customer.getId());
    }

    void createCustomerSimple() throws IOException, OmiseException, ClientException {
        Customer customer = client().customers()
                .create(new Customer.Create()
                        .email("john.doe@example.com")
                        .description("John Doe (id: 30)"));
        System.out.printf("created customer: %s", customer.getId());
    }

    void updateCustomer() throws IOException, OmiseException, ClientException {
        Customer customer = client().customers()
                .update("cust_test_4xtrb759599jsxlhkrb", new Customer.Update()
                        .email("john.smith@example.com")
                        .description("Another description"));
        System.out.printf("updated email: %s", customer.getEmail());
    }

    void destroyCustomer() throws IOException, OmiseException, ClientException {
        Customer customer = client().customers().destroy("cust_test_4xtrb759599jsxlhkrb");
        System.out.printf("destroy customer: %s", customer.getId());
    }

    void listAllDisputes() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().build();
        ScopedList<Dispute> disputes = client().sendRequest(request, new TypeReference<ScopedList<Dispute>>() {});
        System.out.printf("no. of disputes: %d", disputes.getTotal());
    }

    void listClosedDiputes() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().status(DisputeStatus.Closed).build();
        ScopedList<Dispute> disputes = client().sendRequest(request, new TypeReference<ScopedList<Dispute>>() {});
        System.out.printf("closed disputes: %d", disputes.getTotal());
    }

    void listOpenDiputes() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().status(DisputeStatus.Open).build();
        ScopedList<Dispute> disputes = client().sendRequest(request, new TypeReference<ScopedList<Dispute>>() {});
        System.out.printf("open disputes: %d", disputes.getTotal());
    }

    void listPendingDiputes() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().status(DisputeStatus.Pending).build();
        ScopedList<Dispute> disputes = client().sendRequest(request, new TypeReference<ScopedList<Dispute>>() {});
        System.out.printf("pending disputes: %d", disputes.getTotal());
    }

    void retrieveDispute() throws IOException, OmiseException, ClientException {
        Request<Dispute> request = new Dispute.GetRequestBuilder("dspt_test_4zgf15h89w8t775kcm8").build();
        Dispute dispute = client().sendRequest(request, Dispute.class);
        System.out.printf("disputed amount: %d", dispute.getAmount());
    }

    void updateDispute() throws IOException, OmiseException, ClientException {
        Request<Dispute> request = new Dispute.UpdateRequestBuilder("dspt_test_4zgf15h89w8t775kcm8")
                .message("Proofs and other information...")
                .build();
        Dispute dispute = client().sendRequest(request, Dispute.class);
        System.out.printf("updated dispute: %s", dispute.getMessage());
    }

    void listEvents() throws IOException, OmiseException, ClientException {
        ScopedList<Event> events = client().events().list();
        System.out.printf("no. of events: %d", events.getTotal());
    }

    void retrieveEvent() throws IOException, OmiseException, ClientException {
        Event event = client().events().get("evnt_test_5vxs0ajpo78");
        System.out.printf("key of event: %s", event.getKey());
    }

    void retrieveCustomer() throws IOException, OmiseException, ClientException {
        Customer customer = client().customers().get("cust_test_4xtrb759599jsxlhkrb");
        System.out.printf("customer email: %s", customer.getEmail());
    }

    void listCustomers() throws IOException, OmiseException, ClientException {
        ScopedList<Customer> customers = client().customers().list();
        System.out.printf("returned customers: %d", customers.getData().size());
        System.out.printf("total no. of customers: %d", customers.getTotal());
    }

    void createTransfer() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.CreateRequestBuilder()
                .amount(100000)
                .build();
        Transfer transfer = client().sendRequest(request, Transfer.class);
        System.out.printf("created transfer: %s", transfer.getId());
    }

    void createTransferWithRecipient() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.CreateRequestBuilder()
                .amount(100000)
                .recipient("recp_test_4z6p7e0m4k40txecj5o")
                .build();
        Transfer transfer = client().sendRequest(request, Transfer.class);
        System.out.printf("created transfer: %s", transfer.getId());
    }

    void destroyTransfer() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.DestroyRequestBuilder("trsf_test_4xs5px8c36dsanuwztf")
                .build();
        Transfer transfer = client().sendRequest(request, Transfer.class);
        System.out.printf("destroyed transfer: %s", transfer.getId());
    }

    void listTransfers() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Transfer>> request = new Transfer.ListRequestBuilder()
                .build();
        ScopedList<Transfer> transfers = client().sendRequest(request, new TypeReference<ScopedList<Transfer>>() {
        });
        System.out.printf("returned transfers: %d", transfers.getData().size());
        System.out.printf("total no. of transfers: %d", transfers.getTotal());
    }

    void retrieveTransfer() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.GetRequestBuilder("trsf_test_4xs5px8c36dsanuwztf")
                .build();
        Transfer transfer = client().sendRequest(request, Transfer.class);
        System.out.printf("transfer amount: %d", transfer.getAmount());
    }

    void updateTransfer() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.UpdateRequestBuilder("trsf_test_4xs5px8c36dsanuwztf")
                .amount(100000)
                .build();
        Transfer transfer = client().sendRequest(request, Transfer.class);
        System.out.printf("transfer amount: %d", transfer.getAmount());
    }

    void createRecipient() throws IOException, OmiseException, ClientException {
        Recipient recipient = client().recipients()
                .create(new Recipient.Create()
                        .name("Somchai Prasert")
                        .email("somchai.prasert@example.com")
                        .type(RecipientType.Individual)
                        .bankAccount(new BankAccount.Params()
                                .brand("bbl")
                                .number("1234567890")
                                .name("SOMCHAI PRASErT")));
        System.out.printf("created recipient: %s", recipient.getId());
    }

    void destroyRecipient() throws IOException, OmiseException, ClientException {
        Recipient recipient = client().recipients().destroy("recp_test_4z6p7e0m4k40txecj5o");
        System.out.printf("destroyed recipient: %s", recipient.getId());
    }

    void listRecipients() throws IOException, OmiseException, ClientException {
        ScopedList<Recipient> recipients = client().recipients().list();
        System.out.printf("returned recipients: %d", recipients.getData().size());
        System.out.printf("total no. of recipients: %d", recipients.getTotal());
    }

    void retrieveRecipient() throws IOException, OmiseException, ClientException {
        Recipient recipient = client().recipients().get("recp_test_4z6p7e0m4k40txecj5o");
        System.out.printf("recipient's email: %s", recipient.getEmail());
    }

    void updateRecipient() throws IOException, OmiseException, ClientException {
        Recipient recipient = client().recipients()
                .update("recp_test_4z6p7e0m4k40txecj5", new Recipient.Update()
                        .email("somchai@prasert.com")
                        .bankAccount(new BankAccount.Params()
                                .brand("kbank")
                                .number("1234567890")
                                .name("SOMCHAI PRASERT")));
        System.out.printf("updated recipient: %s", recipient.getId());
    }

    void createRefund() throws IOException, OmiseException, ClientException {
        Request<Refund> request = new Refund.CreateRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz")
                .amount(10000)
                .build();
        Refund refund = client().sendRequest(request, Refund.class);
        System.out.printf("created refund: %s", refund.getId());
    }

    void listRefunds() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Refund>> request = new Refund.ListRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz").build();
        ScopedList<Refund> refunds = client().sendRequest(request, new TypeReference<ScopedList<Refund>>() {
        });
        System.out.printf("total no. of refunds: %d", refunds.getTotal());
    }

    void retrieveRefund() throws IOException, OmiseException, ClientException {
        Request<Refund> request = new Refund.GetRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz", "rfnd_test_4ypebtxon6oye5o8myu").build();
        Refund refund = client().sendRequest(request, Refund.class);
        System.out.printf("refunded amount: %d", refund.getAmount());
    }

    void createToken() throws IOException, OmiseException, ClientException {
        Request<Token> request = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Somchai Prasert")
                        .number("4242424242424242")
                        .expirationMonth(10)
                        .expirationYear(2022)
                        .city("Bangkok")
                        .postalCode("10320")
                        .securityCode("123"))
                .build();
        Token token = client().sendRequest(request, Token.class);
        System.out.printf("created token: %s", token.getId());
    }

    void retrieveToken() throws IOException, OmiseException, ClientException {
        Request<Token> request = new Token.GetRequestBuilder("tokn_test_4xs9408a642a1htto8z").build();
        Token token = client().sendRequest(request, Token.class);
        System.out.printf("token last digits: %s", token.getCard().getLastDigits());
    }

    void listTransactions() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Transaction>> request = new Transaction.ListRequestBuilder().build();
        ScopedList<Transaction> transactions = client().sendRequest(request, new TypeReference<ScopedList<Transaction>>() {
        });
        System.out.printf("no. of transactions: %d", transactions.getTotal());
    }

    void retrieveTransactions() throws IOException, OmiseException, ClientException {
        Request<Transaction> request = new Transaction.GetRequestBuilder("trxn_test_4xuy2z4w5vmvq4x5pfs").build();
        Transaction transaction = client().sendRequest(request, Transaction.class);
        System.out.printf("transaction amount: %d", transaction.getAmount());
    }

    private Client client() throws ClientException {
        return new Client(OMISE_SKEY);
    }
}