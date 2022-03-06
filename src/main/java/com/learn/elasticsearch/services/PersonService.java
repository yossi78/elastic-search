package com.learn.elasticsearch.services;
import com.learn.elasticsearch.dl.document.Person;
import com.learn.elasticsearch.dl.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public void save(final Person person){
        personRepository.save(person);
    }

    public Person findById(final String id){
        return personRepository.findById(id).orElse(null);
    }


    public List<Person> fetchAllDocuments() {
        List<Person> list=new ArrayList<>();
        personRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public void deleteDocument(String id) {
        personRepository.deleteById(id);
    }
}
