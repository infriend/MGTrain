package train.a2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import train.a2.dao.SuiteDao;
import train.a2.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    SuiteDao suiteDao = null;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int addinventory(String hotelname, String suitetype, int num) {
        return suiteDao.addNum(hotelname, suitetype, num);
    }
}
