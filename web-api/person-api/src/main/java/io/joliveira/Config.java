package io.joliveira;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.samplers.ProbabilisticSampler;
import io.opentracing.Tracer;
import io.opentracing.contrib.spring.web.client.TracingRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@org.springframework.context.annotation.Configuration
public class Config {
    @Autowired
    private Environment environment;

    //@Bean
    public Tracer jaegertracer() {
        Configuration.SamplerConfiguration samplerConfig = Configuration
                .SamplerConfiguration
                .fromEnv()
                .withType(environment.getProperty("jaeger.sampler.type", ProbabilisticSampler.TYPE))
                .withParam(environment.getProperty("jaeger.sampler.param", Integer.class, 1));

        Configuration.SenderConfiguration senderConfig = Configuration
                .SenderConfiguration
                .fromEnv()
                .withAgentHost(environment.getProperty("jaeger.agent.host", "localhost"))
                .withAgentPort(Integer.valueOf(environment.getProperty("jaeger.agent.port", "5775" )));

        Configuration.ReporterConfiguration reporterConfig = Configuration
                .ReporterConfiguration
                .fromEnv()
                .withLogSpans(environment.getProperty("jaeger.reporter.log.spans", Boolean.class, true))
                .withSender(senderConfig);

        Configuration config = new Configuration(environment.getProperty("jaeger.service.name", "person-api"))
                .withSampler(samplerConfig)
                .withReporter(reporterConfig);

        return config.getTracer();
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return  restTemplateBuilder
                .interceptors(
                        Collections
                                .singletonList(new TracingRestTemplateInterceptor(jaegertracer())))
                .build();
    }

}
