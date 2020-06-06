package az.maqa.project.webservices;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

	public Set<Class<?>> getClasses() {
		Set<Class<?>> set1 = new HashSet<>();
		set1.add(WebServices.class);
		return set1;

	}

}
