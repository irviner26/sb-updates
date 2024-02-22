package net.migii26.springboot.controller;

import net.migii26.springboot.model.response.WebResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.migii26.springboot.model.request.AuthenticationRequest;
import net.migii26.springboot.model.request.RegisterRequest;
import net.migii26.springboot.model.response.AuthenticationResponse;
import net.migii26.springboot.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    
    @PostMapping(path = "/register")
    public ResponseEntity<WebResponse<AuthenticationResponse>> register(
        @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, authenticationService.register(request).getToken())
                .body(WebResponse.<AuthenticationResponse>builder().data(AuthenticationResponse.builder().token(authenticationService.register(request).getToken()).build()).build());
        // return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(path = "/authentication")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
