package com.github.sfragata.completablefuture;

import java.math.BigDecimal;

public class InputParameter {

    private BigDecimal amount;

    private String code;

    private int waitTimeoutSecs;

    public InputParameter() {
    }

    public BigDecimal getAmount() {

        return this.amount;
    }

    public void setAmount(
        final BigDecimal amount) {

        this.amount = amount;
    }

    public String getCode() {

        return this.code;
    }

    public void setCode(
        final String code) {

        this.code = code;
    }

    public int getWaitTimeoutSecs() {

        return this.waitTimeoutSecs;
    }

    public void setWaitTimeoutSecs(
        final int waitTimeoutSecs) {

        this.waitTimeoutSecs = waitTimeoutSecs;
    }

    @Override
    public String toString() {

        return String.format("InputParameter [amount=%s, code=%s, waitTimeoutSecs=%s]", this.amount, this.code,
            this.waitTimeoutSecs);
    }

}
