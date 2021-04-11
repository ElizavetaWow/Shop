package com.krylova.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer article;
    @ManyToOne
    private Country country;
    @ManyToOne
    private Manufacturer manufacturer;
    @OneToMany(targetEntity=Product.class )
    private List products;
}
