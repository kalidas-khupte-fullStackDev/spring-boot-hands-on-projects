package com.loose.coupling;

public class LooseUserDataBaseService implements UserDataProviderInterface {

    @Override
    public String getUserData() {
        return "User Details from DB";
    }
}
