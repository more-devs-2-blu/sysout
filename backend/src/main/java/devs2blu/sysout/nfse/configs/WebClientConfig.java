package devs2blu.sysout.nfse.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
	@Value("${sysout.webservice.url}")
	private String webServiceUrl;

	@Bean
	public WebClient webClient() {
		return WebClient
				.builder()
				.baseUrl(webServiceUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
				.build();
	}
}
