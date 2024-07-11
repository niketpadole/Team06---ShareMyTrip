package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.controllers;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionLinkRequestDto;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    void createTransaction() throws Exception {
        TransactionLinkRequestDto dto = new TransactionLinkRequestDto();
        String response = "Transaction created successfully";

        when(transactionService.createTransaction(any(TransactionLinkRequestDto.class))).thenReturn(response);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"exampleField\":\"exampleValue\"}")) // replace with actual fields from dto
                .andExpect(status().isOk())
                .andExpect(content().string(is(response)));

        verify(transactionService).createTransaction(any(TransactionLinkRequestDto.class));
    }
}
