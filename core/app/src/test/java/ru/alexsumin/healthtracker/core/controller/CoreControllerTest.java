package ru.alexsumin.healthtracker.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.alexsumin.healthtracker.core.api.DifferenceDTO;
import ru.alexsumin.healthtracker.core.api.MeasurementDTO;
import ru.alexsumin.healthtracker.core.api.UserDTO;
import ru.alexsumin.healthtracker.core.service.MainService;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class CoreControllerTest {

    private MockMvc mockMvc;
    private CoreController coreController;
    private ObjectMapper objectMapper;

    @Mock
    private MainService mainService;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        coreController = new CoreController(mainService);
        mockMvc = MockMvcBuilders.standaloneSetup(coreController).build();
    }

    @Test
    public void addNewMeasurementTest() throws Exception {
        String expected = "42";
        when(mainService.addNewMeasurement(any(), anyLong()))
                .thenReturn(DifferenceDTO.builder().value(new BigDecimal(expected)).build());

        var measurementDTO = MeasurementDTO.builder()
                .value(new BigDecimal("117"))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/user/1/measurement")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(measurementDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(expected)));

        verify(mainService, only()).addNewMeasurement(any(), anyLong());
    }

    @Test
    public void createUserTest() throws Exception {
        doNothing().when(mainService).createUser(any());

        var userDTO = UserDTO.builder()
                .id(42L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mainService, only()).createUser(any());
    }
}