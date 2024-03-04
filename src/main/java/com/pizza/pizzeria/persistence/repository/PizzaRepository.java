package com.pizza.pizzeria.persistence.repository;

import com.pizza.pizzeria.persistence.entity.PizzaEntity;
import com.pizza.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    PizzaEntity findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTopByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByVeganTrue();
    @Query(value = "UPDATE pizza " +
                    "SET price = :newPrice " +
                    "WHERE id_pizza = :idPizza", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("idPizza") int idPizza, @Param("newPrice") double newPrice);
    //Spring expression language
    @Query(value = "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.idPizza}", nativeQuery = true)
    @Modifying
    void updatePrice1(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);

}
