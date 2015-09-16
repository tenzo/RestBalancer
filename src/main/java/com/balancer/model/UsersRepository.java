package com.balancer.model;

import org.jvnet.hk2.annotations.*;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsersRepository {
    private Map<String, List <String>> groups;

    public UsersRepository(Map<String, Double> groupsConfig) {
        groups = groupsConfig.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> new ArrayList<>()
                ));
    }

    public Optional<String> getUserGroup (String userId) {
        return groups.entrySet().stream()
                    .filter(entry -> entry.getValue().contains(userId))
                    .findAny()
                    .map(entry -> entry.getKey());

    }

    public void addUserToGroup(String groupName, String userId) {
        if (groups.containsKey(groupName)) {
            groups.get(groupName).add(userId);
        }
    }

    public Map<String, Double> getCurrentBalance() {
        return groups.entrySet().stream()
                .collect(Collectors.toMap(
                   entry -> entry.getKey(),
                    entry -> entry.getValue().size()/getAllUsersCount()
                ));
    }

    private Double getAllUsersCount() {
        return 1.0d * groups.entrySet().stream()
                .mapToInt(entry -> entry.getValue().size())
                .sum();
    }
}
