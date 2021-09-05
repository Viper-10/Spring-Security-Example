package com.springsecurity.example.security;

import java.util.Set;
import java.util.HashSet;
import com.google.common.collect.Sets; 
import static com.springsecurity.example.security.UserPermissions.*;

public enum UserRoles {
	STUDENT(new HashSet<>()), 
	ADMIN (Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_WRITE, STUDENT_READ)), 
	ADMIN_TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));
	
	private final Set<UserPermissions> permissions;

	private UserRoles(Set<UserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<UserPermissions> getPermissions() {
		return permissions;
	} 
	
}
