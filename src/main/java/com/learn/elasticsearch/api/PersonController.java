package com.learn.elasticsearch.api;
import com.learn.elasticsearch.dl.document.Person;
import com.learn.elasticsearch.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping
    public void save(@RequestBody final Person person){
        personService.save(person);
    }


    @GetMapping("/{id}")
    public Person getAllDocuments(@PathVariable String id){
        return personService.findById(id);
    }

    @GetMapping
    public List<Person> getAllDocuments(){
        return personService.fetchAllDocuments();
    }



    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable String id){
        personService.deleteDocument(id);
    }



}
