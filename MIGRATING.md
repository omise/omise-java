# Migration Guide

### Migrating from v3 to v4

Most of the changes are related to the consistency of field, method and class names.

The benefit of having these consistencies allow the users of library to have predictable and consistent usage. 

It also allows us to update our library quickly by using code generation in future releases.

The following contain all changes from v3 to v4.

#### Schedules
* Rename `ChargeScheduling` and `TransferScheduling` to `ChargeSchedule` and `TransferSchedule` respectively.

#### Cards
* Change order of params
  - `Card.UpdateRequestBuilder(cardId, customerId)` contains wrong order of params (it should be `Card.UpdateRequestBuilder(customerId, cardId)`.

  - `Document.GetRequestBuilder(documentId, disputeId)` to `Document.GetRequestBuilder(disputeId, documentId)`

#### Date & Time
  - `Dispute.closedDate` -> `closedAt` (`closed_at` in API)
  - `Receipt.issuedDate` -> `issuedOn` (`issued_on` in API)
  - `Occurrence.scheduleDate` -> `scheduleOn` (`scheduled_on` in API)
  - `Occurrence.retryDate` -> `retryOn` (`retry_on` in API)
  - `Schedule.startDate` -> `startOn` (`start_on` in API)
  - `Schedule.endDate` -> `endOn` (`end_on` in API)
  - `Schedule.nextOccurrencesDates` -> `nextOccurrencesOn` (`next_occurrences_on` in API)

### Migrating to v3

The alteration made in the v3.0.0 of `omise-java` are breaking changes and would require code changes 
from your side if you have already been using this library from previous versions. We have put 
together a guide to make this process easier by pointing the changes to you. You can find the complete [version 3 migration guide here][1].

[1]: https://www.omise.co/omise-java-v3-migration