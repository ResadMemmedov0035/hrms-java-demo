package javademos.hrms;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HrmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(HrmsApplication.class, args);

//		String str1 = "Java";
//		String str2 = "Java";
//		
//		System.out.println(str1 == str2);
//		System.out.println(str1 == str2);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("javademos.hrms")).build();
	}

	@Bean
	public LocaleResolver localeResolver() {

		var slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dslzfqynw", 
				"api_key", "616956362454572", 
				"api_secret", "-c22qNE_zxK4E42x75RnygWK9VM"));
	}
}
