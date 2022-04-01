package com.example.springbatch.Repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springbatch.model.Facebookprocess;

@Repository
@Transactional
public interface FacebookProccessRepository extends CrudRepository<Facebookprocess, Integer> {
    
}

