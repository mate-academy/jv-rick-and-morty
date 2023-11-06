package mate.academy.rickandmorty;

import jakarta.annotation.PostConstruct;
import mate.academy.rickandmorty.service.DataDownLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static DataDownLoadService downLoadServiceStatic;
    @Autowired
    private DataDownLoadService downLoadService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        downLoadServiceStatic.getAllCharactersFromApi();
    }

    @PostConstruct
    private void setDownLoadServiceStatic() {
        downLoadServiceStatic = this.downLoadService;
    }
}
