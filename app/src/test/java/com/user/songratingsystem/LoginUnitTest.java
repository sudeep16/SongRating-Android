package com.user.songratingsystem;

import com.user.songratingsystem.bll.LoginBll;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTest {

    LoginBll loginBll = new LoginBll();

    @Test
    public void loginTest() {
        boolean result = loginBll.checkUser("sudeep123", "sudeep");
        assertEquals(true, result);
    }
}