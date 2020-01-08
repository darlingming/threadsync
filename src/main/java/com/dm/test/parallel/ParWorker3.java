package com.dm.test.parallel;


import com.dm.async.callback.ICallback;
import com.dm.async.callback.IWorker;
import com.dm.async.executor.timer.SystemClock;
import com.dm.async.worker.WorkResult;
import lombok.extern.log4j.Log4j2;

/**
 * @author DM wrote on 2019-11-20.
 */
@Log4j2
public class ParWorker3 implements IWorker<String, String>, ICallback<String, String> {
    private long sleepTime = 1000;

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
    @Override
    public String action(String object) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result = " + SystemClock.now() + "---param = " + object + " from 3";
    }


    @Override
    public String defaultValue() {
        return "worker3--default";
    }

    @Override
    public void begin() {
        //  log.info(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
              log.info("callback worker3 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker3 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}
