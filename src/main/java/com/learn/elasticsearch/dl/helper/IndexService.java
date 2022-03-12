package com.learn.elasticsearch.dl.helper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;




@Service
@Slf4j
public class IndexService {

    private final List<String> INDICES_TO_CREATE = List.of(Indices.VEHICLE_INDEX);
    private final RestHighLevelClient client;

    @Autowired
    public IndexService(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void tryToCreateIndices() {

        final String settings  = Util.loadAsString("static/es-settings.json");

        for(final String indexName : INDICES_TO_CREATE){
            try{
                boolean  indexExists = client
                        .indices()
                        .exists(new GetIndexRequest(indexName),RequestOptions.DEFAULT);

                if(indexExists){
                    continue;
                }
                final String mapping = Util.loadAsString("static/mappings/" + indexName + ".json");
                if(settings==null  || mapping==null){
                    log.error("Filed to create index with name '{}'",indexName);
                    continue;
                }
                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                createIndexRequest.settings(settings, XContentType.JSON);
                createIndexRequest.mapping(settings,XContentType.JSON);
                client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
            }catch(Exception e){
                log.error(e.getMessage(),e);
            }
        }


    }
}



















}