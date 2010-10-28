package org.jbpmext.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_user")

public class User  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
     private String username;
     private String password;


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Long getId() {
        return this.id;
    }
     
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="username", length=32)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="password", length=16)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }


	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", username="
				+ username + "]";
	}
    
    
	public static void main(String args[]){
		User user = new User();
		System.out.println(user.toString());
		
	}
   

}