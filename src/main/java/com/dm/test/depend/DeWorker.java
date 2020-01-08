package com.dm.test.depend;


import com.dm.async.callback.ICallback;
import com.dm.async.callback.IWorker;
import com.dm.async.worker.WorkResult;
import lombok.extern.log4j.Log4j2;

/**
 * @author DM wrote on 2019-11-20.
 */
@Log4j2
public class DeWorker implements IWorker<String, User>, ICallback<String, User> {

    @Override
    public User action(String object) {
        try {
            Thread.sleep(5000);
              log.info("-----------" +object);
        } catch (InterruptedException e) {
            log.error(" InterruptedException ",e);
        }
        return new User("user0");
    }


    @Override
    public User defaultValue() {
        return new User("default User");
    }

    @Override
    public void begin() {
        log.info(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<User> workResult) {
        log.info("worker0 的结果是：" + workResult.getResult());
    }

}
