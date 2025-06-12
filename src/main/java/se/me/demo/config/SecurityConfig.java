package se.me.demo.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import se.me.demo.service.MyUserDetailsService;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;


/**
 * Denna klass konfigurerar säkerheten
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Denna metod stänger av CSRF, bestämmer vad olika roller har tillgång till på webbsidan
     * och alla förfrågningar måste gå igenom den här metoden.
     *
     * @param http Den är av klassen HTTPSecurity och ger oss tillgång att
     *        modifiera SecurityFilterChain
     * @return En modifierad variant av SecurityFilterChain
     *
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers("/request-token").permitAll());

        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/home"
                    ).permitAll()
                        .requestMatchers("/user").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/register","/admin").hasRole("ADMIN"));


        http.authorizeHttpRequests(authorize ->
                authorize.anyRequest().authenticated());

        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt ->
                        jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));


        return http.build();
    }

    /**
     * Denna metod hämtar en generator som genererar keypairs
     * @return Ett keypair
     *
     */
    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    /**
     * Denna klass skapar en ny passWordEncoder
     * @return En ny passWordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Sätter upp en JWK source för att senare använda den för att skapa en encoder.
     * @param keyPair Är ett nyckelpar som transformeras för att kunna encoda JWT-tokens.
     * @return En JWTSource returneras som används för att skapa en encoder.
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
        RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    /**
     * Denna metod skapar en JWT
     * @param jwkSource Denna parameter används för att skapa en encoder.
     * @return Skapar en ny encoder med jwkSource som parameter.
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return  new NimbusJwtEncoder(jwkSource);
    }

    /**
     * Denna metod använder keypairs för skapa en decoder.
     * @param keyPair Samma nyckelpar som tidigare som används för att matcha vår encoder.
     * @return En decoder skapas
     */
    @Bean
    public JwtDecoder jwtDecoder(KeyPair keyPair) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }

    /**
     * Denna metod skapar en provider och sätter den med en vår userDetailsService och
     * passWordEncoder. Detta används vid inloggning.
     * @param myUserDetailsService Denna sätts in i providern
     * @param passwordEncoder Denna sätts in i providern
     * @return Returnerar en ny ProviderManager som tar in vår provider.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            MyUserDetailsService myUserDetailsService,
            PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    /**
     * Översätter tokens claims till roller i spring security.
     * @return En authenticationConverter som kontrollerar vad för tokens
     * som hör till vilka roller.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("");
        converter.setAuthoritiesClaimName("scope");

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
        return authenticationConverter;
    }
}
