package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionLinkRequestDto;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

//    @GetMapping
//    public List<Transaction> getAllTransactions() {
//        return transactionService.getAllTransactions();
//    }

    @PostMapping
    public String createTransaction(@RequestBody TransactionLinkRequestDto dto) {
        System.out.println("In payments controller"+dto.toString());
        String res= transactionService.createTransaction(dto);
        System.out.println(res);
        return res;
    }
}
