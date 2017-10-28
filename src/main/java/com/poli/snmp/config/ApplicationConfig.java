package com.poli.snmp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages =
    { "com.poli.snmp" })
public class ApplicationConfig extends WebMvcConfigurerAdapter
{

    // private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        // registry.addMapping("/*").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("http://*");
        registry.addMapping("/**");
    }

}
