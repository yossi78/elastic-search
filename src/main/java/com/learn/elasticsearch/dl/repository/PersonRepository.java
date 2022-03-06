package com.learn.elasticsearch.dl.repository;
import com.learn.elasticsearch.dl.document.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends ElasticsearchRepository<Person,String> {


}


