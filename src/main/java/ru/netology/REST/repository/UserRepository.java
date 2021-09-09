package ru.netology.REST.repository;

import org.springframework.stereotype.Repository;
import ru.netology.REST.model.Authorities;
import ru.netology.REST.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.asList;


@Repository
public class UserRepository {
    private Map<User, List<Authorities>> users = new ConcurrentHashMap<>();

    {
        users.put(new User("ivan", "1234"), asList(Authorities.READ));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {

        return users.getOrDefault(new User(user,password),null);

    }
}
