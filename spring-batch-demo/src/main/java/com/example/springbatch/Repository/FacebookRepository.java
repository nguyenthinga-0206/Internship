package com.example.springbatch.Repository;

import javax.transaction.Transactional;

import com.example.springbatch.model.Facebook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FacebookRepository extends CrudRepository<Facebook, Integer> {
    
}
