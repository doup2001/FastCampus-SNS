package com.fc.client;

import com.fc.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserClient {

    private final Map<Long, User> users = new HashMap<>();

    public UserClient(){
        users.put(1L, new User(1L, "user1", "imageUrl1"));
        users.put(2L, new User(2L, "user2", "imageUrl2"));
        users.put(3L, new User(3L, "user3", "imageUrl3"));
        users.put(4L, new User(4L, "user4", "imageUrl4"));
        users.put(5L, new User(5L, "user5", "imageUrl5"));

    }

    public User getUser(Long id){
        return users.get(id);
    }

    public Boolean isFollow(Long followerId, Long followeeId) {
        return true;
    }
}
