package com.walkd.dmzing.auth.ajax;

import com.walkd.dmzing.domain.User;
import com.walkd.dmzing.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AjaxUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = repository.findByEmail(email).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException(email + "라는 사용자가 없습니다.");
		}

		return user.createUserDetails();
	}
}
