package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.controllers;

import java.util.List;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //Find transactions by passengerId
     @GetMapping("/{passengerId}")
    public ResponseEntity<List<TransactionDetailsDTO>> getTransactions(@PathVariable Integer passengerId){
        List<TransactionDetailsDTO> response= transactionService.getTransactions(passengerId);
        return ResponseEntity.ok(response);
     }
     //Find all transactions by PublisherRideID
     @GetMapping("/{publisherRideId}/transactions")
     public ResponseEntity<List<TransactionDetailsDTO>> getPassengerTransactions(@PathVariable Integer publisherRideId){
         List<TransactionDetailsDTO> response= transactionService.getPassengerTransactions(publisherRideId);
         return ResponseEntity.ok(response);
     }

}
