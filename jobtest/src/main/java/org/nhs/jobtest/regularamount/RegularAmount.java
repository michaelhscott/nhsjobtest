package org.nhs.jobtest.regularamount;

import javax.validation.constraints.NotNull;

public class RegularAmount {
    private Frequency frequency;

    private String amount;

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "[amount]" + amount + " [frequency]" + frequency;
    }
}
