package com.softwareag.cafeteria.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "employee")
public class Employee {

    @Id
    private String id;
    @Field(type= FieldType.Text,name="name")
    private String name;
    @Field(type= FieldType.Text,name="password")
    private String password;
    @Field(type= FieldType.Integer,name="balance")
    private int balance;
    @Field(type=FieldType.Text,name="role")
    private String role;


}
