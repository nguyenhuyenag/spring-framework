package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.AppRoleDAO;
import com.dao.AppUserDAO;
import com.entity.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserDAO appUserDAO;

	@Autowired
	private AppRoleDAO appRoleDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser appUser = appUserDAO.findUserAccount(userName);
		if (appUser == null) {
			System.out.println("User `" + userName + "` not found!");
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		System.out.println("Found User: " + appUser);
		// [ROLE_USER, ROLE_ADMIN,...]
		List<String> roleNames = appRoleDAO.getRoleNames(appUser.getUserId());
		List<GrantedAuthority> grantList = new ArrayList<>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN, ...
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		return (UserDetails) new User(appUser.getUserName(), appUser.getEncrytedPassword(), grantList);
	}

}
