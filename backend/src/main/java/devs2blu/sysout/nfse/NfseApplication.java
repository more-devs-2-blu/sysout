package devs2blu.sysout.nfse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import devs2blu.sysout.nfse.consumers.ApiConsumer;

@SpringBootApplication
public class NfseApplication implements CommandLineRunner {
	@Autowired
	private ApiConsumer apiConsumer;

	public static void main(String[] args) {
		SpringApplication.run(NfseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String username = "25.825.307/0001-52";
		String password = "Teste@2023";
		String body = "<?xml version='1.0' encoding='ISO-8859-1'?><nfse><nfse_teste>1</nfse_teste></nfse>";

		System.out.println(apiConsumer.request(username, password, body));
	}
}
