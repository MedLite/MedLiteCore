package com.FrameWork.MedLite.Authentification.repository;

import com.FrameWork.MedLite.Authentification.domaine.RefreshToken;
import com.FrameWork.MedLite.Authentification.domaine.User;
 import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

 

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);
  
     RefreshToken  findByUser(User user);


   @Modifying
  int deleteByUser(User user);
//   public void deleteByUser(User user);
}
