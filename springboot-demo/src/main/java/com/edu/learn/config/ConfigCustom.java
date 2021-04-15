package com.edu.learn.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConfigCustom {
//    @Value("${nihao}")
    private String name;
}
