package com.softwareag.cafeteria.model.request;

import com.softwareag.cafeteria.model.Product;
import lombok.Data;

@Data
public class OrderRequestItems {
    private Product product;
    private int count;
}
//65a9fda936a0917447d58ef1 //employee
//
//65a9fb6480baa326407f8e94 //prod
//65a9fcb29cb0d343353050b7 //prod