# Spring Boot Keycloak

# Run keycloak 

		/bin/standalone.bat
	
	+ Setup environment 
	
		CMD  >	standalone.bat
			
	+ http://localhost:8080/auth/

# Change port

	+ Open: /standalone/configuration/standalone.xml
	
		<socket-binding name="http" port="${jboss.http.port:8080}"/>	

# Create user
	
	bin/add-user-keycloak.bat -r master -u admin -p admin



	