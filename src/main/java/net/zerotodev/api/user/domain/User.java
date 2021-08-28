package net.zerotodev.api.user.domain;

import lombok.Data;
import net.zerotodev.api.order.domain.Order;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity @Data
@Table(name = "users")
public class User {
    @Id @Column(name = "user_id")
    private long userId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min=8, message = "8자리 이상 입력하시오")
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "reg_date")
    private String regDate;
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Role> roles;
}
