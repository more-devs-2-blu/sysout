package devs2blu.sysout.nfse.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devs2blu.sysout.nfse.consumers.ApiConsumer;
import devs2blu.sysout.nfse.dtos.NfseDto;
import devs2blu.sysout.nfse.models.NfseModel;
import devs2blu.sysout.nfse.models.UserModel;
import devs2blu.sysout.nfse.services.NfseService;
import devs2blu.sysout.nfse.services.UserService;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBException;
import lombok.Data;

@Data
@RestController
@RequestMapping("/nfse")
public class NfseController {
	@Autowired
	private ApiConsumer apiConsumer;

	@Autowired
	private NfseService nfseService;

	@Autowired
	UserService userService;

	@GetMapping("/all/{id}")
	public ResponseEntity<List<NfseModel>> getNfsesByUserId(@PathVariable("id") UUID id) {
		List<NfseModel> nfseModels = nfseService.findNfseByUserId(id);
		return new ResponseEntity<>(nfseModels, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addNfse(@Valid @RequestBody NfseDto nfseDto) {
		Optional<UserModel> userModelOptional = userService.findUserById(nfseDto.getUserId());

		if (!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: NFSe has no owner!");
		}

		NfseModel nfseModel = new NfseModel();
		BeanUtils.copyProperties(nfseDto, nfseModel);
		return new ResponseEntity<>(nfseService.saveNfse(nfseModel), HttpStatus.OK);
	}

	@PostMapping("/cancel")
	public ResponseEntity<?> cancelarNfse(@RequestParam int series) {
		try {
			nfseService.cancelNfse(series);
			return ResponseEntity.ok("NFS-e cancelada com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Não foi possível cancelar a NFS-e. Erro: " + e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateNfse(@PathVariable("id") UUID id, @Valid @RequestBody NfseDto nfseDto) {
		Optional<NfseModel> nfseModelOptional = nfseService.findNfseById(id);

		if (!nfseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: NFSe doesn't exist!");
		}

		NfseModel nfseModel = new NfseModel();
		BeanUtils.copyProperties(nfseDto, nfseModel);
		nfseModel.setId(nfseModelOptional.get().getId());

		return new ResponseEntity<>(nfseService.saveNfse(nfseModel), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteNfse(@PathVariable("id") UUID id) {
		Optional<NfseModel> nfseModelOptional = nfseService.findNfseById(id);

		if (!nfseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: NFSE doesn't exist!");
		}

		nfseService.deleteNfse(id);
		return ResponseEntity.status(HttpStatus.OK).body("NFSE deleted successfully!");
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> emmitNfse(@PathVariable("id") UUID id) throws JAXBException {
		Optional<NfseModel> nfseModelOptional = nfseService.findNfseById(id);

		if (!nfseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: NFSE doesn't exist!");
		}

		NfseDto nfseDto = new NfseDto();
		BeanUtils.copyProperties(nfseModelOptional.get(), nfseDto);

		String username = "25.825.307/0001-52";
		String password = "Teste@2023";

		String body = nfseService.nfseToXml(nfseDto);
		System.out.println(apiConsumer.request(username, password, body));

		return ResponseEntity.status(HttpStatus.OK).body("Emission request sent successfully!");
	}
}
