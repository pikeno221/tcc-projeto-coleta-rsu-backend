package io.projetocoletarsu.controller;

import io.projetocoletarsu.model.TokenResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@Api(value = "token", description = "")
public class TokenController {

    @GetMapping
    public ResponseEntity<TokenResponse> getToken() {
        return ResponseEntity.ok(new TokenResponse());
    }
}
