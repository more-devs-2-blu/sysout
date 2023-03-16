package devs2blu.sysout.nfse.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devs2blu.sysout.nfse.dtos.UserDto;
import devs2blu.sysout.nfse.exceptions.UserNotFoundException;
import devs2blu.sysout.nfse.models.UserModel;
import devs2blu.sysout.nfse.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final JwtService jwtService;

	public UserDto getUserInfo(String authorizationHeader) {
		var userDocIdentification = jwtService.extractUsername(authorizationHeader.substring(7));
		var user = userRepository.findByUserDocIdentification(userDocIdentification);
		if (user.isPresent()) {
			ModelMapper modelMapper = new ModelMapper();
			return modelMapper.map(user.get(), UserDto.class);
		} else {
			throw new UserNotFoundException("User not found.");
		}
	}

	public Optional<UserModel> findUserById(UUID id) {
		return userRepository.findById(id);
	}

	public UserModel saveUser(UserModel userModel) {
		return userRepository.save(userModel);
	}

	public List<UserModel> findAllUsers() {
		return userRepository.findAll();
	}

	public UserModel editUser(UserModel userModel) {
		return userRepository.save(userModel);
	}

	public void deleteUser(UUID id) {
		userRepository.deleteById(id);
	}
}
