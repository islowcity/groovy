package slow.city.service.Impl;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import slow.city.model.Groovy;
import slow.city.repository.GroovyRepository;
import slow.city.service.GroovyService;

@Service
@Transactional
public class GroovyServiceImpl implements GroovyService,InitializingBean{

	private static final Logger log = LoggerFactory.getLogger(GroovyServiceImpl.class);
	
	@Autowired
	private GroovyRepository groovyRepository;
	
	private GroovyClassLoader loader ;
	
	private Map<String,Class> clazzMap = new HashMap<>();
	/*
	 * question transaction or lock spring 代理是否合适
	 * (non-Javadoc)
	 * @see slow.city.service.GroovyService#run(java.lang.String, java.util.Map)
	 */
	//private ReentrantLock lock = new ReentrantLock();
	@Override
	public void run(String scriptText, Map<String, Object> context) {
		Class<?> clazz = clazzMap.get(scriptText);
		if(null == clazz){
			clazz = loader.parseClass(scriptText);
			clazzMap.put(scriptText, clazz);
		}
		Binding binding = new Binding();
		if(null != context){
			for(Entry<String,Object> entry:context.entrySet()){
				binding.setVariable(entry.getKey(), entry.getValue());
			}
		}
		Script script = InvokerHelper.createScript(clazz, binding);
		script.run();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loader = new GroovyClassLoader(GroovyServiceImpl.class.getClassLoader());
	}

	@Override
	public void invoke(Groovy groovy, Map<String, Object> context) {

	}

	@Override
	public Groovy findOneById(Integer id) {
		 
		return groovyRepository.findOneById(id);
	}
}
