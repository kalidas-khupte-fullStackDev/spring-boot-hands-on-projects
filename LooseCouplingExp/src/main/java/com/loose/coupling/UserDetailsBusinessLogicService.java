package com.loose.coupling;

public class UserDetailsBusinessLogicService {

    UserDataProviderInterface i_userDataService;

    public UserDetailsBusinessLogicService() {
    }

    public void setI_userDataService(UserDataProviderInterface i_userDataService) {
        this.i_userDataService = i_userDataService;
    }

    public UserDetailsBusinessLogicService(UserDataProviderInterface i_userDataService) {
        this.i_userDataService = i_userDataService;
    }

    public String toGetUserDetailsFromService() {
        return i_userDataService.getUserData();
    }
}
