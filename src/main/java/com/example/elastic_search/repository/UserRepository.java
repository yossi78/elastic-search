package com.example.elastic_search.repository;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.elastic_search.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private static final String INDEX = "users";

    public User save(User user) throws IOException {
        IndexResponse response = elasticsearchClient.index(i -> i
                .index(INDEX)
                .id(user.getId())
                .document(user)
        );
        user.setId(response.id());
        return user;
    }

    public List<User> findAll() throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(INDEX)
                .size(1000) // Adjust size as needed
                .query(q -> q.matchAll(m -> m))
        );

        SearchResponse<User> searchResponse = elasticsearchClient.search(searchRequest, User.class);

        List<User> users = new ArrayList<>();
        for (Hit<User> hit : searchResponse.hits().hits()) {
            users.add(hit.source());
        }
        return users;
    }

    public User findById(String id) throws IOException {
        GetResponse<User> getResponse = elasticsearchClient.get(g -> g
                .index(INDEX)
                .id(id), User.class);

        return getResponse.found() ? getResponse.source() : null;
    }

    public void deleteById(String id) throws IOException {
        DeleteResponse deleteResponse = elasticsearchClient.delete(d -> d
                .index(INDEX)
                .id(id)
        );
    }
}
