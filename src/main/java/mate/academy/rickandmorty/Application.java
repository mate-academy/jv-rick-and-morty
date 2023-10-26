package mate.academy.rickandmorty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("API Documentation Overview link: http://localhost:8080/api/swagger-ui/index.html#/");
        logger.info("Get random personality link " + "http://localhost:8080/api/personalities/random");
        logger.info("find all link " + "http://localhost:8080/api/personalities");
        logger.info("search link " + "http://localhost:8080/api/personalities/search?name=Alien&name=Baby&status=1&gender=0&gender=1");
    }
}
