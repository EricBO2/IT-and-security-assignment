package se.me.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.me.demo.service.TokenService;

/**
 * Denna klass är en endpoint som tar emot information för inloggning.
 */
@RestController
@RequestMapping("/request-token")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    /**
     * Denna konstruktor injectar samtliga parametrar.
     * @param authenticationManager
     * @param tokenService
     */
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    /**
     * Denna metod skapar tokens baserat på inloggningsinformation.
     * @param loginRequest Denna parameter har username och password för inloggning.
     * @return En responseEntity med HTML status och en token.
     */
    @PostMapping
    public ResponseEntity<String> token(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
}
