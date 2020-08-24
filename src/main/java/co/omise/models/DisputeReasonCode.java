package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DisputeReasonCode {
    @JsonProperty("cancelled_recurring_transaction")
    CancelledRecurringTransaction,
    @JsonProperty("credit_not_processed")
    CreditNotProcessed,
    @JsonProperty("duplicate_processing")
    DuplicateProcessing,
    @JsonProperty("expired_card")
    ExpiredCard,
    @JsonProperty("goods_or_services_not_provided")
    GoodsOrServicesNotProvided,
    @JsonProperty("incorrect_currency")
    IncorrectCurrency,
    @JsonProperty("incorrect_transaction_amount")
    IncorrectTransactionAmount,
    @JsonProperty("late_presentment")
    LatePresentment,
    @JsonProperty("non_matching_account_number")
    NonMatchingAccountNumber,
    @JsonProperty("not_as_described_or_defective_merchandise")
    NotAsDescribedOrDefectiveMerchandise,
    @JsonProperty("not_available")
    NotAvailable,
    @JsonProperty("not_recorded")
    NotRecorded,
    @JsonProperty("other")
    Other,
    @JsonProperty("paid_by_other_means")
    PaidByOtherMeans,
    @JsonProperty("transaction_not_recognised")
    TransactionNotRecognised,
    @JsonProperty("unauthorized_charge")
    UnauthorizedCharge;
}