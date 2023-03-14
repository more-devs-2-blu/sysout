package devs2blu.sysout.nfse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
public class NfseApplication implements CommandLineRunner {
	@Autowired
	WebClient webClient;

	@Value("${sysout.webservice.uri}")
	private String webServiceUri;

	public String sendXmlRequest(String username, String password) {
		Resource file = new ClassPathResource("exemplo_emissao.xml");

		String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<nfse>\n<nfse_teste>1</nfse_teste>\n</nfse>\n\u001a";
		Resource body = new ByteArrayResource(xml.getBytes());

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
		String result = sendXmlRequest("25.825.307/0001-52", "Teste@2023");
		System.out.println(result);
	}
}
