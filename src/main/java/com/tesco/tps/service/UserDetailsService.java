package com.tesco.tps.service;

import com.tesco.tps.auth.support.service.v2.UserDetails;

public interface UserDetailsService {
	UserDetails getUserDetails(String uuid);

	UserDetails getUserDetailsBuAccessToken(String token);
}
