package devs2blu.sysout.nfse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import devs2blu.sysout.nfse.services.WebClientService;

@SpringBootApplication
public class NfseApplication implements CommandLineRunner {

	@Autowired
	WebClientService webClientService;

	public static void main(String[] args) {
		SpringApplication.run(NfseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		webClientService.sendXmlRequest();
	}
}
