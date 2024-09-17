# CHANGE LOG

## v.5.1.0

- **NEW** Add missing request params in request models ([#178](https://github.com/omise/omise-java/pull/178))
- **UPDATE** Update okhttp ([#180](https://github.com/omise/omise-java/pull/180))

## v.5.0.0

- **UPDATE** Update guava test dependency ([#168](https://github.com/omise/omise-java/pull/168))
- **UPDATE** Update gradle to 8.7 and JDK to 21 ([#171](https://github.com/omise/omise-java/pull/171))
- **BREAKING** Replace deprecated joda-time with java-time ([#173](https://github.com/omise/omise-java/pull/173))

## v.4.9.1

- **NEW** Add `merchant_advice` param to charge model ([#166](https://github.com/omise/omise-java/pull/166))

## v.4.9.0

- **NEW** Support wechat_pay source ([#164](https://github.com/omise/omise-java/pull/164))

## v.4.8.0

- **NEW** Support dynamic webhooks ([#161](https://github.com/omise/omise-java/pull/161))

## v.4.7.0

- **NEW** Support partial capture (#158)

## v4.6.0

- **NEW** Introducing support for `duitnow_obw` source type to facilitate charge creation for `duitnow` charges. ([#156](https://github.com/omise/omise-java/pull/156))

## v4.5.0

- **NEW** Added `Metadata field to scheduled charge creation` source types (#153)

## v4.4.0

- **NEW** Added `Atome payment method` source types (#148)

## v4.3.0

- **NEW** Added `KTB NEXT Mobile Banking` source types (#146)

## v4.2.0

- **NEW** Added `Installment UOB` and `Installment TTB` source types (#141)
- **REMOVED** `Installment Citi`

## v4.1.0

- **NEW** Added `Mobile Banking Bay`, `Mobile Banking BBL`, `Mobile Banking KBank` and `Mobile Banking OCBC PAO` source types (#141)

## v4.0.8

- **FIXED** Fix passing null if email not specified in request (#139)

## v4.0.7

- **NEW** Added `AlipayCN`, `AlipayHK`, `DANA`, `GCash`, `KakaoPay` and `TouchNGo` source type to Source.

## v4.0.6

- **NEW** Added `Rabbit Linepay` source type to Source

## v4.0.5

- **NEW** Added `FPX` source type (#127).
- **NEW** Added `bank` field to Source (#127).
- **NEW** Added support for Capability API (#127).

## v4.0.4

- **NEW** Added `AppRedirect` flow type (#125).

## v4.0.3

- **NEW** Added `MobileBankingScb` source type (#121).

## v4.0.2

- **NEW** Added `InstallmentScb` and `InstallmentCiti` source types.

## v4.0.1

- **FIXED** `PlatformFee` can't be serialized.

## v4.0.0

- See [Migration guide](MIGRATING.md#migrating-from-v3-to-v4)

## v3.1.1

- **REMOVED** Removed certificate pinning

## v3.1.0

- **NEW** Added `PromptPay` source type to Source.
- **NEW** Added `PayNow` source type to Source.
- **NEW** Added `PointsCiti` source type to Source.
- **NEW** Added `mobileNumber` field to Source.
- **NEW** Added `scannableCode` field to Source.

## v3.0.3

- **NEW** Added `zeroInterestInstallments` field to Charge.
- **NEW** Added `zeroInterestInstallments` field to Charge.CreateRequestBuilder.
- **NEW** Added `zeroInterestInstallments` field to Source.CreateRequestBuilder.

## v3.0.2

- **NEW** Added `zeroInterestInstallments` field to Source.

## 3.0.1

- **NEW** Added `firstDigits` field to Card.

## v3.0.0

- **CHANGED** Changed the way Omise API is accessed through `Omise-Java`.
- **CHANGED** Used latest API version (`2019-05-29`).
- **NEW** Added `Capabilities` API.
- **NEW** Added `Installment` as a source type.
- **NEW** Made data models `Serializable`.
- **REMOVED** Removed Guava from dependencies.

## v2.7.8

- **NEW** Add metadata fields to Source.
- **NEW** Add metadata fields to creating a Source.
- **NEW** Add metadata fields to creating a Charge.
- **NEW** Add Econtext as a Source type.
- **NEW** Add TrueMoney as a Source type.

## v2.7.7

- **FIXED** Updated Jackson to version 2.9.8 to fix vulnerability issue.

## v2.7.6

- **FIXED** Fixed `sourceOfFund` in Charge no longer in Charge API.

## v2.7.5

- **REMOVED:** Removed Sinarmas virtual account as a payment source type.

## v2.7.4

- **CHANGED:** Changed variable names for Alipay barcode payment
- **NEW:** Add metadata field to creating and updating a Transfer.
- **NEW:** Add metadata field to creating a Refund.
- **NEW:** Add metadata field to updating a Dispute.

## v2.7.3

- **NEW:** Add source's fields to support create source of Alipay Wallet.

## v2.7.2

- **FIXED:** Add missing `ChargeStatus.Expired` status.

## v2.7.1

- **FIXED:** `omise-java` version sent `null` to User-Agent. Add manifest to sent correctly implementation version.

## v2.7.0

- **NEW:** Support `source-api`.

## v2.6.0

- **REMOVED:** Certificate pinning.
- **NEW:** Receipts API.

## v2.5.4

- **NEW:** Transfer schedule APIs.
- **FIXED:** There is no `currency` field on TransferScheduling, it has been removed.

## v2.5.3

- **FIXED:** `installment_terms` are sent as `0` when not set. The field is now a boxed
  `Integer` so it defaults to `null` instead.

## v2.5.2

- **FIXED:** Added missing `TransferScheduling` type.
- **NEW:** Add `Schedule.charge.description` field.
- **NEW:** [Forex API](https://www.omise.co/forex-api).
- **NEW:** [Installments](https://www.omise.co/installment-payment).
- **NEW:** Support for fail-fast transfers.

## v2.5.1

- **NEW:** New `metadata` for Charge and Customer objects.

## v2.5.0

- **CHANGED:** Library now sends parameters using JSON encoding so that we can more easily
  support future APIs.
- **NEW:** [Schedules](https://www.omise.co/schedules-api) and
  [Occurrences](https://www.omise.co/occurrences-api) API.
- **NEW:** New offsite type: `alipay` for [Alipay](https://www.omise.co/alipay)

## v2.4.2

- **FIXED:** Initial CHANGELOG.md file creation.
