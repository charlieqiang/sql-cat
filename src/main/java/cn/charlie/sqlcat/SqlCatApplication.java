package cn.charlie.sqlcat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author charlie
 * @date 4/10/2023 10:25 AM
 **/
@SpringBootApplication
@MapperScan("cn.charlie.sqlcat.mapper")
public class SqlCatApplication {
    public static void main(String[] args) {
        SpringApplication.run(SqlCatApplication.class, args);
    }
}
