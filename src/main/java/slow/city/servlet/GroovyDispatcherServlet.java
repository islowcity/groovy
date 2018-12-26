package slow.city.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.util.StringUtils;

import slow.city.common.ApplicationContextHolder;
import slow.city.model.Groovy;
import slow.city.service.GroovyService;

public class GroovyDispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req,HttpServletResponse resp){
		
		String path = req.getRequestURI();
		if(path.startsWith("/groovy")){
			path = StringUtils.substringAfter(path, "/groovy");
			
			String channelCode = req.getHeader("slowcity.com");
			
			//获取GroovyService bean
			GroovyService groovyService = ApplicationContextHolder.getBean(GroovyService.class);
			String id = req.getParameter("id");
			if(null != id){
				Groovy groovy = groovyService.findOneById(Integer.valueOf(id));
				if(groovy == null){
					resp.setStatus(404);
				}else{
					String scriptText = groovy.getContent();
					groovyService.run(scriptText, null);
				}
			}else{
				
			}
		}
		
	}
	
	
}
