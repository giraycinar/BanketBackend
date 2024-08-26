package com.bilgeadam.banket.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bilgeadam.banket.entity.enums.ERole;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
public class JwtTokenManager {

    // Volkan: NOT FINAL

    @Value("secretKey")
    private String secretKey;

    @Value("issuer")
    private String issuer;
    private static final Long TOKEN_EXPIRATION_TIME_IN_MILLISECONDS = 1000L * 60 * 360;  // 6 hours

    public Optional<String> createToken(UUID uuid, List<ERole> roles){
        try {
            String stringUUID = uuid.toString();
            return Optional.of(JWT.create()
                    .withAudience()
                    .withClaim("uuid", stringUUID)
                    .withClaim("role", roles.stream().map(Enum::toString).toList())
                    .withIssuer(issuer)
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME_IN_MILLISECONDS))
                    .sign(Algorithm.HMAC512(secretKey)));
        }
        catch (Exception e){
            return Optional.empty();
        }
    }
    // Volkan:
    // getClaimsFromToken(0) : uuid
    // getClaimsFromToken(1-2-3-4) : list of roles (can have multiple roles)
    public List<String> getAllClaimsFromTokenAsString(String token) {
        DecodedJWT decodedJWT = decodeToken(token);
        Optional<String> optionalUuid = Optional.ofNullable(decodedJWT.getClaim("uuid").asString());
        Optional<List<String>> optionalRoles = Optional.ofNullable(decodedJWT.getClaim("role").asList(String.class));
        if(optionalUuid.isEmpty() || optionalRoles.isEmpty() || optionalRoles.get().isEmpty())
            throw new BanketApplicationException(ErrorType.INVALID_TOKEN);
        List<String> claims = new ArrayList<>();
        claims.add(optionalUuid.get());
        claims.addAll(optionalRoles.get());
        return claims;
    }

    public String getUUIDFromTokenAsString(String token) {
        Optional<String> optionalUuid = Optional.ofNullable(decodeToken(token)
                .getClaim("uuid")
                .asString());
        return optionalUuid.orElseThrow(() -> new BanketApplicationException(ErrorType.INVALID_TOKEN));
    }

    public List<String> getRolesFromTokenAsString(String token) {
        Optional<List<String>> optionalRoles = Optional.ofNullable(decodeToken(token)
                .getClaim("role")
                .asList(String.class));
        return optionalRoles.orElseThrow(() -> new BanketApplicationException(ErrorType.INVALID_TOKEN));
    }

    public Boolean isTokenExpired(String token) {
        Instant tokenExpires = Optional.ofNullable(decodeToken(token)
                .getExpiresAt()
                .toInstant()).orElseThrow(() -> new BanketApplicationException(ErrorType.INVALID_TOKEN));
        return Instant.now().isAfter(tokenExpires);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        if (!getUUIDFromTokenAsString(token).equals(userDetails.getUsername()))
            throw new BanketApplicationException(ErrorType.INVALID_TOKEN);
        if (isTokenExpired(token))
            throw new BanketApplicationException(ErrorType.TOKEN_HAS_EXPIRED);
        return true;
    }

    private DecodedJWT decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException e) {
            throw new BanketApplicationException(ErrorType.INVALID_TOKEN);
        }
    }

}
