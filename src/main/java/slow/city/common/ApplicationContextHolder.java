package slow.city.common;
import java.io.IOException;
import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

public class ApplicationContextHolder implements ApplicationContextAware {

	private static final ApplicationContextHolder INSTANCE = new ApplicationContextHolder();
   
	static ApplicationContext applicationContext;

    private ApplicationContextHolder(){}
    
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

    public static ApplicationContextHolder getInstance() {
        return   INSTANCE;
    }
    public static void autowireBean(Object bean) {
    	applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);
    }

    /**
     * get bean by given type
     * @param type
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    /**
     * get beans by given type
     * @param type
     * @return
     */
    public static <T> Collection<T> getBeans(Class<T> type){
    	return applicationContext.getBeansOfType(type).values();
    }
    
    /**
     * get resource
     * @param location
     * @return
     */
    public static Resource getResource(String location){
    	return applicationContext.getResource(location);
    }
    
    /**
     * get resources
     * @param location
     * @return
     * @throws IOException 
     */
    public static Resource[] getResources(String locationPattern) throws IOException{
    	return applicationContext.getResources(locationPattern);
    }
    
    
    /**
     * get bean by given name
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
    	return (T) applicationContext.getBean(beanName);
    }
    
    /**
     * get spring environment
     * @return
     */
    
    public static Environment getEnv( ){
    	return applicationContext.getEnvironment();
    }
}
