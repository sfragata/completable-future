package com.github.sfragata.completablefuture;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Process {

    public Process() {
    }

    void process(
        final InputParameter inputParameter) {

        System.out.printf("process::%s%n", printCurrentThread());

        final CompletableFuture<Result> res =
            CompletableFuture.supplyAsync(() -> processInputParameter(inputParameter)).exceptionally(exception -> {
                final Result result = new Result();
                result.setStatus(Result.Status.ERROR);
                result.setMessage(exception.getMessage());
                System.out.printf("exceptionally::%s - status::%s%n", printCurrentThread(), result);

                return result;
            });

        System.out.println(res);

    }

    Result processInputParameter(
        final InputParameter inputParameter) {

        System.out.printf("processInputParameter::%s, input param: %s%n", printCurrentThread(), inputParameter);
        final Result result = new Result();
        try {
            System.out.printf("processInputParameter::%s - sleeping %d ms.%n", printCurrentThread(),
                TimeUnit.SECONDS.toMillis(inputParameter.getWaitTimeoutSecs()));
            Thread.sleep(TimeUnit.SECONDS.toMillis(inputParameter.getWaitTimeoutSecs()));
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (inputParameter.getAmount().equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("amount can't be zero");
        }

        result.setStatus(Result.Status.SUCCESS);
        System.out.printf("processInputParameter::%s - status::%s%n", printCurrentThread(), result);

        return result;
    }

    String printCurrentThread() {

        return Thread.currentThread().getName();
    }

    public static void main(
        final String[] args) {

        System.out.printf("main-start::%s%n", Thread.currentThread().getName());

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
        System.out.printf("main-send::%s-%s%n", Thread.currentThread().getName(), inputParameter1);

        process.process(inputParameter2);
        System.out.printf("main-send::%s-%s%n", Thread.currentThread().getName(), inputParameter2);
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("main-end::%s%n", Thread.currentThread().getName());

    }

}
