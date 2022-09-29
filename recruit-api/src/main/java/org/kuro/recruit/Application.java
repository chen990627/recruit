package org.kuro.recruit;

import cn.hutool.core.util.IdUtil;
import com.houkunlin.system.dict.starter.SystemDictScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SystemDictScan
@SpringBootApplication
@MapperScan(basePackages = "org.kuro.recruit.mapper")
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("接口文档：\nhttp://localhost:8360/doc.html\n");
    }
}
