package com.loose.coupling;

public class LooseUserWebServiceImpl implements UserDataProviderInterface {

    @Override
    public String getUserData() {
        return "User Details from Another DB or Web service";
    }
}
