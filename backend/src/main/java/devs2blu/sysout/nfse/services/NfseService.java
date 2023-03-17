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
	private ApiConsumer apiConsumer;
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

	public void cancelNfse(UUID id) {
		String username = "25.825.307/0001-52";
		String password = "Teste@2023";
		String body = "<?xml version='1.0' encoding='iso-8859-1'?>" +
				"<nfse>" +
				"<nfse_teste>1</nfse_teste>" +
				"<nf>" +
				"<numero></numero>" +
				"<situacao></situacao>" +
				"<observacao></observacao>" +
				"</nf>" +
				"<prestador>" +
				"<cpfcnpj></cpfcnpj>" +
				"<cidade></cidade>" +
				"</prestador>" +
				"</nfse>";
		apiConsumer.request(username,password,body);

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
