package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class App {
    public static final String REMOTE_SERVICE_URI = "https://cat-fact.herokuapp.com/facts";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().
                setUserAgent("My Test Service").
                setDefaultRequestConfig(RequestConfig.custom().
                        setConnectTimeout(5000)   // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = httpClient.execute(request);

        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        String subbody = body.substring(7, body.length() - 1);//так и не понял, как сделать нормально - в результате просто подрезал строку

        List<CatsFactParser> cats = mapper.readValue(subbody, new TypeReference<>() {});

        cats.stream().filter(value -> value.getUpvotes() > 0).forEach(System.out::print);
    }
}

