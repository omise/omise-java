package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DisputeReasonCode {
    @JsonProperty("cancelled_recurring_transaction")cancelledRecurringTransaction,
    @JsonProperty("credit_not_processed")creditNotProcessed,
    @JsonProperty("duplicate_processing")duplicateProcessing,
    @JsonProperty("expired_card")expiredCard,
    @JsonProperty("goods_or_services_not_provided")goodsOrServicesNotProvided,
    @JsonProperty("incorrect_currency")incorrectCurrency,
    @JsonProperty("incorrect_transaction_amount")incorrectTransactionAmount,
    @JsonProperty("late_presentment")latePresentment,
    @JsonProperty("non_matching_account_number")notMatchingAmountNumber,
    @JsonProperty("not_as_described_or_defective_merchandise")notAsDescribedOrDefectiveMerchandise,
    @JsonProperty("not_recorded")notRecorded,
    @JsonProperty("paid_by_other_means")paidByOtherMeans,
    @JsonProperty("transaction_not_recognised")transactionNotRecognized,
    @JsonProperty("unauthorized_charge_aka_fraud")unauthorizedCharge,
    @JsonProperty("not_available")notAvailable,
    @JsonProperty("other")other
}
