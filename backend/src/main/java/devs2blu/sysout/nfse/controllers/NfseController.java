package devs2blu.sysout.nfse.controllers;

import devs2blu.sysout.nfse.configs.WebClientConfig;
import devs2blu.sysout.nfse.dtos.NfseDto;
import devs2blu.sysout.nfse.models.NfseModel;
import devs2blu.sysout.nfse.services.NfseService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RestController;

import devs2blu.sysout.nfse.dtos.NfseDto;
import devs2blu.sysout.nfse.enums.InvoiceStatus;
import devs2blu.sysout.nfse.models.NfseModel;
import devs2blu.sysout.nfse.models.UserModel;
import devs2blu.sysout.nfse.services.NfseService;
import devs2blu.sysout.nfse.services.UserService;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@RestController
@RequestMapping("/nfse")
public class NfseController {

	@Autowired
	private WebClientConfig webClientConfig;

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
		Optional<UserModel> userModelOptional = userService.findUserById(UUID.fromString(nfseDto.getUserId()));

		if (!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: NFSe has no owner!");
		}

		var nfseModel = new NfseModel();
		BeanUtils.copyProperties(nfseDto, nfseModel);
		nfseModel.setUser(userModelOptional.get());
		nfseModel.setDateOfIssue(LocalDateTime.now());
		nfseModel.setTaxableEventDate(LocalDateTime.now());

		return new ResponseEntity<>(nfseService.saveNfse(nfseModel), HttpStatus.OK);
	}

	@PostMapping("/cancel")
	public ResponseEntity<?> cancelarNFS(@RequestParam int series) {
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

		var nfseModel = new NfseModel();
		BeanUtils.copyProperties(nfseDto, nfseModel);
		nfseModel.setId(nfseModelOptional.get().getId());

		return new ResponseEntity<>(nfseService.saveNfse(nfseModel), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteNfse(@PathVariable("id") UUID id) {
		Optional<NfseModel> nfseModelOptional = nfseService.findNfseById(id);

		if (!nfseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: NFSE not exists!");
		}
		nfseService.deleteNfse(id);
		return ResponseEntity.status(HttpStatus.OK).body("NFSE deleted successfully!");
	}
}
