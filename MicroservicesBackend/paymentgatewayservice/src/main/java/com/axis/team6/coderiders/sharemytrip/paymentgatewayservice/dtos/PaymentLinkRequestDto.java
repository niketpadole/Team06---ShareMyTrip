package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos;

import lombok.Data;

@Data
public class PaymentLinkRequestDto {
    private String orderId;
    private String customerName;
    private String phone;
    private int amount;
}
