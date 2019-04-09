package top.hjie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;

import top.hjie.pojo.AutoTaskUser;
import top.hjie.service.IAutoTaskUserService;

@Controller
@RequestMapping("/task")
public class autoTaskController {

	@Resource
	private IAutoTaskUserService autoTaskUserService;
	
	@RequestMapping("/addAutoCommentTask")
	public ResponseEntity<?> addAutoCommentTask(@RequestBody AutoTaskUser autoTaskUser,HttpServletRequest request){
		Map<String, Object> success = new HashMap<>();
		try {
			autoTaskUser.setIpAddress(request.getRemoteAddr());
			AutoTaskUser atu = autoTaskUserService.addAutoCommentTask(autoTaskUser);
			success.put("success", true);
			success.put("msg", "添加成功！您的任务编码为： " + atu.getUserCode() + " 请牢记您的编码！");
		} catch (Exception e) {
			e.printStackTrace();
			success.put("success", false);
			success.put("msg", "添加失败！");
			success.put("data", null);
		}
		return ResponseEntity.ok(success);
	}
	
	@RequestMapping("/getAutoCommentTaskList")
	public ResponseEntity<?> getAutoCommentTaskList(String userCode,Integer page){
		PageInfo<AutoTaskUser> autoTaskUserList = autoTaskUserService.getAutoCommentTaskList(userCode,page);
		return ResponseEntity.ok(autoTaskUserList);
	}
	
}
