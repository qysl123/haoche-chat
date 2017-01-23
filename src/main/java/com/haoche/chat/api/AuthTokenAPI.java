package com.haoche.chat.api;

public interface AuthTokenAPI{	
	Object getAuthToken(String clientId, String clientSecret);
}
