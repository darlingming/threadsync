package com.dm.test.seq;


import com.dm.async.executor.Async;
import com.dm.async.executor.timer.SystemClock;
import com.dm.async.group.WorkerWrapper;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ExecutionException;

/**
 * 串行测试
 * @author DM wrote on 2019-11-20.
 */
@Log4j2
public class TestSequential {
    public static void main(String[] args) throws InterruptedException {


        SeqWorker w = new SeqWorker();
        SeqWorker1 w1 = new SeqWorker1();
        SeqWorker2 w2 = new SeqWorker2();

        SeqTimeoutWorker t = new SeqTimeoutWorker();
        WorkerWrapper<String, String> workerWrapper = new WorkerWrapper<>(w, "0", w);
        WorkerWrapper<String, String> workerWrapper1 = new WorkerWrapper<>(w1, "1", w1);
        WorkerWrapper<String, String> workerWrapper2 = new WorkerWrapper<>(w2, "2", w2);

        //1在0后面串行
        workerWrapper.addNext(workerWrapper1);
        //2在1后面串行
        workerWrapper1.addNext(workerWrapper2);


//        testNormal(workerWrapper);
//        testGroupTimeout(workerWrapper);

    }

    private static void testNormal(WorkerWrapper<String, String> workerWrapper) throws ExecutionException, InterruptedException {
        long now = SystemClock.now();
          log.info("begin-" + now);

        Async.beginWork(3500, workerWrapper);

          log.info("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        Async.shutDown();
    }

    private static void testGroupTimeout(WorkerWrapper<String, String> workerWrapper) throws ExecutionException, InterruptedException {
        long now = SystemClock.now();
          log.info("begin-" + now);

        Async.beginWork(2500, workerWrapper);

          log.info("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        Async.shutDown();
    }
}
