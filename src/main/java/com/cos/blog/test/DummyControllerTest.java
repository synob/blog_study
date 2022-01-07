package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,  @RequestBody User updateUser) {
		System.out.println("password : " +updateUser.getPassword());
		System.out.println("email : " +updateUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("업데이트에 실패하였습니다.");
		});
		user.setEmail(updateUser.getEmail());
		user.setPassword(updateUser.getPassword());
		
		//userRepository.save(user); //주석 더티 체킹
		return user;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제 실패 하였습니다.";
		}
		
		return "삭제되었습니다.. id : "+id;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id" , direction = Direction.DESC) Pageable pageable){
		List<User> users =  userRepository.findAll(pageable).getContent();
		return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int  id) {
		
		//람다식
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("유저가 없습니다");
		});
		/*
		 * User user = userRepository.findById(id).orElseThrow(new
		 * Supplier<IllegalArgumentException>() {
		 * 
		 * @Override public IllegalArgumentException get() { return new
		 * IllegalArgumentException(" 유저가 없습니다. id: "+ id); }});
		 */
		// 요청: 웹브라우저
		// user  객체  = 자바 오브젝트
		// 변환 (웹브라우저가 이해할수 있는 데이타) ->   json(Gson  lib)
		// 스프링 부튼 messageconvertor 가 Jackson 라이브러리를 호출해서 user  오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user ;
	}
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username : " +user.getUsername());
		System.out.println("password : " +user.getPassword());
		System.out.println("email : " +user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입완료!!";
	}

}
