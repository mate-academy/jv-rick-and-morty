package mate.academy.rickandmorty.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "mate.academy.rickandmorty.service.rick.and.morty.client")
public class FeignConfiguration {
}
