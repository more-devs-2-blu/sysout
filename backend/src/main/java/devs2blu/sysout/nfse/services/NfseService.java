package devs2blu.sysout.nfse.services;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.xml.transform.Result;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import devs2blu.sysout.nfse.consumers.ApiConsumer;
import devs2blu.sysout.nfse.dtos.NfseDto;
import devs2blu.sysout.nfse.models.NfseModel;
import devs2blu.sysout.nfse.models.UserModel;
import devs2blu.sysout.nfse.repositories.NfseRepository;
import devs2blu.sysout.nfse.repositories.UserRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@Service
public class NfseService {
	@Value("${sysout.webservice.url}")
	private String apiUrl;

	@Autowired
	private NfseRepository nfseRepository;

	@Autowired
	private UserRepository userRepository;

	public NfseModel saveNfse(NfseModel nfseModel) {
		return nfseRepository.save(nfseModel);
	}

	public List<NfseModel> findAllNfse() {
		return nfseRepository.findAll();
	}

	public Optional<NfseModel> findNfseById(UUID id) {
		return nfseRepository.findById(id);
	}

	public List<NfseModel> findNfseByUserId(UUID userId) {
		return nfseRepository.findByUserId(userId);
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

	public String nfseToXml(NfseDto nfseDto) throws JAXBException {

		String xmlContent = "";

		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(NfseDto.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Print XML String to Console
			StringWriter sw = new StringWriter();

			// Write XML to StringWriter
			jaxbMarshaller.marshal(nfseDto, sw);

			// Verify XML Content
			xmlContent = sw.toString();
			System.out.println(xmlContent);

		} catch (JAXBException e) {
			e.printStackTrace();
			xmlContent = "Error!";
		}

		return xmlContent;
	}
}
