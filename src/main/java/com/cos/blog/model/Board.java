package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된  DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스   auto_increment
 
    @Column(nullable = false, length = 100)
    private String title;
    
    @Lob //대용량 데이터
    private String content;
    
    @ColumnDefault("0")
    private int count; //조회수
    
    @ManyToOne //many=many, User=one
    @JoinColumn(name="userId")
    private User user;
    
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //연관관계의 주인이 아니다(난 FK가아니다) 컬럼을만들지마.
    private List<Reply> reply;
    
    @CreationTimestamp
    private Timestamp createDate;
}
