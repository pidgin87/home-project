package com.smirnoff.home.ui.service.userprofile;

import com.smirnoff.home.platform.user.profile.model.UserProfile;

public interface UserProfileService {
    UserProfile getProfileByEmail(String email);
}
