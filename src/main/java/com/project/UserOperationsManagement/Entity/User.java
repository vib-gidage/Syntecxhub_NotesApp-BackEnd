 package com.project.UserOperationsManagement.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int userId;
		
		@NotBlank(message = "Username is required")
		@Column(nullable = false, unique = true)
		private String userName;
		
		@NotBlank(message = "Password is required") 
		
		@Pattern(
		        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
		        message = "Password must contain uppercase, lowercase, number and special character"
		    ) 
		
		@Column(nullable = false)
		private String password;
		
		@NotBlank(message = "Email is required")
		@Email(message = "Invalid email format")
		@Column(nullable = false,unique = true)
		private String email;
		
		@NotBlank(message = "Name is required")
		private String name;
		
		private int age;
		
		public User() {
			super();
		}
		
		public User(int userId, String userName, String password, String email, String name, int age) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.password = password;
			this.email = email;
			this.name = name;
			this.age = age;
		}
		
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", name=" + name + ", age="
					+ age + "]";
		}
		
		

	}





