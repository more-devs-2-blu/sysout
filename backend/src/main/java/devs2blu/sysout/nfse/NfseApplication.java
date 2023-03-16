package devs2blu.sysout.nfse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NfseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NfseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
