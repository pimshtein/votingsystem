package ru.java.votingsystem.web;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.repository.RestaurantRepository;
import ru.java.votingsystem.util.JsonUtil;
import ru.java.votingsystem.web.restaurant.request.RequestMapper;
import ru.java.votingsystem.web.restaurant.request.create.CreateMenuTo;
import ru.java.votingsystem.web.restaurant.request.create.CreateRestaurantTo;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.java.votingsystem.web.TestData.*;
import static ru.java.votingsystem.web.restaurant.RestaurantController.*;

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
                .andExpect(MATCHER.contentJson(firstRestaurant));
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("%s/%d/", REST_URL, SECOND_RESTAURANT_ID)))
                .andExpect(status().isNoContent());
        MATCHER.assertMatch(restaurantRepository.findAll(), firstRestaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void create() throws Exception {
        CreateMenuTo newMenuTo = new CreateMenuTo("Test", 10);
        CreateRestaurantTo newRestaurantTo = new CreateRestaurantTo("Test", List.of(newMenuTo));
        Restaurant newRestaurant = RequestMapper.createRestaurantFromTo(newRestaurantTo);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurantTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        Restaurant created = MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        MATCHER.assertMatch(created, newRestaurant);
        MATCHER.assertMatch(restaurantRepository.getById(newId), newRestaurant);
    }
}
