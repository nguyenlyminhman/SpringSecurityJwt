package com.leesoft.admin.mappers;

import com.leesoft.admin.models.AccountInfo;
import com.leesoft.admin.models.AccountInfoDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    AccountInfo loginAccount(String accountId);
    AccountInfoDetail getAccountInfo(String accountId);

}
