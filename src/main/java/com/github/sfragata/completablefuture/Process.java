package com.github.sfragata.completablefuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Process {

    private static final Logger LOGGER = LoggerFactory.getLogger(Process.class);
    public Process() {
    }

    void process(
        final InputParameter inputParameter) {

        LOGGER.info("process::{}", printCurrentThread());

        final CompletableFuture<Result> res =
            CompletableFuture.supplyAsync(() -> processInputParameter(inputParameter)).exceptionally(exception -> {
                final Result result = new Result();
                result.setStatus(Result.Status.ERROR);
                result.setMessage(exception.getMessage());
                LOGGER.info("exceptionally::{} - status::{}", printCurrentThread(), result);

                return result;
            });

        LOGGER.info(String.valueOf(res));

    }

    Result processInputParameter(
        final InputParameter inputParameter) {

        LOGGER.info("processInputParameter::{}, input param: {}", printCurrentThread(), inputParameter);
        final Result result = new Result();
        try {
            LOGGER.info("processInputParameter::{} - sleeping {} ms.", printCurrentThread(),
                TimeUnit.SECONDS.toMillis(inputParameter.getWaitTimeoutSecs()));
            Thread.sleep(TimeUnit.SECONDS.toMillis(inputParameter.getWaitTimeoutSecs()));
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (inputParameter.getAmount().equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("amount can't be zero");
        }

        result.setStatus(Result.Status.SUCCESS);
        LOGGER.info("processInputParameter::{} - status::{}", printCurrentThread(), result);

        return result;
    }

    String printCurrentThread() {

        return Thread.currentThread().getName();
    }

    public static void main(
        final String[] args) {

        LOGGER.info("main-start::{}", Thread.currentThread().getName());

        final InputParameter inputParameter1 = new InputParameter();
        inputParameter1.setAmount(BigDecimal.ONE);
        inputParameter1.setCode("XPTO1");
        inputParameter1.setWaitTimeoutSecs(5);
        ///
        final InputParameter inputParameter2 = new InputParameter();
        inputParameter2.setAmount(BigDecimal.ZERO);
        inputParameter2.setCode("XPTO2");
        inputParameter2.setWaitTimeoutSecs(8);

        final Process process = new Process();

        process.process(inputParameter1);
        LOGGER.info("main-send::{}-{}", Thread.currentThread().getName(), inputParameter1);

        process.process(inputParameter2);
        LOGGER.info("main-send::{}-{}", Thread.currentThread().getName(), inputParameter2);
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("main-end::{}", Thread.currentThread().getName());

    }

}
