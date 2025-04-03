package com.FrameWork.MedLite.Authentification.service;

import com.FrameWork.MedLite.Authentification.Utils.AppConfig;
import com.FrameWork.MedLite.Authentification.domaine.RefreshToken;
import com.FrameWork.MedLite.Authentification.repository.RefreshTokenRepository;
import com.FrameWork.MedLite.Authentification.repository.UserRepository;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenService {

    @Value("${security.jwt.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

//    @Autowired
//    private Long expirationTime;// Assuming you have this configured as well
    @Value("${security.jwt.expiration-time}")
    private Long expirationTime;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private String appTimeZoneId; 

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());

        try {  // Add try-catch for potential ZoneId exceptions
//            ZoneOffset zoneId = ZoneId.of(appTimeZoneId);
//            ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId).plusMinutes(expirationTime);
//            Instant expiryInstant = zonedDateTime.toInstant();
//            refreshToken.setExpiryDate(expiryInstant);
            refreshToken.setExpiryDate(Instant.now().plusMillis(expirationTime));
        } catch (DateTimeException e) {
            // Handle the exception appropriately, e.g., log the error and use a default time zone
            System.err.println("Error creating RefreshToken: Invalid time zone. Using UTC as default.");
            // Fallback to UTC
        }

//        refreshToken.setExpiryDate(Instant.now().plusMillis(expirationTime));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
//      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
