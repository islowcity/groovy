package slow.city.service;

import java.util.Map;

import slow.city.model.Groovy;

public interface GroovyService {

	public void run(String scriptText, Map<String, Object> context);
	
	public void invoke(Groovy groovy, Map<String, Object> context);

	public Groovy findOneById(Integer id);
}
