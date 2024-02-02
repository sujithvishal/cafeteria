package com.softwareag.cafeteria.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class Product {

    @Id
    private String id;
    @Field(type= FieldType.Text,name="name")
    private String name;
    @Field(type= FieldType.Integer,name="price")
    private int price;
    @Field(type= FieldType.Text,name="imageurl")
    private String imageUrl;
}
