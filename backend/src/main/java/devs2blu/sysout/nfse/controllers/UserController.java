package devs2blu.sysout.nfse.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devs2blu.sysout.nfse.dtos.UserDto;
import devs2blu.sysout.nfse.enums.UserRole;
import devs2blu.sysout.nfse.models.UserModel;
import devs2blu.sysout.nfse.services.AuthenticationService;
import devs2blu.sysout.nfse.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<UserDto> getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
		UserDto user = userService.getUserInfo(authorizationHeader);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getAllUsers() {
		List<UserModel> userModels = userService.findAllUsers();
		return new ResponseEntity<>(userModels, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addNfse(@Valid @RequestBody UserDto userDto) {
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		return new ResponseEntity<>(userService.saveUser(userModel), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateNfse(@PathVariable("id") UUID id, @Valid @RequestBody UserDto userDto) {
		Optional<UserModel> userModelOptional = userService.findUserById(id);

		if (!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: User doesn't exist!");
		}

		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		userModel.setId(userModelOptional.get().getId());

		return new ResponseEntity<>(userService.saveUser(userModel), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteNfse(@PathVariable("id") UUID id) {
		Optional<UserModel> userModelOptional = userService.findUserById(id);

		if (!userModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: User doesn't exist!");
		}

		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body("NFSE deleted successfully!");
	}
}
