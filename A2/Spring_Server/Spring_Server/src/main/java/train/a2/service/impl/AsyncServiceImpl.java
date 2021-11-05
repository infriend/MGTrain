package train.a2.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import train.a2.service.AsyncService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AsyncServiceImpl implements AsyncService {
    private List<Long> tasksList;
    private AtomicBoolean inProgress = new AtomicBoolean(false);

    @Override
    @Async
    public Future<List<Long>> generateReport() {
        if (inProgress.compareAndSet(false, true)) {
            tasksList = Collections.synchronizedList(new ArrayList<Long>());

            for (long i=0 ; i<250000000000l ; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Cancelled");
                    inProgress.set(false);
                    return new AsyncResult<List<Long>>(tasksList);
                }
                tasksList.add(Long.valueOf(i));

                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Cancelled in sleep");
                    // thread might get interrupted during sleep not only during work
                    return new AsyncResult<List<Long>>(tasksList);
                }
            }
        }
        return new AsyncResult<List<Long>>(tasksList);
    }

    public Integer doneSoFar() {
        return tasksList.size() ;
    }
}
