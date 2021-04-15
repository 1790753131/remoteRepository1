package com.edu.learn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;


/***
 * ConfigurationProperties 读取自定义配置属性的值
 */
@Component
@ConfigurationProperties(prefix ="config-attributes")
@Data
public class ConfigProperty {
        private String value;
        private String[]  valueArray;
        private List<String> valueList;
        private Map<String,Object> valueMap;
        private  List<Map<String,Object>> valueMapList;
}
