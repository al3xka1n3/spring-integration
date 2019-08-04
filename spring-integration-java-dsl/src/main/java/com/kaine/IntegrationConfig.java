package com.kaine;

import com.kaine.msg.ServerMsg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    public IntegrationFlow flow() {
/*            return IntegrationFlows
                .from("requestChannel")
                .handle(Http
                        .outboundGateway("http://localhost:8080/getServerTime")
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(ServerMsg.class))
                .channel(MessageChannels.queue("responseChannel"))
                .get();
*/
        return f -> f
                .channel("requestChannel")
                .handle(Http
                        .outboundGateway("http://localhost:8080/getServerTime")
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(ServerMsg.class))
                .channel(c -> c.queue("responseChannel"));
    }
}
