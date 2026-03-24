package com.charter.rewards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllRewards_returnsOk() throws Exception {
        mockMvc.perform(get("/api/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void getRewardsByCustomer_returnsCorrectData() throws Exception {
        mockMvc.perform(get("/api/rewards/customer-A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("customer-A"))
                .andExpect(jsonPath("$.totalPoints").isNumber())
                .andExpect(jsonPath("$.monthlyPoints").isMap());
    }
}
