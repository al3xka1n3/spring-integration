package com.kaine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:http-inbound.xml", "classpath:http-outbound.xml"})
public class SpringIntegrationMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationMainApplication.class, args);
    }

/*    @Bean
    public IntegrationFlow outbound() {
        return IntegrationFlows
                .from("httpOutRequest")
                .handle(Http
                        .outboundGateway("http://dummy.restapiexample.com/api/v1/employees")
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(String.class))
                .get();
    }*/
}
