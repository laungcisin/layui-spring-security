package com.laungcisin.security.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

/**
 * 此配置解决以下bug
 * org.springframework.expression.spel.SpelEvaluationException: EL1057E:(pos 8): No bean resolver registered in the context to resolve access to bean 'xxx'.
 */
@Configuration
public class OAuth2WebSecurityExpressionHandlerConfig {

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }
}
