package com.example.elastic_search.repository;

import com.example.elastic_search.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;




public interface UserRepository extends ElasticsearchRepository<User, String> {
}
