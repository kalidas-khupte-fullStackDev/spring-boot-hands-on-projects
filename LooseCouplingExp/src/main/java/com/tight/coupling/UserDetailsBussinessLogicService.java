package com.tight.coupling;

import com.loose.coupling.UserDataProviderInterface;

public class UserDetailsBussinessLogicService {

    UserDataProviderInterface userDataService;

    public UserDetailsBussinessLogicService(UserDataProviderInterface userDataService) {
        this.userDataService = userDataService;
    }

    public String toGetUserDetailsFromService() {
        return userDataService.getUserData();
    }
}
