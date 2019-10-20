package org.nhs.jobtest;

import org.nhs.jobtest.regularamount.RegularAmount;
import org.nhs.jobtest.constraint.WeeklyRegularAmountConstraint;

import java.util.logging.Logger;

/**
 * This represents a page on an NHS BSA web application
 * @author Michael Scott
 */
public class BsaWebPage  {

    final static Logger logger = Logger.getLogger(String.valueOf(BsaWebPage.class));

    @WeeklyRegularAmountConstraint
    private RegularAmount esa;

    public void setEsa(RegularAmount esa) {
        this.esa = esa;
    }

    public void doStuff() {
        // business rules go here
    }
}
;