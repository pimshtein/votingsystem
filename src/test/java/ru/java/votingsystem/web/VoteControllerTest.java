package ru.java.votingsystem.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.java.votingsystem.web.TestData.*;
import static ru.java.votingsystem.web.vote.VoteController.REST_URL;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class VoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("%s/%s/", REST_URL, "by-restaurant")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ALL_VOTES_BY_RESTAURANT_TO_MATCHER.contentJson(allVotes));
    }
}
