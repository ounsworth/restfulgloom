package ca.flearning.restfulgloom.dao;

import ca.flearning.restfulgloom.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    List<RefreshToken> findByToken(String token);
}
