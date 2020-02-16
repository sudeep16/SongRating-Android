package com.user.songratingsystem;

import com.user.songratingsystem.bll.RegisterBll;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterUnitTest {
    RegisterBll registerBll = new RegisterBll();

    @Test
    public void loginTest() {
        boolean result = registerBll.registerUser("sudeep213", "sudeep123", "sudeep@gmail.com", "9805454545", "Kathmandu", "Male", "imageFile-1581582017540.jpeg");
        assertEquals(true, result);
    }
}
