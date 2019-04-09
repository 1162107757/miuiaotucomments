package top.hjie.task;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import top.hjie.mapper.AutoTaskUserMapper;

@Configuration
@Component
@EnableScheduling
public class AutoComment {
	
	@Resource
	private AutoTaskUserMapper autoTaskUserMapper;
	
	void assignmentAask() throws Exception{
		
		Integer number = autoTaskUserMapper.getAllStartIngUser();
		if(number < 10){
			Class<?> clazz = Class.forName("top.hjie.task.AutoComment1");
			Method method = clazz.getMethod("startTask",AutoTaskUserMapper.class);
			method.invoke(clazz.newInstance(),autoTaskUserMapper);
		}
		
		/*for (int i = 1; i <= 1; i++) {
			Class<?> clazz = Class.forName("top.hjie.task.AutoComment" + i);
			Method method = clazz.getMethod("startTask",AutoTaskUserMapper.class);
			method.invoke(clazz.newInstance(),autoTaskUserMapper);
		}*/
	}
	
}
