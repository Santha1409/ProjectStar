package com.tesco.tps.secure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.tesco.tps.auth.support.service.v2.UserDetails;
import com.tesco.tps.service.UserDetailsService;

/**
 * @author a483 Rabindra 18 May 2016 11:08:55
 * 
 */
public abstract class AbstractSimpleAuthenticator {
	private static final String ALL = "ALL";

	public UserDetails getUserDetailsV2(String token) {
		UserDetails userDetails = getUserDetailsService().getUserDetailsBuAccessToken(token);
System.out.println("Amit-"+userDetails);
		Map<String, com.tesco.tps.auth.support.service.v2.AccessPolicy> mapping = new HashMap<>();
		if (userDetails != null) {
			userDetails.getGroups().parallelStream().forEach(grp -> {
				grp.getRoles().stream().forEach(role -> {
					if (role.getAccess() != null && !role.getAccess().isEmpty()) {
						role.getAccess().stream().map(access -> prepareUserDetailsV2(mapping, access))
								.collect(Collectors.toList());
					}
				});

			});
			userDetails.setAccessPolicy(mapping);
		}
		return userDetails;
	}

	private com.tesco.tps.auth.support.service.v2.AccessPolicy prepareUserDetailsV2(
			Map<String, com.tesco.tps.auth.support.service.v2.AccessPolicy> mapping, UserDetails.Access access) {
		if (mapping.containsKey(access.getResourceName())) {
			com.tesco.tps.auth.support.service.v2.AccessPolicy authMap = mapping.get(access.getResourceName());
			if (authMap.getPermissions() != null) {
				if (access.getPermissions() == null || access.getPermissions().isEmpty()
						|| access.getPermissions().contains(ALL)) {
					authMap.setPermissions(Arrays.asList(ALL));
				} else {
					authMap.getPermissions().addAll(access.getPermissions().stream()
							.map(permission -> permission.getName()).collect(Collectors.toList()));
				}
			}
			if (authMap.getFilters() != null) {
				if (access.getFilters() == null || access.getFilters().isEmpty()
						|| access.getFilters().parallelStream()
								.anyMatch(filter -> filter.getProperty().equalsIgnoreCase(ALL)
										&& filter.getValue().equalsIgnoreCase(ALL))) {
					authMap.setFilters(access.getFilters().parallelStream()
							.filter(filter -> filter.getProperty().equalsIgnoreCase(ALL)
									&& filter.getValue().equalsIgnoreCase(ALL))
							.collect(Collectors.toList()));
				} else {
					authMap.getFilters().addAll(access.getFilters());
				}
			}
			return authMap;
		} else {
			return mapping.put(access.getResourceName(),
					new com.tesco.tps.auth.support.service.v2.AccessPolicy(access.getPermissions().stream()
							.map(permission -> permission.getName()).collect(Collectors.toList()),
							access.getFilters()));
		}
	}

	abstract protected UserDetailsService getUserDetailsService();

}
