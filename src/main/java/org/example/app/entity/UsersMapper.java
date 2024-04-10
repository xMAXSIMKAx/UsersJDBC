package org.example.app.entity;

import java.util.Map;

public class UsersMapper {

    public Users mapData(Map<String, String> data) {
        Users users = new Users();
        if (data.containsKey("id"))
            users.setId(Long.parseLong(data.get("id")));
        if (data.containsKey("name"))
            users.setName(data.get("name"));
            users.setEmail(data.get("email"));
        return users;
    }
}
