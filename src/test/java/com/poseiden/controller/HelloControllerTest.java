package com.poseiden.controller;

import com.poseiden.configuration.security.TokenUtils;
import com.poseiden.configuration.security.UserAuthorization;
import com.poseiden.domain.UserAccount;
import com.poseiden.repo.UserAccountRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.poseiden.domain.role.Role.ADMIN;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@Transactional
public class HelloControllerTest {

    private MockMvc mockMvc;
    private MockMvc unAuthMockMvc;

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private TokenUtils tokenUtils;

    @Test
    public void getHelloTest() throws Exception {
        this.mockMvc =
                MockMvcBuilders.
                        webAppContextSetup(this.wac).
                        build();

        mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Project Initial With Gradle!")));
    }

    @Test
    public void getHelloWithAuth() throws Exception {
        UserAccount userAccount = this.userAccountRepo.save(new UserAccount("user", "password", ADMIN));

        String token = tokenUtils.generateToken(new UserAuthorization(userAccount.getUsername(),
                userAccount.getPassword(), userAccount.getRole()));

        this.unAuthMockMvc = MockMvcBuilders.
                webAppContextSetup(this.wac).
                apply(springSecurity()).
                build();

        this.unAuthMockMvc.perform(
                MockMvcRequestBuilders
                        .get("/security")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greeting with security!")));
    }
}

