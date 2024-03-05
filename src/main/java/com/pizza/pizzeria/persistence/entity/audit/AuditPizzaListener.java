package com.pizza.pizzeria.persistence.entity.audit;

import com.pizza.pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;
    @PostLoad
    public void postLoad(PizzaEntity pizzaEntity){
        System.out.println("POST LOAD....");
        this.currentValue= SerializationUtils.clone(pizzaEntity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue.toString());
        System.out.println("NEW VALUE: "+ pizzaEntity.toString());
    }
    @PreRemove
    public void onPrePersist(PizzaEntity pizzaEntity){
        System.out.println(pizzaEntity.toString());
    }
}
