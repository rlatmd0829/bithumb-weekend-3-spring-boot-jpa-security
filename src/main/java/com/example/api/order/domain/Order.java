package com.example.api.order.domain;

import com.example.api.item.domain.Item;
import com.example.api.user.domain.User;
import lombok.Data;

import javax.persistence.*;

@Entity @Data
@Table(name = "orders")
public class Order {
    @Id @Column(name = "order_id")
    private long orderId;
    @Column(name = "price")
    private long price;
    @Column(name = "order_status")
    private String count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
