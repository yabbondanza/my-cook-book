package com.example.mycookbookapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.mycookbookapi.model.Receita;

@Repository
public interface ReceitaRepository extends CrudRepository<Receita, Integer> {
    List <Receita> findAll();
    
    Receita findById (int id);
}

