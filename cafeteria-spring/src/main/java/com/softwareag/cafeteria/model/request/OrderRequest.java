package com.softwareag.cafeteria.model.request;

import com.softwareag.cafeteria.model.OrderItems;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    String employeeId;
    List<OrderRequestItems> orderRequestItems;
    int total;



}
