package com.flowerhada.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.flowerhada.domain.User;

@Mapper
public interface UserMapper {
	public User readUser(Long id);
	public List<String> readAuthority(Long id);
	public void createAuthority(User user);
	public void deleteAuthority(Long id);
}
