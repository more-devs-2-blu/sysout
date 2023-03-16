package devs2blu.sysout.nfse.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devs2blu.sysout.nfse.models.NfseModel;
import devs2blu.sysout.nfse.models.UserModel;
import devs2blu.sysout.nfse.repositories.NfseRepository;
import devs2blu.sysout.nfse.repositories.UserRepository;

@Service
public class NfseService {

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

	public List<NfseModel> findNfseByUserId(UUID id) {
		UserModel user = userRepository.findById(id).get();
		return nfseRepository.findByUser(user);
	}

	public NfseModel editNfse(NfseModel nfseModel) {
		return nfseRepository.save(nfseModel);
	}

	public void deleteNfse(UUID id) {
		nfseRepository.deleteById(id);
	}
}
