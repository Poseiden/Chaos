package com.poseiden.controller;

import com.poseiden.configuration.security.TokenUtils;
import com.poseiden.configuration.security.UserAuthorization;
import com.poseiden.controller.base.APIBaseTest;
import com.poseiden.domain.UserAccount;
import com.poseiden.repo.UserAccountRepo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.poseiden.domain.role.Role.ADMIN;
import static com.poseiden.domain.role.Role.EMPLOYEE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerTest extends APIBaseTest {
    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private TokenUtils tokenUtils;

    @Test
    public void should_return_hello_msg() throws Exception {
        this.unAuthMockMvc.perform(
                MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Project Initial With Gradle!")));
    }

    @Test
    public void should_return_success_msg_when_right_token() throws Exception {
        UserAccount userAccount = this.userAccountRepo.save(new UserAccount("user", "password", ADMIN));

        String token = tokenUtils.generateToken(new UserAuthorization(userAccount.getUsername(),
                userAccount.getPassword(), userAccount.getRole()));

        this.unAuthMockMvc.perform(
                MockMvcRequestBuilders
                        .get("/security")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greeting with security!")));
    }

    @Test
    @Ignore
    //todo 500 issue
    public void should_return_err_msg_when_account_not_found() throws Exception {
        String token = tokenUtils.generateToken(new UserAuthorization("no this user",
                "password", ADMIN));

        this.unAuthMockMvc.perform(
                MockMvcRequestBuilders
                        .get("/security")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greeting with security!")));
    }

    @Test
    public void should_return_err_msg_when_role_incorrect() throws Exception {
        UserAccount userAccount = this.userAccountRepo.save(new UserAccount("user", "password", EMPLOYEE));

        String token = tokenUtils.generateToken(new UserAuthorization(userAccount.getUsername(),
                userAccount.getPassword(), userAccount.getRole()));

        this.unAuthMockMvc.perform(
                MockMvcRequestBuilders
                        .get("/security")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void generateToken() {

        String token = tokenUtils.generateToken(new UserAuthorization("name",
                "1234", ADMIN));

        System.out.println(token);
        assertNotNull(token);
    }
}

