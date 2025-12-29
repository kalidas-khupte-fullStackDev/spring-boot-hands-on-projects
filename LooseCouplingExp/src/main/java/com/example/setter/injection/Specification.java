package com.example.setter.injection;

public class Specification {

    private String modelName;
    private String companyMakerName;
    private String manufactureYear;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCompanyMakerName() {
        return companyMakerName;
    }

    public void setCompanyMakerName(String companyMakerName) {
        this.companyMakerName = companyMakerName;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "modelName='" + modelName + '\'' +
                ", companyMakerName='" + companyMakerName + '\'' +
                ", manufactureYear=" + manufactureYear +
                '}';
    }
}
