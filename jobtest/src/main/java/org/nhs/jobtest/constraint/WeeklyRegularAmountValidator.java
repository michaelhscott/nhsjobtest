package org.nhs.jobtest.constraint;


import org.apache.log4j.Logger;
import org.nhs.jobtest.regularamount.RegularAmount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.MathContext;


/**
 * Validate a Regular Amount for a weekly payment
 *
 * @author Michael Scott
 */
public class WeeklyRegularAmountValidator implements
        ConstraintValidator<WeeklyRegularAmountConstraint, RegularAmount> {

    final static Logger logger = Logger.getLogger(String.valueOf(WeeklyRegularAmountValidator.class));

    @Override
    public void initialize(WeeklyRegularAmountConstraint RegularAmount) {
    }

    @Override
    /**
     * Validates a weekly regular amount.
     * Amounts that are not weekly are deemed to be valid.
     */
    public boolean isValid(RegularAmount regularAmount,
                           ConstraintValidatorContext cxt) {
        logger.trace("Entered WeeklyRegularAmountValidator.isValid() with " + regularAmount);

        BigDecimal weeklyAmt = null;
        boolean valid = false;

        // if frequency==null is prohibited then add @NotNull to RegularAmount.frequency
        if (regularAmount.getFrequency() == null) {
            logger.info("frequency is null, assume valid");
            valid = true;
        } else {
            try {
                switch (regularAmount.getFrequency()) {
                    case WEEK:
                        weeklyAmt = new BigDecimal(regularAmount.getAmount());
                        break;

                    case TWO_WEEK:
                        weeklyAmt = new BigDecimal(regularAmount.getAmount()).divide(new BigDecimal(2), MathContext.DECIMAL128);
                        break;

                    case FOUR_WEEK:
                        weeklyAmt = new BigDecimal(regularAmount.getAmount()).divide(new BigDecimal(4), MathContext.DECIMAL128);
                        break;

                    case QUARTER:
                        weeklyAmt = new BigDecimal(regularAmount.getAmount()).divide(new BigDecimal(13), MathContext.DECIMAL128);
                        break;

                    case YEAR:
                        weeklyAmt = new BigDecimal(regularAmount.getAmount()).divide(new BigDecimal(52), MathContext.DECIMAL128);
                        break;

                    default:
                        logger.debug("Frequency is not a multiple of week, assume amount is valid");
                        valid = true;
                }

                if (weeklyAmt != null) {
                    // validating a weekly frequency amount
                    logger.debug("weekly amount = " + weeklyAmt);
                    valid = weeklyAmt.scale() < 3;
                }
            } catch (Exception e) {
                // if amount==null is prohibited then add @NotNull to RegularAmount.amount
                logger.debug("weekly amount " + regularAmount.getAmount() + " is invalid");
            }
        }

        logger.trace("Exiting WeeklyRegularAmountValidator.isValid(), valid = " + valid);

        return valid;
    }
}
