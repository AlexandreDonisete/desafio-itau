package com.alexandre.desafio_transacao_itau.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API de Transações", version = "1.0", description = "API de Transações"))
public class OpenApiConfig {
}
