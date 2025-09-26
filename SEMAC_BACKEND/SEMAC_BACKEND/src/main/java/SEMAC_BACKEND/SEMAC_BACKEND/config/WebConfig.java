package SEMAC_BACKEND.SEMAC_BACKEND.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a política de CORS a todos os endpoints ("/**")
                .allowedOrigins("http://localhost:5173") // Permite requisições desta origem
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Libera os métodos HTTP
                .allowedHeaders("*") // Libera todos os cabeçalhos
                .allowCredentials(true);
    }
}
