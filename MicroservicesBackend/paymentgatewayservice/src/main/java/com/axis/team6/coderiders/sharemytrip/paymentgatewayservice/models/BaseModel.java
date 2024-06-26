package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class BaseModel {
    @Id
    private String id;
}
