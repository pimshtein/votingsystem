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
import ru.java.votingsystem.repository.RestaurantRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.java.votingsystem.web.TestData.*;
import static ru.java.votingsystem.web.restaurant.RestaurantController.REST_URL;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @WithUserDetails(ADMIN_EMAIL)
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("%s/%d/", REST_URL, FIRST_RESTAURANT_ID)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentJson(restaurant));
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("%s/%d/", REST_URL, SECOND_RESTAURANT_ID)))
                .andExpect(status().isNoContent());
        MATCHER.assertMatch(restaurantRepository.findAll(), restaurant);
    }
}
