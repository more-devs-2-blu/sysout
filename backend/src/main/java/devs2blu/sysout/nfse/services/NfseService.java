package devs2blu.sysout.nfse.services;

import devs2blu.sysout.nfse.models.NfseModel;
import devs2blu.sysout.nfse.repositories.NfseRepository;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NfseService {
	@Value("${sysout.webservice.url}")
	private String apiUrl;

	@Autowired
	private NfseRepository nfseRepository;

	public NfseModel saveNfse(NfseModel nfseModel) {
		return nfseRepository.save(nfseModel);
	}

	public List<NfseModel> findAllNfse() {
		return nfseRepository.findAll();
	}

	public Optional<NfseModel> findNfseById(UUID id) {
		return nfseRepository.findById(id);
	}

	public NfseModel editNfse(NfseModel nfseModel) {
		return nfseRepository.save(nfseModel);
	}

	public void deleteNfse(UUID id) {
		nfseRepository.deleteById(id);
	}


	public void cancelNfse(int series) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(apiUrl);
		httpPost.setHeader("Content-Type", "application/json");
		JSONObject json = new JSONObject();
		json.put("number", series);
		httpPost.setEntity(new StringEntity(json.toString(), StandardCharsets.UTF_8));
		try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("Error ao cancelar nota fiscal. Status code: " + statusCode);
			}
		}
	}
}
