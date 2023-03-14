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
import org.springframework.web.bind.annotation.RestController;

import devs2blu.sysout.nfse.dtos.NfseDto;
import devs2blu.sysout.nfse.models.NfseModel;
import devs2blu.sysout.nfse.services.NfseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/nfse")
public class NfseController {

	private NfseService nfseService;

	@Autowired
	public NfseController(NfseDto nfseDto, NfseService nfseService) {
		this.nfseService = nfseService;
	}

	@GetMapping
	public ResponseEntity<List<NfseModel>> getAllNfse() {
		List<NfseModel> nfseModels = nfseService.findAllNfse();
		return new ResponseEntity<>(nfseModels, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<NfseModel> addNfse(@Valid @RequestBody NfseDto nfseDto) {
		var nfseModel = new NfseModel();
		BeanUtils.copyProperties(nfseDto, nfseModel);

		return new ResponseEntity<>(nfseService.saveNfse(nfseModel), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<Object> updateNfse(@PathVariable("id") UUID id, @Valid @RequestBody NfseDto nfseDto) {
		Optional<NfseModel> nfseModelOptional = nfseService.findNfseById(id);

		if (!nfseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: NFSE not exists!");
		}

		var nfseModel = new NfseModel();
		BeanUtils.copyProperties(nfseDto, nfseModel);
		nfseModel.setId(nfseModelOptional.get().getId());

		return new ResponseEntity<>(nfseService.saveNfse(nfseModel), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
    public ResponseEntity<Object> deleteNfse(@PathVariable("id") UUID id){
        Optional<NfseModel> nfseModelOptional = nfseService.findNfseById(id);

        if(!nfseModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: NFSE not exists!")
        }
        nfseService.deleteNfse(id);
        return ResponseEntity.status(HttpStatus.OK).body("NFSE deleted successfully!");
    }
}
