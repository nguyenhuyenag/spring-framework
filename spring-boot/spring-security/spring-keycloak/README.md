# Spring Boot Keycloak

# Run keycloak 

		keycloak-12.0.1\bin\standalone.bat
	
	+ Setup environment 
	
		CMD > standalone.bat
			> standalone.bat --http-port=8081
	
	+ http://localhost:8080/auth/
	

# Create user
	
	bin/add-user-keycloak.bat -r master -u admin -p admin
