package train.a2.service;

import java.util.List;
import java.util.concurrent.Future;

public interface AsyncService {
    public Future<List<Long>> generateReport();
    public Integer doneSoFar();
}
