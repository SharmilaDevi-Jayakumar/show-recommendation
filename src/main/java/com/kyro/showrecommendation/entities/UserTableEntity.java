package com.kyro.showrecommendation.entities;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Getter
@Setter
@Entity
@Table(name = "user_table")
public class UserTableEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @Column(name ="user_id")
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @Column
    private String password;


    public UserTableEntity() {}

    public UserTableEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
