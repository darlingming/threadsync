package com.dm.test.seq;


import com.dm.async.callback.ICallback;
import com.dm.async.callback.IWorker;
import com.dm.async.executor.timer.SystemClock;
import com.dm.async.worker.WorkResult;
import lombok.extern.log4j.Log4j2;

/**
 * @author DM wrote on 2019-11-20.
 */
@Log4j2
public class SeqWorker implements IWorker<String, String>, ICallback<String, String> {

    @Override
    public String action(String object) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result = " + SystemClock.now() + "---param = " + object + " from 0";
    }


    @Override
    public String defaultValue() {
        return "worker0--default";
    }

    @Override
    public void begin() {
        //  log.info(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
              log.info("callback worker0 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker0 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}
