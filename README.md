# completable-future
Example how to use CompletableFuture

##Expected output
```
main-start::main
process::main
processInputParameter::ForkJoinPool.commonPool-worker-1, input param: InputParameter [amount=1, code=XPTO1, waitTimeoutSecs=5]
java.util.concurrent.CompletableFuture@682a0b20[Not completed]
main-send::main-InputParameter [amount=1, code=XPTO1, waitTimeoutSecs=5]
process::main
processInputParameter::ForkJoinPool.commonPool-worker-1 - sleeping 5000 ms.
processInputParameter::ForkJoinPool.commonPool-worker-2, input param: InputParameter [amount=0, code=XPTO2, waitTimeoutSecs=8]
processInputParameter::ForkJoinPool.commonPool-worker-2 - sleeping 8000 ms.
java.util.concurrent.CompletableFuture@3d075dc0[Not completed]
main-send::main-InputParameter [amount=0, code=XPTO2, waitTimeoutSecs=8]


processInputParameter::ForkJoinPool.commonPool-worker-1 - status::Result [status=SUCCESS, message=null]
exceptionally::ForkJoinPool.commonPool-worker-2 - status::Result [status=ERROR, message=java.lang.IllegalArgumentException: amount can't be zero]
main-end::main
```