package com.pizza.pizzeria.service;

import com.pizza.pizzeria.persistence.entity.PizzaEntity;
import com.pizza.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.pizza.pizzeria.persistence.repository.PizzaRepository;
import com.pizza.pizzeria.service.dto.UpdatePizzaPriceDto;
import com.pizza.pizzeria.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
   private final PizzaRepository pizzaRepository;
   private final PizzaPagSortRepository pizzaPagSortRepository;
   @Autowired
   public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository){

       this.pizzaRepository=pizzaRepository;
       this.pizzaPagSortRepository = pizzaPagSortRepository;
   }

   public List<PizzaEntity> getAll(){
       return this.pizzaRepository.findAll();
   }
    public Page<PizzaEntity> getAllPagSort(int page, int elements){
        Pageable pageRequest= PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }
   public List<PizzaEntity> getAvailable(){
       System.out.println(this.pizzaRepository.countByVeganTrue());
       return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
   }

    public Page<PizzaEntity> getAvailableSort(int page, int elements, String sortBy, String sortDirection){
       Sort sort =  Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
       Pageable pageRequest= PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }
    public PizzaEntity getByName(String name){
       return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }
    public PizzaEntity getFirstByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name);
    }
    public List<PizzaEntity> getWith(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(name);
    }
    public List<PizzaEntity> getWithout(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(name);
    }
    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTopByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
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
    @Transactional(noRollbackFor = EmailApiException.class)
    public void updatePrice(UpdatePizzaPriceDto dto){
       this.pizzaRepository.updatePrice1(dto);
       this.sendEmail();
    }
    public void sendEmail(){
       throw new EmailApiException();
    }
    public boolean exists(int idPizza){
       return this.pizzaRepository.existsById(idPizza);
    }

}
