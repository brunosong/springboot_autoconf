package com.brunosong.config.autoconfig;

import com.brunosong.config.BrunoAutoConfiguration;
import com.brunosong.config.BrunoConfigurationProperty;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

@BrunoAutoConfiguration
public class PropertyPostProcessorConfig {

    /* postProcessAfterInitialization 를 사용하면 모든 빈의 초기화가 끝난다음에 실행을 해준다. 모든 빈이 한번씩 다 실행을 한다. */
    @Bean
    BeanPostProcessor processor(Environment environment) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

                BrunoConfigurationProperty annotation = AnnotationUtils.findAnnotation(bean.getClass(), BrunoConfigurationProperty.class);
                if(annotation == null) return bean;

                return Binder.get(environment).bindOrCreate("", bean.getClass());

            }
        };
    }

}
