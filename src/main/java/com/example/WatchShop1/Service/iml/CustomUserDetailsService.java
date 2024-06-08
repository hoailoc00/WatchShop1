package com.example.WatchShop1.Service.iml;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.AccountEntity;
import com.example.WatchShop1.Entity.CustomUserDetails;
import com.example.WatchShop1.Entity.PermissionEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity accountEntity=accountService.findByUsername(username);
		if(accountEntity==null) {
			throw new UsernameNotFoundException("Account Not Found");
		}
		Collection<GrantedAuthority> authorities=new HashSet<GrantedAuthority>();
		PermissionEntity permissionEntity=accountEntity.getPermission();
		authorities.add(new SimpleGrantedAuthority(permissionEntity.getName()));
		return new CustomUserDetails(accountEntity, authorities);
	}

}
