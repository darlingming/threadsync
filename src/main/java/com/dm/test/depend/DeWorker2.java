package com.dm.test.depend;


import com.dm.async.callback.ICallback;
import com.dm.async.callback.IWorker;
import com.dm.async.worker.WorkResult;
import lombok.extern.log4j.Log4j2;

/**
 * @author DM wrote on 2019-11-20.
 */
@Log4j2
public class DeWorker2 implements IWorker<WorkResult<User>, String>, ICallback<WorkResult<User>, String> {

    @Override
    public String action(WorkResult<User> result) {
          log.info("par2的入参来自于par1： " + result.getResult());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.getResult().getName();
    }


    @Override
    public String defaultValue() {
        return "default";
    }

    @Override
    public void begin() {
        log.info(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, WorkResult<User> param, WorkResult<String> workResult) {
          log.info("worker2 的结果是：" + workResult.getResult());
    }

}
