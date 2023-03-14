package devs2blu.sysout.nfse.services;

import devs2blu.sysout.nfse.dtos.UserDto;
import devs2blu.sysout.nfse.exceptions.UserNotFoundException;
import devs2blu.sysout.nfse.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
