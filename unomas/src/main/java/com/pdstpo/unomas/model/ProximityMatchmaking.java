package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.repositories.IUserRepository;
import org.locationtech.jts.geom.Point;
import java.util.List;


public class ProximityMatchmaking implements IMatchmakingStrategy {

    IUserRepository userRepository;

    public ProximityMatchmaking(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> search(Match match) {

        Point point = match.getLocation();

        return userRepository.findUsersNearLocation(point.getX(), point.getY(), 100);
    }
}
