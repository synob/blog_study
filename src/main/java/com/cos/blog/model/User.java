package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> java(다른언어) object -> 테이블로  매핑 해주는 기술
@Entity //user  클래스가  mysql 에 테이블이 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //인서트시에 널인 컬럼 제외시킨다.
public class User {
	
	    @Id //primary key
	    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된  DB의 넘버링 전략을 따라간다.
		private int id; //시퀀스   auto_increment
		
	    @Column(nullable = false, length = 30)
		private String username;
	    
	    @Column(nullable = false, length = 100)
		private String password;
	    
	    @Column(nullable = false, length = 50)
		private String email;
	    
	    //@ColumnDefault("'USER'")
	    @Enumerated(EnumType.STRING)
	    private RoleType role; //Enum 을 쓰는것이 좋다 //admin, user, manager 
	    
	    @CreationTimestamp //시간이 자동으로 입력
		private Timestamp createDate;
}
