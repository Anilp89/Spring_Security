package com.stark.dao;

import com.stark.model.User;

public interface UserRepository {
	public User getActiveUser(String userName);
}
