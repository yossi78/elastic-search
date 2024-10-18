package com.example.elastic_search.service;

import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.example.elastic_search.model.User;
import com.example.elastic_search.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private static final String INDEX = "users";

    public UserService() {
    }

    @PostConstruct
    public void createIndexIfNotExists() {
        try {
            boolean exists = elasticsearchClient.indices().exists(e -> e.index(INDEX)).value();
            if (!exists) {
                CreateIndexRequest request = CreateIndexRequest.of(c -> c
                        .index(INDEX)
                        .mappings(m -> m
                                .properties("age", p -> p.integer(i -> i))
                                .properties("firstName", p -> p.text(t -> t))
                                .properties("lastName", p -> p.text(t -> t))
                        )
                );
                CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(request);
                if (!createIndexResponse.acknowledged()) {
                    throw new RuntimeException("Failed to create index: " + INDEX);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error checking/creating index: " + INDEX, e);
        }
    }

    public User saveUser(User user) throws IOException {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() throws IOException {
        return userRepository.findAll();
    }

    public User getUserById(String id) throws IOException {
        return userRepository.findById(id);
    }

    public void deleteUser(String id) throws IOException {
        userRepository.deleteById(id);
    }
}
