package com.springsecurity.example.security;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.springsecurity.example.security.UserPermissions.*;

public enum UserRoles {
	STUDENT(Sets.newHashSet()),
	ADMIN (Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_WRITE, STUDENT_READ)), 
	ADMIN_TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));
	
	private final Set<UserPermissions> permissions;

	private UserRoles(Set<UserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<UserPermissions> getPermissions() {
		return permissions;
	} 

	public Set<SimpleGrantedAuthority> getAuthorities(){
		Set<SimpleGrantedAuthority> authorities =
				permissions.stream()
							.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
							.collect(Collectors.toSet());

		authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return authorities;
	}
}
