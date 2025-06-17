package com.pdstpo.unomas.repositories;

import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.enums.LevelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE ST_DWithin(location::geography, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326)::geography, :distance)", nativeQuery = true)
    List<User> findUsersNearLocation(@Param("lng") double lng, @Param("lat") double lat, @Param("distance") double distance);

    @Query(value = "SELECT u.* FROM users u " +
            "JOIN match_users mu ON mu.user_id = u.id " +
            "JOIN matches m ON m.id = mu.match_id " +
            "WHERE m.sport_id = :sportId AND m.state = 'finalizado' " +
            "GROUP BY u.id " +
            "HAVING COUNT(m.id) >= :minCompletedMatches",
            nativeQuery = true)
    List<User> findUsersWithMinCompletedMatches(@Param("minCompletedMatches") int minCompletedMatches, @Param("sportId") Integer sportId);

    @Query(value = "SELECT * FROM users u JOIN user_sports us ON us.user_id = u.id WHERE us.sport_id = :sportId AND us.favourite = true",
            nativeQuery = true)
    List<User> findUsersIsFavoriteSport(@Param("sportId") Integer sportId);

    @Query(value = "SELECT * FROM users u JOIN user_sports us ON us.user_id = u.id WHERE us.sport_id = :sportId AND (:minLevel IS NULL OR us.level >= :minLevel) AND (:maxLevel IS NULL OR us.level <= :maxLevel)",
            nativeQuery = true)
    List<User> findUsersByLevel(@Param("sportId") Integer sportId, @Param("minLevel") Integer minLevel, @Param("maxLevel") Integer maxLevel);
}
