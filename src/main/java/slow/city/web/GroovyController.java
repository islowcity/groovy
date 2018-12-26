package slow.city.web;


import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import slow.city.common.ApplicationContextHolder;
import slow.city.model.Groovy;
import slow.city.service.GroovyService;

@RestController
@RequestMapping("/groovy")
public class GroovyController {

	@Autowired
	private GroovyService grovyService;
	
    @RequestMapping("/runScript/{id}")
    public String runScript(@PathVariable Integer id) throws Exception {
		Binding binding = new Binding();
		binding.setVariable("name", "Tom");
		binding.setVariable("age", 16);
		binding.setProperty("favourite", "play paino");
		GroovyShell shell = new GroovyShell(binding);
		Groovy groovy = grovyService.findOneById(id);
		/* "println \"welcome to $name ;age is $age;favourite is $favourite \"; return favourite";*/
		String script = groovy.getContent();
		Object value = shell.evaluate(script);
	 
		return value.toString();
    }

    
    @GetMapping("/test")
    public String test(){
    	//ApplicationContextHolder.getInstance();
		GroovyService groovyService = ApplicationContextHolder.getInstance().getBean(GroovyService.class);
    	
    	System.out.println("groovyService:" + groovyService);
    	return "success";
    }
}