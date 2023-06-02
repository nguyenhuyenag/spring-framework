//package com.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "user_role")
//public class UserRole {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//
////	@ManyToOne
////	@JoinColumn(name = "user_id") // map với User thông qua user_id
////	private User user;
////
////	@ManyToOne
////	@JoinColumn(name = "role_id") // map với Role thông qua role_id
////	private Role role;
//	
//	@ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    private User user;
//    
//    @ManyToOne
//    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
//    private Role role;
//
//}
