package tn.esprit.asi.configs;

import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.RequiredArgsConstructor;
import tn.esprit.asi.dtos.UserDto;
import tn.esprit.asi.services.UserService;

import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {
	@Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final UserService userService;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto user = userService.findByLogin(decoded.getIssuer());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
