package br.com.aluramusic.aluramusic;

import br.com.aluramusic.aluramusic.principal.principal;
import br.com.aluramusic.aluramusic.repositorio.ArtistaRepository;
import ch.qos.logback.core.testUtil.RunnableWithCounterAndDone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class AluramusicApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(AluramusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal principal = new principal(repositorio);
		principal.exibeMenu();
	}
}
