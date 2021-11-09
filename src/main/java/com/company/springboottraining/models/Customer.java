package com.company.springboottraining.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;


@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @PrimaryKey
    @Column
    private int customerID;

    @Column
    private String name;

    @Column
    private List<Integer> arrayCustomer = new ArrayList<>();
}

