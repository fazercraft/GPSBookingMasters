package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tpgps.model.User;


class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User("1","test@email.com","01010101","passtest","usertest");
    }

    @Test
    void getEmail() {
        String test =  user.getEmail();
        assert test.equalsIgnoreCase(user.getEmail());
    }

    @Test
    void getPwdUser() {
        String test =  user.getPwdUser();
        assert test.equalsIgnoreCase(user.getPwdUser());
    }

    @Test
    void setEmail() {
        user.setEmail("emailteste@isec.com");
        assert user.getEmail().equalsIgnoreCase("emailteste@isec.com");
    }

    @Test
    void setPwdUser() {
        user.setPwdUser("novaPass");
        assert user.getPwdUser().equalsIgnoreCase("novaPass");
    }
}