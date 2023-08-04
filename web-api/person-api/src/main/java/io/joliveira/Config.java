package io.joliveira;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@org.springframework.context.annotation.Configuration
@EnableJpaRepositories
public class Config {
    @Autowired
    private Environment environment;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return  restTemplateBuilder
               /* .interceptors(
                        Collections
                                .singletonList(new TracingRestTemplateInterceptor(jaegertracer())))*/
                .errorHandler(new DefaultResponseErrorHandler() {
                    public void handleError(ClientHttpResponse response) throws IOException {
                        HttpStatus statusCode = response.getStatusCode();

                        if (!(HttpStatus.NOT_FOUND.value() == statusCode.value()))
                            throw new HttpClientErrorException(statusCode, response.getStatusText());

                    }

                })
                .build();
    }

}
