package co.omise;

import co.omise.models.*;
import co.omise.models.schedules.*;
import co.omise.requests.Request;
import java.time.LocalDate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

final class Example {
    private static final Logger LOGGER = Logger.getLogger(Example.class.getName());
    private static final String OMISE_SKEY = "skey_test_123";
    private static final String OMISE_PKEY = "pkey_test_123";
    private static final String CREATED_TOKEN_LOG_PREFIX = "created token: ";
    private static final String CREATED_CHARGE_LOG_TEMPLATE = "created charge: %s";
    private final Client testClient;

    private static void logInfo(String template, Object... args) {
        LOGGER.log(Level.INFO, () -> String.format(template, args));
    }

    private static void logInfoMessage(String message) {
        LOGGER.log(Level.INFO, message);
    }

    Example() {
        this(null);
    }

    Example(Client testClient) {
        this.testClient = testClient;
    }

    void retrieveAccount() throws IOException, OmiseException, ClientException {
        Request<Account> getAccountRequest = new Account.GetRequestBuilder().build();
        Account account = client().sendRequest(getAccountRequest);
        logInfo("account id: %s", account.getId());
    }

    void retrieveBalance() throws IOException, OmiseException, ClientException {
        Request<Balance> getBalanceRequest = new Balance.GetRequestBuilder().build();
        Balance balance = client().sendRequest(getBalanceRequest);
        logInfo("transferable balance: %d", balance.getTransferable());
    }

    void destroyCard() throws IOException, OmiseException, ClientException {
        Request<Card> request = new Card.DeleteRequestBuilder(
                "card_test_4xsjw0t21xaxnuzi9gs", "cust_test_4xsjvylia03ur542vn6")
                .build();
        Card card = client().sendRequest(request);
        logInfo("destroyed card: %s", card.getId());
    }

    void listCards() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Card>> request =
                new Card.ListRequestBuilder("cust_test_4xsjvylia03ur542vn6").build();
        ScopedList<Card> cards = client().sendRequest(request);
        logInfo("returned cards: %d", cards.getData().size());
        logInfo("total no. of cards: %d", cards.getTotal());
    }

    void retrieveCard() throws IOException, OmiseException, ClientException {
        Request<Card> request =
                new Card.GetRequestBuilder("card_test_4xsjw0t21xaxnuzi9gs", "cust_test_4xsjvylia03ur542vn6")
                        .build();
        Card card = client().sendRequest(request);
        logInfo("card last digits: %s", card.getLastDigits());
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
        Card card = client().sendRequest(request);
        logInfo("updated card: %s", card.getId());
    }

    void captureCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> captureChargeRequest =
                new Charge.CaptureRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz")
                        .build();
        Charge charge = client().sendRequest(captureChargeRequest);

        logInfo("captured charge: %s", charge.getId());
    }

    void partialCaptureCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> captureChargeRequest =
                new Charge.CaptureRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz")
                        .captureAmount(10000)
                        .build();
        Charge charge = client().sendRequest(captureChargeRequest);

        logInfo("captured charge: %s", charge.getId());
    }

    void chargeWithCard() throws IOException, OmiseException, ClientException {
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .customer("cust_test_4xtrb759599jsxlhkrb")
                        .card("card_test_4xtsoy2nbfs7ujngyyq")
                        .build();
        Charge charge = client().sendRequest(createChargeRequest);
        logInfo(CREATED_CHARGE_LOG_TEMPLATE, charge.getId());
    }

    void chargeWithAuthentication(AuthenticationType authenticationType)
            throws IOException, OmiseException, ClientException {
        Request<Token> request = new Token.CreateRequestBuilder()
                .card(new Card.Create()
                        .name("Somchai Prasert")
                        .number("5185600630000142")
                        .expirationMonth(10)
                        .expirationYear(2030)
                        .city("Bangkok")
                        .email("demo@example.co")
                        .phoneNumber("09434343444")
                        .postalCode("10320")
                        .securityCode("123"))
                .build();
        Token token = client().sendRequest(request);
        logInfoMessage(CREATED_TOKEN_LOG_PREFIX + token.getId());

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .card(token.getId())
                        .authentication(authenticationType)
                        .returnUri("https://example.com/orders/345678/complete")
                        .build();
        Charge charge = client().sendRequest(createChargeRequest);

        logInfo(
                "created charge with %s authentication: %s and auth url: %s ",
                authenticationType.name(),
                charge.getId(),
                charge.getAuthorizeUri());
    }

    void chargeWithCustomer() throws IOException, OmiseException, ClientException {
        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .customer("cust_test_4xtrb759599jsxlhkrb")
                        .build();
        Charge charge = client().sendRequest(createChargeRequest);

        logInfo(CREATED_CHARGE_LOG_TEMPLATE, charge.getId());
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
        Token token = client().sendRequest(request);

        logInfoMessage(CREATED_TOKEN_LOG_PREFIX + token.getId());

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .card(token.getId())
                        .build();
        Charge charge = client().sendRequest(createChargeRequest);

        logInfo(CREATED_CHARGE_LOG_TEMPLATE, charge.getId());
    }

    void createPartialCaptureCharge() throws IOException, OmiseException, ClientException {
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
        Token token = client().sendRequest(request);

        logInfoMessage(CREATED_TOKEN_LOG_PREFIX + token.getId());

        Request<Charge> createChargeRequest =
                new Charge.CreateRequestBuilder()
                        .amount(100000) // 1,000 THB
                        .currency("thb")
                        .card(token.getId())
                        .authorizationType(AuthorizationType.PreAuth)
                        .capture(false)
                        .build();
        Charge charge = client().sendRequest(createChargeRequest);

        logInfo(CREATED_CHARGE_LOG_TEMPLATE, charge.getId());
    }

    void listCharges() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Charge>> listChargeRequest = new Charge.ListRequestBuilder().build();
        ScopedList<Charge> charges = client().sendRequest(listChargeRequest);

        logInfo("returned charges: %d", charges.getData().size());
        logInfo("total no. of charges: %d", charges.getTotal());
    }

    void retrieveCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> getChargeRequest = new Charge.GetRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz").build();
        Charge charge = client().sendRequest(getChargeRequest);

        logInfo("charge amount: %d", charge.getAmount());
    }

    void reverseCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> reverseChargeRequest =
                new Charge.ReverseRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz").build();
        Charge charge = client().sendRequest(reverseChargeRequest);

        logInfo("charge reversal: %s", charge.isReversed());
    }

    void updateCharge() throws IOException, OmiseException, ClientException {
        Request<Charge> updateChargeRequest =
                new Charge.UpdateRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz")
                        .description("updated description")
                        .build();

        Charge charge = client().sendRequest(updateChargeRequest);

        logInfo("updated description: %s", charge.getDescription());
    }

    void attachCardToCustomer() throws IOException, OmiseException, ClientException {
        Request<Customer> request = new Customer.UpdateRequestBuilder("cust_test_4xtrb759599jsxlhkrb")
                .card("tokn_test_4xs9408a642a1htto8z")
                .build();
        Customer customer = client().sendRequest(request);
        logInfo("updated customer: %s", customer.getId());
    }

    void createCustomerSimple() throws IOException, OmiseException, ClientException {
        Request<Customer> request = new Customer.CreateRequestBuilder()
                .email("john.doe@example.com")
                .description("John Doe (id: 30)")
                .build();
        Customer customer = client().sendRequest(request);
        logInfo("created customer: %s", customer.getId());
    }

    void updateCustomer() throws IOException, OmiseException, ClientException {
        Request<Customer> request = new Customer.UpdateRequestBuilder("cust_test_4xtrb759599jsxlhkrb")
                .email("john.smith@example.com")
                .description("Another description")
                .build();
        Customer customer = client().sendRequest(request);
        logInfo("updated email: %s", customer.getEmail());
    }

    void destroyCustomer() throws IOException, OmiseException, ClientException {
        Request<Customer> request = new Customer.DeleteRequestBuilder("cust_test_4xtrb759599jsxlhkrb").build();
        Customer customer = client().sendRequest(request);
        logInfo("destroy customer: %s", customer.getId());
    }

    void listAllDisputes() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().build();
        ScopedList<Dispute> disputes = client().sendRequest(request);
        logInfo("total no. of disputes: %d", disputes.getTotal());
    }

    void listClosedDiputes() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().status(DisputeStatus.Closed).build();
        ScopedList<Dispute> disputes = client().sendRequest(request);
        logInfo("closed disputes: %d", disputes.getTotal());
    }

    void listOpenDiputes() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().status(DisputeStatus.Open).build();
        ScopedList<Dispute> disputes = client().sendRequest(request);
        logInfo("open disputes: %d", disputes.getTotal());
    }

    void listPendingDiputes() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Dispute>> request = new Dispute.ListRequestBuilder().status(DisputeStatus.Pending).build();
        ScopedList<Dispute> disputes = client().sendRequest(request);
        logInfo("pending disputes: %d", disputes.getTotal());
    }

    void retrieveDispute() throws IOException, OmiseException, ClientException {
        Request<Dispute> request = new Dispute.GetRequestBuilder("dspt_test_4zgf15h89w8t775kcm8").build();
        Dispute dispute = client().sendRequest(request);
        logInfo("disputed amount: %d", dispute.getAmount());
    }

    void updateDispute() throws IOException, OmiseException, ClientException {
        Request<Dispute> request = new Dispute.UpdateRequestBuilder("dspt_test_4zgf15h89w8t775kcm8")
                .message("Proofs and other information...")
                .build();
        Dispute dispute = client().sendRequest(request);
        logInfo("updated dispute: %s", dispute.getMessage());
    }

    void listEvents() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Event>> request = new Event.ListRequestBuilder().build();
        ScopedList<Event> events = client().sendRequest(request);
        logInfo("total no. of events: %d", events.getTotal());
    }

    void retrieveEvent() throws IOException, OmiseException, ClientException {
        Request<Event> request = new Event.GetRequestBuilder("evnt_test_5vxs0ajpo78").build();
        Event event = client().sendRequest(request);
        logInfo("key of event: %s", event.getKey());
    }

    void retrieveCustomer() throws IOException, OmiseException, ClientException {
        Request<Customer> request = new Customer.GetRequestBuilder("cust_test_4xtrb759599jsxlhkrb").build();
        Customer customer = client().sendRequest(request);
        logInfo("customer email: %s", customer.getEmail());
    }

    void listCustomers() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Customer>> request = new Customer.ListRequestBuilder().build();
        ScopedList<Customer> customers = client().sendRequest(request);
        logInfo("returned customers: %d", customers.getData().size());
        logInfo("total no. of customers: %d", customers.getTotal());
    }

    void createTransfer() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.CreateRequestBuilder()
                .amount(100000)
                .build();
        Transfer transfer = client().sendRequest(request);
        logInfo("created transfer: %s", transfer.getId());
    }

    void createTransferWithRecipient() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.CreateRequestBuilder()
                .amount(100000)
                .recipient("recp_test_4z6p7e0m4k40txecj5o")
                .build();
        Transfer transfer = client().sendRequest(request);
        logInfo("created transfer: %s", transfer.getId());
    }

    void destroyTransfer() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.DeleteRequestBuilder("trsf_test_4xs5px8c36dsanuwztf")
                .build();
        Transfer transfer = client().sendRequest(request);
        logInfo("destroyed transfer: %s", transfer.getId());
    }

    void listTransfers() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Transfer>> request = new Transfer.ListRequestBuilder()
                .build();
        ScopedList<Transfer> transfers = client().sendRequest(request);
        logInfo("returned transfers: %d", transfers.getData().size());
        logInfo("total no. of transfers: %d", transfers.getTotal());
    }

    void retrieveTransfer() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.GetRequestBuilder("trsf_test_4xs5px8c36dsanuwztf")
                .build();
        Transfer transfer = client().sendRequest(request);
        logInfo("transfer amount: %d", transfer.getAmount());
    }

    void updateTransfer() throws IOException, OmiseException, ClientException {
        Request<Transfer> request = new Transfer.UpdateRequestBuilder("trsf_test_4xs5px8c36dsanuwztf")
                .amount(100000)
                .build();
        Transfer transfer = client().sendRequest(request);
        logInfo("transfer amount: %d", transfer.getAmount());
    }

    void createRecipient() throws IOException, OmiseException, ClientException {
        Request<Recipient> request = new Recipient.CreateRequestBuilder()
                .name("Somchai Prasert")
                .email("somchai.prasert@example.com")
                .type(RecipientType.Individual)
                .bankAccount(new BankAccount.Params()
                        .brand("bbl")
                        .number("1234567890")
                        .name("SOMCHAI PRASErT"))
                .build();
        Recipient recipient = client().sendRequest(request);
        logInfo("created recipient: %s", recipient.getId());
    }

    void destroyRecipient() throws IOException, OmiseException, ClientException {
        Request<Recipient> request = new Recipient.DeleteRequestBuilder("recp_test_4z6p7e0m4k40txecj5o").build();
        Recipient recipient = client().sendRequest(request);
        logInfo("destroyed recipient: %s", recipient.getId());
    }

    void listRecipients() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Recipient>> request = new Recipient.ListRequestBuilder().build();
        ScopedList<Recipient> recipients = client().sendRequest(request);
        logInfo("returned recipients: %d", recipients.getData().size());
        logInfo("total no. of recipients: %d", recipients.getTotal());
    }

    void retrieveRecipient() throws IOException, OmiseException, ClientException {
        Request<Recipient> request = new Recipient.GetRequestBuilder("recp_test_4z6p7e0m4k40txecj5o").build();
        Recipient recipient = client().sendRequest(request);
        logInfo("recipient's email: %s", recipient.getEmail());
    }

    void updateRecipient() throws IOException, OmiseException, ClientException {
        Request<Recipient> request = new Recipient.UpdateRequestBuilder("recp_test_4z6p7e0m4k40txecj5")
                .email("somchai@prasert.com")
                .bankAccount(new BankAccount.Params()
                        .brand("kbank")
                        .number("1234567890")
                        .name("SOMCHAI PRASERT"))
                .build();
        Recipient recipient = client().sendRequest(request);
        logInfo("updated recipient: %s", recipient.getId());
    }

    void createRefund() throws IOException, OmiseException, ClientException {
        Request<Refund> request = new Refund.CreateRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz")
                .amount(10000)
                .build();
        Refund refund = client().sendRequest(request);
        logInfo("created refund: %s", refund.getId());
    }

    void listRefunds() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Refund>> request = new Refund.ListRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz").build();
        ScopedList<Refund> refunds = client().sendRequest(request);
        logInfo("total no. of refunds: %d", refunds.getTotal());
    }

    void retrieveRefund() throws IOException, OmiseException, ClientException {
        Request<Refund> request = new Refund.GetRequestBuilder("chrg_test_4xso2s8ivdej29pqnhz", "rfnd_test_4ypebtxon6oye5o8myu").build();
        Refund refund = client().sendRequest(request);
        logInfo("refunded amount: %d", refund.getAmount());
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
        Token token = client().sendRequest(request);
        logInfo("created token: %s", token.getId());
    }

    void retrieveToken() throws IOException, OmiseException, ClientException {
        Request<Token> request = new Token.GetRequestBuilder("tokn_test_4xs9408a642a1htto8z").build();
        Token token = client().sendRequest(request);
        logInfo("token last digits: %s", token.getCard().getLastDigits());
    }

    void listTransactions() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Transaction>> request = new Transaction.ListRequestBuilder().build();
        ScopedList<Transaction> transactions = client().sendRequest(request);
        logInfo("total no. of transactions: %d", transactions.getTotal());
    }

    void retrieveTransaction() throws IOException, OmiseException, ClientException {
        Request<Transaction> request = new Transaction.GetRequestBuilder("trxn_test_4xuy2z4w5vmvq4x5pfs").build();
        Transaction transaction = client().sendRequest(request);
        logInfo("transaction amount: %d", transaction.getAmount());
    }

    void createLink() throws IOException, OmiseException, ClientException {
        Request<Link> request = new Link.CreateRequestBuilder()
                .amount(100000) // 1,000 THB
                .currency("thb")
                .title("Omise Sale")
                .description("Medium size T-Shirt (Blue)")
                .multiple(true) // can be used for multiple payments
                .build();

        Link link = client().sendRequest(request);
        logInfo("link created: %s", link.getId());
    }

    void retrieveLink() throws IOException, OmiseException, ClientException {
        Request<Link> request = new Link.GetRequestBuilder("link_test_6csdepgdsdob7ee47sf").build();

        Link link = client().sendRequest(request);
        logInfo("link retrieved: %s", link.getId());
    }

    void listLinks() throws IOException, OmiseException, ClientException {
        Request<ScopedList<Link>> request = new Link.ListRequestBuilder().build();

        ScopedList<Link> links = client().sendRequest(request);
        logInfo("total no. of links: %d", links.getTotal());
    }

    void createSource() throws IOException, OmiseException, ClientException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.InternetBankingBay)
                .amount(100000) // 1,000 THB
                .currency("thb")
                .terminalId("test_term_id")
                .storeId("test_store_id")
                .storeName("Omise Shop")
                .build();

        Source source = client().sendRequest(request);
        logInfo("source created: %s", source.getId());
    }

    void createSourceInstallment() throws IOException, ClientException, OmiseException {
        Request<Source> request = new Source.CreateRequestBuilder()
                .type(SourceType.InstallmentBay)
                .amount(500000)
                .currency("thb")
                .installmentTerm(4)
                .build();

        Source source = client().sendRequest(request);
        logInfo("source created: %s", source.getId());
    }

    void retrieveSearch() throws ClientException, IOException, OmiseException {
        Request<SearchResult<Charge>> request = new SearchResult.SearchRequestBuilder<Charge>(
                new SearchResult.Options()
                        .scope(SearchScope.Charge)
                        .query("chrg_test_4xso2s8ivdej29pqnhz"))
                .build();
        SearchResult<Charge> searchResult = client().sendRequest(request);
        logInfo("total no. of search result: %d", searchResult.getTotal());
    }

    void retrieveSchedule() throws IOException, ClientException, OmiseException {
        Request<Schedule> request = new Schedule.GetRequestBuilder("schd_test_57wedy7pc6v9i59xpbx").build();

        Schedule schedule = client().sendRequest(request);
        logInfo("schedule retrieved: %s", schedule.getId());
    }

    void listSchedule() throws IOException, ClientException, OmiseException {
        Request<ScopedList<Schedule>> request = new Schedule.ListRequestBuilder().build();

        ScopedList<Schedule> schedules = client().sendRequest(request);
        logInfo("total no. of schedules: %d", schedules.getTotal());
    }

    void listChargeSchedule() throws IOException, ClientException, OmiseException {
        Request<ScopedList<Schedule>> request = new Charge.ListSchedulesRequestBuilder().build();

        ScopedList<Schedule> schedules = client().sendRequest(request);
        logInfo("total no. of charge schedules: %d", schedules.getTotal());
    }

    void listCustomerSchedule() throws IOException, ClientException, OmiseException {
        Request<ScopedList<Schedule>> request = new Schedule.CustomerScheduleListRequestBuilder("cust_test_4yq6txdpfadhbaqnwp3").build();

        ScopedList<Schedule> schedules = client().sendRequest(request);
        logInfo("total no. of customer schedules: %d", schedules.getTotal());
    }

    void listTransferSchedule() throws IOException, ClientException, OmiseException {
        Request<ScopedList<Schedule>> request = new Schedule.TransferScheduleListRequestBuilder().build();

        ScopedList<Schedule> schedules = client().sendRequest(request);
        logInfo("total no. of transfer schedules: %d", schedules.getTotal());
    }

    void listRecipientSchedule() throws IOException, ClientException, OmiseException {
        Request<ScopedList<Schedule>> request = new Schedule.RecipientScheduleListRequestBuilder("recp_test_50894vc13y8z4v51iuc").build();

        ScopedList<Schedule> schedules = client().sendRequest(request);
        logInfo("total no. of recipient schedules: %d", schedules.getTotal());
    }

    void createSchedule() throws ClientException, IOException, OmiseException {
        Request<Schedule> request = new Schedule.CreateRequestBuilder()
                .every(1)
                .period(SchedulePeriod.Month)
                .on(new ScheduleOn.Params().daysOfMonth(2))
                .startDate(LocalDate.parse("2017-04-27"))
                .endDate(LocalDate.parse("2018-04-27"))
                .charge(new ChargeSchedule.Params()
                        .customer("cust_test_55bb3hkywglfyyachha")
                        .amount(88800)
                        .description("Monthly membership fee"))
                .build();

        Schedule schedule = client().sendRequest(request);
        logInfo("schedule created: %s", schedule.getId());
    }

    void destroySchedule() throws ClientException, IOException, OmiseException {
        Request<Schedule> request = new Schedule.DeleteRequestBuilder("schd_test_57s33hm9fg1pzcqihxs").build();

        Schedule schedule = client().sendRequest(request);
        logInfo("destroyed schedule: %s", schedule.getId());
    }

    void retrieveOccurrence() throws IOException, ClientException, OmiseException {
        Request<Occurrence> request = new Occurrence.GetRequestBuilder("occu_test_59wupnlrayrqccw6lob").build();

        Occurrence occurrence = client().sendRequest(request);
        logInfo("occurrence retrieved: %s", occurrence.getId());
    }

    void listOccurrence() throws IOException, ClientException, OmiseException {
        Request<ScopedList<Occurrence>> request = new Occurrence.ListRequestBuilder("schd_test_59wupnlq9lej6bi12i8").build();

        ScopedList<Occurrence> occurrences = client().sendRequest(request);
        logInfo("total no. of occurrences: %d", occurrences.getTotal());
    }

    void retrieveReceipt() throws ClientException, IOException, OmiseException {
        Request<Receipt> request = new Receipt.GetRequestBuilder("rcpt_59lezici7p7gt85hfwr").build();
        Receipt receipt = client().sendRequest(request);
        logInfo("retrieved receipt: %s", receipt.getId());
    }

    void listReceipt() throws ClientException, IOException, OmiseException {
        Request<ScopedList<Receipt>> request = new Receipt.ListRequestBuilder().build();
        ScopedList<Receipt> receipts = client().sendRequest(request);
        logInfo("total no. of receipts: %d", receipts.getTotal());
    }

    void getForex() throws ClientException, IOException, OmiseException {
        Request<Forex> request = new Forex.GetRequestBuilder("usd").build();
        Forex forex = client().sendRequest(request);
        logInfo("forex rate: %f", forex.getRate());
    }

    void getCapapabilities() throws ClientException, IOException, OmiseException {
        Request<Capability> request = new Capability.GetRequestBuilder().build();
        Capability capability = client().sendRequest(request);
        logInfo("capability isZeroInterestInstallments flag: %b", capability.isZeroInterestInstallments());
    }

    private Client client() throws ClientException {
        if (testClient != null) {
            return testClient;
        }

        return new Client.Builder()
                .publicKey(OMISE_PKEY)
                .secretKey(OMISE_SKEY)
                .build();
    }
}
