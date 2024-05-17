package com.springboot.email.restController;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.email.domain.HttpResponse;
import com.springboot.email.domain.User;
import com.springboot.email.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<HttpResponse> createUser(@RequestBody User newuser) {
		User createdUser = userService.save(newuser);
		return ResponseEntity.created(URI.create(""))
				.body(HttpResponse.builder().timeStamp(LocalDateTime.now().toString()).data(Map.of("User", createdUser))
						.message("User Created successfully").status(HttpStatus.CREATED)
						.statusCode(HttpStatus.CREATED.value()).build());
	}
	
	@GetMapping
	public ResponseEntity<HttpResponse> comfirmaedAccount(@RequestParam("token") String userToken) {
		Boolean isverify = userService.verifyConfirmKey(userToken);
		return ResponseEntity.ok()
				.body(HttpResponse.builder().timeStamp(LocalDateTime.now().toString()).data(Map.of("success", isverify))
						.message("User Created successfully").status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value()).build());
	}

}
