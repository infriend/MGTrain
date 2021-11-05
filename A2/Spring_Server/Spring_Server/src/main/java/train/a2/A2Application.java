package train.a2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import train.a2.interceptor.SessionInterceptor;

@MapperScan(
        basePackages = "train.a2.dao",
        annotationClass = Repository.class
)
@SpringBootApplication
@EnableAsync
public class A2Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(A2Application.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new SessionInterceptor());
        ir.addPathPatterns("/admin/*");
        ir.addPathPatterns("/customer/*");
        ir.excludePathPatterns("/admin/login");
        ir.excludePathPatterns("/customer/login");
        ir.excludePathPatterns("/customer/regpage");
        ir.excludePathPatterns("/customer/regist");
    }
}
