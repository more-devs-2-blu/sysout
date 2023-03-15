package devs2blu.sysout.nfse.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ApiConsumer {
	@Autowired
	private WebClient webClient;

	@Value("${sysout.webservice.uri}")
	private String webServiceUri;

	public String request(String username, String password, String body) {
		// Load content from file snippet
		// Resource body = new ClassPathResource("exemplo_emissao_2.xml");
		// MultiValueMap<String, Resource> bodyValues = new LinkedMultiValueMap<>();
		// bodyValues.add("body", body);

		MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
		multipartBodyBuilder
				.part("file", body, MediaType.APPLICATION_XML)
				.filename("request.xml");

		return webClient
				.post()
				.uri(webServiceUri)
				.headers(headers -> headers.setBasicAuth(username, password))
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
				.retrieve()
				.bodyToMono(String.class)
				.block();
		;
	}
}
