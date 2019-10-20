# nhsjobtest
nhsjobtest is a Maven project (created using IntelliJ) that demonstrates a JSR-303 annotation validator.

The validator is org.nhs.jobtest.constraint.WeeklyRegularAmountValidator, which validates a (weekly) regular amount. A regular amount comprises a frequency & a monetry amount. If frequency is a multiple of a week, monetary amount must be divisible to a weekly value that is a whole number of pence. If frequency is NOT a multiple of a week, no validation is performed & the regular amount is deemed to be valid.

The WeeklyRegularAmountValidator annotation has been added to org.nhs.jobtest.BsaWebPage, which represents a page controller on an NHS BSA web application.

A comprehensive set of Junit test cases has been written to test WeeklyRegularAmountValidator - org.nhs.jobtest.RegularAmountTest.

Michael Scott 
