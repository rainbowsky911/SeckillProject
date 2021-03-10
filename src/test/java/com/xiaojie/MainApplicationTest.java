package com.xiaojie;

import static org.junit.Assert.assertTrue;

import com.xiaojie.dao.UserMapper;
import com.xiaojie.exception.GlobalException;
import com.xiaojie.pojo.User;
import com.xiaojie.result.CodeMsg;
import com.xiaojie.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Unit test for simple MainApplication.
 */
public class MainApplicationTest
{
    @Autowired
    private UserMapper userMapper;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        User user = userMapper.checkPhone("13721071025");
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPwd= user.getPassword();
        String saltDB = "9d5b364d";
        String calcPass = MD5Util.formPassToDBPass("123456", saltDB);
        if(!StringUtils.equals(dbPwd , calcPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        System.out.println("succ");
    }
}
