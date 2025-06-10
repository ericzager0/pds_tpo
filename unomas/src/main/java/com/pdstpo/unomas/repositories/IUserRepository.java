package com.pdstpo.unomas.repositories;

import com.pdstpo.unomas.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE ST_DWithin(location::geography, ST_SetSRID(ST_MakePoint(:lng, :lat), 4326)::geography, :distance)", nativeQuery = true)
    List<User> findUsersNearLocation(@Param("lng") double lng, @Param("lat") double lat, @Param("distance") double distance);
}
