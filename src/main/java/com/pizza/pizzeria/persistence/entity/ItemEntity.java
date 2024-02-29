package com.pizza.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(ItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class ItemEntity {
    @Id
    @Column(name = "id_item")
    private Integer idItem;
    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_pizza" , nullable = false)
    private Integer idPizza;
    @Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
    private Double quantity;
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
    private OrderEntity order;
    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)
    private PizzaEntity pizza;

}
