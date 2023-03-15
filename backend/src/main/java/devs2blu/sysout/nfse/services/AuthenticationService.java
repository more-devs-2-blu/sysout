package devs2blu.sysout.nfse.services;

import devs2blu.sysout.nfse.dtos.AuthenticationRequest;
import devs2blu.sysout.nfse.dtos.AuthenticationResponse;
import devs2blu.sysout.nfse.dtos.UserDto;
import devs2blu.sysout.nfse.enums.UserRole;
import devs2blu.sysout.nfse.exceptions.RepeatedEmailException;
import devs2blu.sysout.nfse.models.UserModel;
import devs2blu.sysout.nfse.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(UserDto userDto) throws RepeatedEmailException {
		var user = UserModel.builder()
				.name(userDto.getName())
				.email(userDto.getEmail())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.userDocIdentification(userDto.getUserDocIdentification())
				.city(userDto.getCity())
				.state(userDto.getState())
				.district(userDto.getDistrict())
				.cep(userDto.getCep())
				.address(userDto.getAddress())
				.complement(userDto.getComplement())
				.number(userDto.getNumber())
				.role(UserRole.USER)
				.build();

		if (isEmailRepeated(userDto.getEmail())) {
			throw new RepeatedEmailException("An account already exists using this email.");
		}

		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		Optional<UserModel> userTest = repository.findByUserDocIdentification(request.getUserDocIdentification());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUserDocIdentification(),
						request.getPassword()));

		var user = repository.findByUserDocIdentification(request.getUserDocIdentification())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public boolean isEmailRepeated(String email) {
		return repository.findByEmail(email).isPresent();
	};

}
