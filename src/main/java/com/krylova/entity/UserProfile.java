package com.krylova.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class UserProfile {
    @Id
    @Column(name = "userT_id")
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private String city;
    private String street;
    private Integer postalCode;
    private Integer building;
    private Integer flat;
    @OneToOne(targetEntity=UserT.class )
    @MapsId
    private UserT userT;
}
