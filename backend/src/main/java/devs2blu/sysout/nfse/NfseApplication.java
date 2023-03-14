package devs2blu.sysout.nfse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class NfseApplication implements CommandLineRunner {
	@Autowired
	WebClient webClient;

	@Value("${sysout.webservice.uri}")
	private String webServiceUri;

	public String sendXmlRequest(String username, String password) {
		Resource file = new ClassPathResource("exemplo_emissao.xml");
		MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
		multipartBodyBuilder.part("file", file, MediaType.APPLICATION_XML);

		String result = webClient
				.post()
				.uri(webServiceUri)
				.headers(headers -> headers.setBasicAuth(username, password))
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
				.retrieve()
				.bodyToMono(String.class)
				.block();

		return result;
	}

	public static void main(String[] args) {
		SpringApplication.run(NfseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(sendXmlRequest("25.825.307/0001-52", "Teste@2023"));
	}
}
