package com.dm.test.depend;

import com.dm.async.executor.Async;
import com.dm.async.group.WorkerWrapper;
import com.dm.async.worker.WorkResult;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.ExecutionException;


/**
 * 后面请求依赖于前面请求的执行结果
 *
 * @author DM wrote on 2019-12-26
 * @version 1.0
 */
@Log4j2
public class Test {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("==========");

        DeWorker w = new DeWorker();
        DeWorker1 w1 = new DeWorker1();
        DeWorker2 w2 = new DeWorker2();

        WorkerWrapper<String, User> workerWrapper = new WorkerWrapper<>(w, "0", w);
        //虽然尚未执行，但是也可以先取得结果的引用，作为下一个任务的入参
        WorkResult<User> result = workerWrapper.getWorkResult();

        WorkerWrapper<WorkResult<User>, User> workerWrapper1 = new WorkerWrapper<>(w1, result, w1);
        WorkResult<User> result1 = workerWrapper1.getWorkResult();

        WorkerWrapper<WorkResult<User>, String> workerWrapper2 = new WorkerWrapper<>(w2, result1, w2);

        workerWrapper.addNext(workerWrapper1);
        workerWrapper1.addNext(workerWrapper2);

        Async.beginWork(30500, workerWrapper);

//        Async.beginWorkAsync(30500,null, workerWrapper);
        log.info(Async.getThreadCount());
        log.info(workerWrapper2.getWorkResult());
        Async.shutDown();
    }
}
