package com.example.profile.config.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get/app/info")
@RefreshScope
public class ConfigController {

//    Application properties getting set from @ConfigurationProperties
    @Value("${build.id:default}")
    private String buildId;
    @Value("${build.version:default}")
    private String buildVersion;
    @Value("${build.name:default}")
    private String buildName;

    // private AllCentralConfig allCentralConfig;

   // private AllCentralConfigSpring allCentralConfigSpring;

    // Environmental properties
//    @Value("${OS:default}")
//    private String osName;
//
//    @Value("${USERPROFILE:default}")
//    private String currentUserRootDirectory;
//    @Value("${JAVA_HOME:default}")
//    private String jHomePath;

    @GetMapping
    public String getBuildInfo(){

//        controller.setOsName(osName);
//        controller.setJHomePath(jHomePath);
//        controller.setCurrentUserRootDirectory(currentUserRootDirectory);

//        String buildInfoStr = String.join(" ",  "Spring Active profile:", allCentralConfigSpring.getActive() , "Build Id:", allCentralConfig.getId().toString()
//                , "Version:", allCentralConfig.getVersion().toString(), "Name:", allCentralConfig.getName());

        String buildInfoStr = String.join(" ","Build Id:", buildId
              , "Version:", buildVersion, "Name:", buildName);

        return "Build Info : " + buildInfoStr;
        //return "Build & Env Info : " + buildInfoStr;
    }
}
