package com.example.profile.config.demo;

import com.example.profile.config.demo.common.config.AllCentralConfig;
import com.example.profile.config.demo.common.config.AllCentralConfigSpring;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/get/app/info")
@Data
@AllArgsConstructor
@ToString
public class ConfigController {

//    Application properties getting set from @ConfigurationProperties
//    private String buildId;
//    private String buildVersion;
//    private String buildName;

    private AllCentralConfig allCentralConfig;

    private AllCentralConfigSpring allCentralConfigSpring;

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
//        controller.setBuildId(allCentralConfig.);
//        controller.setBuildVersion(buildVersion);
//        controller.setBuildName(buildName);

//        controller.setOsName(osName);
//        controller.setJHomePath(jHomePath);
//        controller.setCurrentUserRootDirectory(currentUserRootDirectory);

        String buildInfoStr = String.join(" ",  "Spring Active profile:", allCentralConfigSpring.getActive() , "Build Id:", allCentralConfig.getId().toString()
                , "Version:", allCentralConfig.getVersion().toString(), "Name:", allCentralConfig.getName());

        return "Build & Env Info : " + buildInfoStr;
    }
}
