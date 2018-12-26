package slow.city;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;

import java.io.File;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jboss.logging.Logger;

public class GroovyJavaExample {

	Logger log = Logger.getLogger(getClass());
	/*
	 * GroovyShell
	 */
	public String groovySheel() {
		Binding binding = new Binding();
		binding.setVariable("name", "Tom");
		binding.setVariable("age", 16);
		binding.setProperty("favourite", "play paino");
		GroovyShell shell = new GroovyShell(binding);
		String script = "println \"welcome to $name ;age is $age;favourite is $favourite \"; return favourite";
		Object value = shell.evaluate(script);
		log.info("value:" + value);
		return value.toString();
	}

	/*
	 * GroovyScriptEngine
	 */
	public String groovyScriptEngineExample() {
		String result = "";
		try {
			// 定义groovy脚本引擎的根路径
			String[] roots = new String[] { ".\\src\\main\\groovyscript" };  
			GroovyScriptEngine engine = new GroovyScriptEngine(roots);

			Binding binding = new Binding();
			binding.setVariable("language", "groovy");
			// script.groovy 脚本引擎根路径下的 脚本名
			Object value = engine.run("script.groovy", binding);
			log.info("value：" + value);
			result = value.toString();
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public String groovyClassLoader() {
		GroovyClassLoader loader = null;
		try {
			loader = new GroovyClassLoader();
			String groovyFilePath = "E:/workspace-eclipse/city/src/main/groovyscript/groovy1.groovy";
			Class groovyClass = loader.parseClass(new File(groovyFilePath));
			GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
			String result = (String) groovyObject.invokeMethod("fun", "你好");

			log.info("groovy 脚本返回的结果：" + result);

			loader.close();
		} catch (Exception e) {
			log.error(e);
		} 
		return null;
	}

	/*
	 * java specification request 223 ScriptEngine接口
	 */
	public String jsrApi() {
		String result = "";
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("groovy");
		String helloLanguage = "def hello(language){return \"hello $language\"}";
		try {
			engine.eval(helloLanguage);

			Invocable inv = (Invocable) engine;
			Object[] params = {"Groovy"};
			Object value = inv.invokeFunction("hello", params);
			log.info("value： " + value);
			result = value.toString();
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}
}
