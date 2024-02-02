package com.softwareag.cafeteria.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.elasticsearch.annotations.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "orders")
public class Order {

    @Id
    private String id;

    @Field(type = FieldType.Text,name="employee_id")
    private String employee;

    @Field(type = FieldType.Nested)
    private List<OrderItems> items;

    @Field(type = FieldType.Integer, name = "total")
    private int total;

    @Field(type = FieldType.Keyword, name = "status")
    private OrderStatus status;

    @Field(type = FieldType.Date, name = "date")
    private LocalDate date;

    // getters and setters
}