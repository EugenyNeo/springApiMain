package com.company.springApiMain.repos;

import com.company.springApiMain.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends CrudRepository<Person, Long> {
    List<Person> findByPersonId(String personId);

}
