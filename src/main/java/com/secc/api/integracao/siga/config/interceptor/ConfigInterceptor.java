package com.secc.api.integracao.siga.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import lombok.RequiredArgsConstructor;

/**
 * Essa classe tem a responsabilidade de configurar as
 * requisições que serão interceptadas.
 */

@RequiredArgsConstructor
@Component
public class ConfigInterceptor extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptador())
        	.addPathPatterns(
        			"/movimentacao/incluir-cossignatario",
        			"/movimentacao/excluir-movimentacao",
        			"/documento/assinar**",
        			"/documento", 
        			"/documento/prepara-documento-certificado",
        			"/autenticar",
        			"/autenticar/siga/jwt",
        			"/pessoa/**",
        			"/pessoa/buscar/",
        			"/pessoa/buscar**",
        			"/assinador/**",
        			"/swagger-ui/**");
    }
 
}
