package com.smirnoff.home.platform.user.profile.service.role;

import com.smirnoff.home.platform.user.profile.persistance.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Map<String, List<UserRole>> getRolesByProfileIds(List<String> profileIds);
}
