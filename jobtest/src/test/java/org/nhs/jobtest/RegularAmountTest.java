package org.nhs.jobtest;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nhs.jobtest.regularamount.Frequency;
import org.nhs.jobtest.regularamount.RegularAmount;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularAmountTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        validator =
                Validation.byDefaultProvider()
                        .configure()
                        .messageInterpolator(new ParameterMessageInterpolator())
                        .buildValidatorFactory()
                        .getValidator();
    }

    @Test
    public void EsaWeekFrequencyAmountValidTest1() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("223.20"); 
        esa.setFrequency(Frequency.WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaWeekFrequencyAmountValidTest2() {
        RegularAmount esa = new RegularAmount();

        //NB: no decimal places
        esa.setAmount("223"); 
        esa.setFrequency(Frequency.WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaWeekFrequencyAmountValidTest3() {
        RegularAmount esa = new RegularAmount();

        //NB: 1 decimal place
        esa.setAmount("223.1"); 
        esa.setFrequency(Frequency.WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }



    @Test
    public void EsaWeekFrequencyAmountInvalidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("55.999"); 
        esa.setFrequency(Frequency.WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Invalid weekly regular amount",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void EsaFourWeekFrequencyAmountValidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("223.20"); 
        esa.setFrequency(Frequency.FOUR_WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaFourWeekFrequencyAmountInvalidTest1() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("55.99"); 
        esa.setFrequency(Frequency.FOUR_WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Invalid weekly regular amount",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void EsaTwoWeekFrequencyAmountValidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("1.10");
        esa.setFrequency(Frequency.TWO_WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaTwoWeekFrequencyAmountValidTest2() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("57.90");
        esa.setFrequency(Frequency.TWO_WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaTwoWeekFrequencyAmountValidTest3() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("0.36");
        esa.setFrequency(Frequency.TWO_WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaTwoWeekFrequencyAmountInvalidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("99.99"); 
        esa.setFrequency(Frequency.TWO_WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Invalid weekly regular amount",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void NonWeeklyFrequencyTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("91"); 
        esa.setFrequency(Frequency.QUARTER);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaAmountInvalidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("INVALID");
        esa.setFrequency(Frequency.TWO_WEEK);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Invalid weekly regular amount",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void EsaNullTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount(null);
        esa.setFrequency(null);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }


    @Test
    public void EsaQuarterFrequencyAmountValidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("396.5");
        esa.setFrequency(Frequency.QUARTER);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaQuarterFrequencyAmountInvalidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("40.00");
        esa.setFrequency(Frequency.QUARTER);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Invalid weekly regular amount",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void EsaYearFrequencyAmountValidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("1149.20");
        esa.setFrequency(Frequency.YEAR);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void EsaYearFrequencyAmountInvalidTest() {
        RegularAmount esa = new RegularAmount();
        esa.setAmount("51.0");
        esa.setFrequency(Frequency.YEAR);

        BsaWebPage bsa = new BsaWebPage();
        bsa.setEsa(esa);

        Set<ConstraintViolation<BsaWebPage>> constraintViolations =
                validator.validate(bsa);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "Invalid weekly regular amount",
                constraintViolations.iterator().next().getMessage());
    }
}
