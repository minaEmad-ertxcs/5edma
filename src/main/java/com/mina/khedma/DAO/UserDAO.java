package com.mina.khedma.DAO;

import com.mina.khedma.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @ToString.Exclude
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}