package com.brunosong.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class BrunoImportSelector implements ImportSelector {

    private final ClassLoader classloader;

    public BrunoImportSelector(ClassLoader classloader) {
        this.classloader = classloader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        /* com.brunosong.config.BrunoAutoConfiguration.imports에 같은 종류에 후보가 두개가 된 상황이다.
        *  총 3개의 인프라빈이 존재하는데 2개중 한개만 선택을 해야 하는 상황이다.
        * */
        List<String> list = new ArrayList<>();
        ImportCandidates.load(BrunoAutoConfiguration.class, classloader).forEach(list::add);

        return list.toArray(new String[0]);
    }

}
