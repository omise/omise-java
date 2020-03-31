# CHANGE LOG

#3.1.0
**NEW** Added `PromptPay` source type to Source.
**NEW** Added `PayNow` source type to Source.
**NEW** Added `PointsCiti` source type to Source.
**NEW** Added `mobileNumber` field to Source.
**NEW** Added `scannableCode` field to Source.

#3.0.3
**NEW** Added `zeroInterestInstallments` field to Charge.
**NEW** Added `zeroInterestInstallments` field to Charge.CreateRequestBuilder.
**NEW** Added `zeroInterestInstallments` field to Source.CreateRequestBuilder.

#3.0.2
**NEW** Added `zeroInterestInstallments` field to Source.

#v3.0.1
**NEW** Added `firstDigits` field to Card.

#v3.0.0
**CHANGED** Changed the way Omise API is accessed through `Omise-Java`.
**CHANGED** Used latest API version (`2019-05-29`).
**NEW** Added `Capabilities` API.
**NEW** Added `Installment` as a source type.
**NEW** Made data models `Serializable`.
**REMOVED** Removed Guava from dependencies.

#v2.7.8
**NEW** Add metadata fields to Source.
**NEW** Add metadata fields to creating a Source.
**NEW** Add metadata fields to creating a Charge.
**NEW** Add Econtext as a Source type.
**NEW** Add TrueMoney as a Source type.

#v2.7.7
**FIXED** Updated Jackson to version 2.9.8 to fix vulnerability issue.

#v2.7.6
**FIXED** Fixed `sourceOfFund` in Charge no longer in Charge API.

#v2.7.5
**REMOVED:** Removed Sinarmas virtual account as a payment source type.

#v2.7.4
**CHANGED:** Changed variable names for Alipay barcode payment
**NEW:** Add metadata field to creating and updating a Transfer.
**NEW:** Add metadata field to creating a Refund.
**NEW:** Add metadata field to updating a Dispute.

# v2.7.3

* **NEW:** Add source's fields to support create source of Alipay Wallet.

# v2.7.2

* **FIXED:** Add missing `ChargeStatus.Expired` status.

# v.2.7.1

* **FIXED:** `omise-java` version sent `null` to User-Agent. Add manifest to sent correctly implementation version.

# v.2.7.0

* **NEW:** Support `source-api`.

# v2.6.0

* **REMOVED:** Certificate pinning.
* **NEW:** Receipts API.

# v2.5.4

* **NEW:** Transfer schedule APIs.
* **FIXED:** There is no `currency` field on TransferScheduling, it has been removed.

# v2.5.3

* **FIXED:** `installment_terms` are sent as `0` when not set. The field is now a boxed
  `Integer` so it defaults to `null` instead.

# v2.5.2

* **FIXED:** Added missing `TransferScheduling` type.
* **NEW:** Add `Schedule.charge.description` field.
* **NEW:** [Forex API](https://www.omise.co/forex-api).
* **NEW:** [Installments](https://www.omise.co/installment-payment).
* **NEW:** Support for fail-fast transfers.

# v2.5.1

* **NEW:** New `metadata` for Charge and Customer objects.

# v2.5.0

* **CHANGED:** Library now sends parameters using JSON encoding so that we can more easily
  support future APIs.
* **NEW:** [Schedules](https://www.omise.co/schedules-api) and
  [Occurrences](https://www.omise.co/occurrences-api) API.
* **NEW:** New offsite type: `alipay` for [Alipay](https://www.omise.co/alipay)

# v2.4.2

* **FIXED:** Initial CHANGELOG.md file creation.
