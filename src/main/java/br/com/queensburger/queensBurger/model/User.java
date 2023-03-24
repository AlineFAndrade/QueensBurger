package br.com.queensburger.queensBurger.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name")
  private String name;
  
  @Column(name = "role")
  private String role;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_profile", 
  		     joinColumns = @JoinColumn(name = "users_id"),
		     inverseJoinColumns = @JoinColumn(name = "profile_id"))
  private List<Profile> profiles = new ArrayList<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public String getRole() {
	    return role;
	  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  } 

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  public List<Profile> getProfiles() {
	return profiles;
  }

  public void setProfiles(List<Profile> profiles) {
	this.profiles = profiles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
	  return getProfiles().stream().map(profile -> new SimpleGrantedAuthority(profile.getName().name()))
			.collect(Collectors.toList());
  }

  @Override
  public String getUsername() {
	  // TODO Auto-generated method stub
	  return null;
  }

  @Override
  public boolean isAccountNonExpired() {
	  // TODO Auto-generated method stub
	  return true;
  }

  @Override
  public boolean isAccountNonLocked() {
	  // TODO Auto-generated method stub
	  return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
	  // TODO Auto-generated method stub
	  return true;
  }

  @Override
  public boolean isEnabled() {
	  // TODO Auto-generated method stub
	  return true;
  }

}

