package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.entities.Sport;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.entities.UserSport;
import com.pdstpo.unomas.model.enums.LevelEnum;
import com.pdstpo.unomas.repositories.ISportRepository;
import com.pdstpo.unomas.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISportRepository sportRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addUserSport(Integer userId, Integer sportId, boolean favorite, LevelEnum level) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con id: " + userId));

        Sport sport = sportRepository.findById(sportId).orElseThrow(() -> new EntityNotFoundException("No se encotró un deporte con id: " + sportId));

        if (user.getUserSports().stream()
                .anyMatch(us -> us.getSport().getId().equals(sport.getId()))) {
            throw new IllegalStateException("El jugador ya tiene registrado este deporte.");
        }

        UserSport userSport = new UserSport();
        userSport.setUser(user);
        userSport.setSport(sport);
        userSport.setLevel(level);
        userSport.setFavourite(favorite);
        user.getUserSports().add(userSport);

        userRepository.save(user);
    }

    @Override
    public void removeUserSport(Integer userId, Integer sportId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con id: " + userId));

        Sport sport = sportRepository.findById(sportId).orElseThrow(() -> new EntityNotFoundException("No se encotró un deporte con id: " + sportId));

        boolean removed = user.getUserSports().removeIf(s -> s.getId().getSportId().equals(sport.getId()));

        if (!removed) {
            throw new IllegalStateException("El jugador no tiene registrado el deporte que se intenta eliminar.");
        }

        userRepository.save(user);
    }
}
