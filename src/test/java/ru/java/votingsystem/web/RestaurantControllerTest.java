package ru.java.votingsystem.web;

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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                .andExpect(RESTAURANT_MATCHER.contentJson(firstRestaurant));
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("%s/%d/", REST_URL, SECOND_RESTAURANT_ID)))
                .andExpect(status().isNoContent());
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findAll(), firstRestaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void create() throws Exception {

        Restaurant newRestaurant = RequestMapper.createRestaurantFromTo(newCreateRestaurantTo);
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newCreateRestaurantTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        Restaurant created = RESTAURANT_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.getById(newId), newRestaurant);
    }

    @Test
    @WithUserDetails(value = ADMIN_EMAIL)
    void update() throws Exception {
        Restaurant newRestaurant = RequestMapper.createRestaurantFromUpdateTo(newUpdateRestaurantTo);

        mockMvc.perform(MockMvcRequestBuilders.put(String.format("%s/%d/", REST_URL, FIRST_RESTAURANT_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newUpdateRestaurantTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        newRestaurant.setId(FIRST_RESTAURANT_ID);
        newRestaurant.getMenus().forEach(menu -> menu.setId(NEW_MENU_ID));

        RESTAURANT_MATCHER.assertMatch(restaurantRepository.getById(FIRST_RESTAURANT_ID), newRestaurant);
    }
}
