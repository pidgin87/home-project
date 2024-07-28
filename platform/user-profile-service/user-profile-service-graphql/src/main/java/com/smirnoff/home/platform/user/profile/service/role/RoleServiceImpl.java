package com.smirnoff.home.platform.user.profile.service.role;

import com.smirnoff.home.platform.user.profile.persistance.entity.UserRole;
import com.smirnoff.home.platform.user.profile.persistance.entity.UserRoleEntity;
import com.smirnoff.home.platform.user.profile.persistance.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public Map<String, List<UserRole>> getRolesByProfileIds(List<String> profileIds) {
        List<UserRoleEntity> roles = userRoleRepository.findByUserProfile_IdIn(profileIds);
        Map<String, List<UserRole>> roleMap = new HashMap<>();
        for (UserRoleEntity role : roles) {
            roleMap.computeIfAbsent(role.getUserProfile().getId(), k -> new ArrayList<>()).add(role.getValue());
        }
        return roleMap;
    }
}
