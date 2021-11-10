package com.udacity.DogRestApi;

import com.udacity.DogRestApi.controller.DogController;
import com.udacity.DogRestApi.service.DogService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(DogController.class)
public class DogControllerUnitTest {

    @MockBean
    DogService dogService;

    @Autowired
    private MockMvc mockMvc;


    @Test
//    @WithMockUser(username = "seren", password = password, roles = "USER")
    public void getAllDogs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dogs")
                        .with(user("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        Mockito.verify(dogService, VerificationModeFactory.times(1)).retrieveAllDos();
    }

    @Test
    public void getBreeds() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/breed")
                    .with(user("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(dogService, VerificationModeFactory.times(1)).retrieveDogBreed();
    }

    @Test
    public void getBreed() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/breed/"+id)
                        .with(user("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(dogService, VerificationModeFactory.times(1)).retrieveDogBreedById(id);
    }

    @Test
    public void getNames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/names")
                        .with(user("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER"))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(dogService, VerificationModeFactory.times(1)).retrieveDogNames();
    }

}
