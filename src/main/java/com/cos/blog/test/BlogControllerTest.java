package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 스프링이 패키지 이하를 스캔해서 모든 파일을  new 하는것은 아니고
						// 특정 어노테이션이 붙어있는 클래스 파일들을  new해서(ioc)스프링컨테이너에 관리해줍니다.
public class BlogControllerTest {
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}

}
