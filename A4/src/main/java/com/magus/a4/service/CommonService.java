package com.magus.a4.service;

import com.magus.a4.vo.Loginvo;
import com.magus.a4.vo.Useraccount;

public interface CommonService {
    Useraccount login(Loginvo loginvo);
}
