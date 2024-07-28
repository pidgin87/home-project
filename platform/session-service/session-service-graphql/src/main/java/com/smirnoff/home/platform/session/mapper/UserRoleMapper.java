package com.smirnoff.home.platform.session.mapper;

import com.smirnoff.home.platform.session.model.UserRoleDto;
import com.smirnoff.home.platform.session.persistance.entity.UserRole;
import com.smirnoff.home.platform.session.persistance.entity.UserRoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    List<UserRoleDto> map(List<UserRoleEntity> roles);

    default UserRoleDto map(UserRoleEntity role) {
        return mapRole(role.getRole());
    }

    UserRoleDto mapRole(UserRole role);
}
