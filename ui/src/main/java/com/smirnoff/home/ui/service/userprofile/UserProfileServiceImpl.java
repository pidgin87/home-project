package com.smirnoff.home.ui.service.userprofile;

import com.smirnoff.home.platform.user.profile.model.UserProfile;
import com.smirnoff.home.ui.adapter.userprofile.UserProfileAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileAdapter userProfileAdapter;

    @Override
    public UserProfile getProfileByEmail(String email) {
        UserProfile profile = userProfileAdapter.getProfileByEmail(email);
        if (isNull(profile)) {
            return userProfileAdapter.createEmptyProfileByEmail(email);
        }
        return profile;
    }
}
