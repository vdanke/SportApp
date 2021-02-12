package org.itstep.sport.service.service;

import io.jsonwebtoken.Claims;
import org.itstep.sport.service.model.User;

public interface JwtService {

    String createToken(User user);

    Claims getClaimsFromToken(String token);

    boolean validateToken(String token);
}
