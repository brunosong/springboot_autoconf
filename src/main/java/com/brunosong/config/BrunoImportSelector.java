package com.brunosong.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class BrunoImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "com.brunosong.config.autoconfig.DispatcherServletConfig",
                "com.brunosong.config.autoconfig.ServletWebServerConfig"
        };
    }

}
