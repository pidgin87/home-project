package com.smirnoff.home.ui.adapter.userprofile;

import com.smirnoff.home.platform.user.profile.model.UserProfile;

public interface UserProfileAdapter {
    UserProfile getProfileByEmail(String email);

    UserProfile createEmptyProfileByEmail(String email);
}
