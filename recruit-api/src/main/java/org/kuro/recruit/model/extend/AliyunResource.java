package org.kuro.recruit.model.extend;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:application.yml", encoding = "UTF-8")
@ConfigurationProperties(prefix = "aliyun")
public class AliyunResource {

    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucketName;
    private String signName;

}
