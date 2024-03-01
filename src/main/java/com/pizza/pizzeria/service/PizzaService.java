package com.pizza.pizzeria.service;

import com.pizza.pizzeria.persistence.entity.PizzaEntity;
import com.pizza.pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
   private final PizzaRepository pizzaRepository;
   @Autowired
   public PizzaService(PizzaRepository pizzaRepository){

       this.pizzaRepository=pizzaRepository;
   }

   public List<PizzaEntity> getAll(){
       return this.pizzaRepository.findAll();
   }

   public PizzaEntity getOne(int idPizza){
       return this.pizzaRepository.findById(idPizza).orElse(null);
   }

    public PizzaEntity save(PizzaEntity pizzaEntity){
        return this.pizzaRepository.save(pizzaEntity);
    }

    public void delete(int idPizza){
       this.pizzaRepository.deleteById(idPizza);
    }
    public boolean exists(int idPizza){
       return this.pizzaRepository.existsById(idPizza);
    }
}
