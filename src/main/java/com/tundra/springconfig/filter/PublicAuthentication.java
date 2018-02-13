package com.tundra.springconfig.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class PublicAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String token;

	private final String principle;
	
    public PublicAuthentication( String principle) {
        super(null);
        this.principle = principle;
    }

    public PublicAuthentication( String principle, String token) {
        super(null);
        this.principle = principle;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String getPrincipal() {
        return principle;
    }

}
