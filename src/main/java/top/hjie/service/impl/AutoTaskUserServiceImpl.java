package top.hjie.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import top.hjie.mapper.AutoTaskUserMapper;
import top.hjie.pojo.AutoTaskUser;
import top.hjie.service.IAutoTaskUserService;

@Service
public class AutoTaskUserServiceImpl implements IAutoTaskUserService {

	@Resource
	private AutoTaskUserMapper autoTaskUserMapper;
	
	@Override
	public AutoTaskUser addAutoCommentTask(AutoTaskUser autoTaskUser) {
		AutoTaskUser atuCookie = autoTaskUserMapper.getAutoTaskUserByCookie(autoTaskUser.getCookie());
		if(atuCookie != null){
			throw new RuntimeException("您已添加过任务，请不要重复添加！");
		}
		/*AutoTaskUser atuIpAddress = autoTaskUserMapper.getAutoTaskUserByIpAddress(autoTaskUser.getIpAddress());
		if(atuIpAddress != null){
			throw new RuntimeException("您已添加过任务，请不要重复添加！");
		}*/
		autoTaskUser.setCreateTime(new Date());
		autoTaskUser.setTaskStatus(1);
		autoTaskUser.setUserCode(createUserCode());
		autoTaskUser.setLockStatus(0);
		autoTaskUserMapper.insertSelective(autoTaskUser);
		return autoTaskUser;
	}
	
	
	public String createUserCode(){
		String[] code = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
		StringBuffer userCode = new StringBuffer("ak_");
		for (int i = 0; i < 6; i++) {
			int index = new Random().nextInt(code.length);
			userCode.append(code[index]);
		}
		
		Example queryMemberByCode = new Example(AutoTaskUser.class);
		queryMemberByCode.createCriteria().andEqualTo("userCode", userCode.toString());
		List<AutoTaskUser> userList = autoTaskUserMapper.selectByExample(queryMemberByCode);
		if(userList != null && userList.size() != 0){
			// 清空
			userCode.setLength(0);
			userCode.append(createUserCode());
		}
		return userCode.toString();
	}


	@Override
	public PageInfo<AutoTaskUser> getAutoCommentTaskList(String userCode, Integer page) {
		if(page == 0){
			page = 1;
		}
		PageHelper.startPage((page - 1) * 10,1000);
		List<AutoTaskUser> autoCommentTaskList = autoTaskUserMapper.getAutoCommentTaskList(userCode);
		PageInfo<AutoTaskUser> info = new PageInfo<>(autoCommentTaskList);
		for (AutoTaskUser autoTaskUser : info.getList()) {
			autoTaskUser.setCookie(null);
			autoTaskUser.setFormHash(null);
			if(autoTaskUser.getTaskStatus().equals(1)){
				autoTaskUser.setTaskStatusName("等待");
			}else if(autoTaskUser.getTaskStatus().equals(2)){
				autoTaskUser.setTaskStatusName("进行中");
			}else if(autoTaskUser.getTaskStatus().equals(3)){
				autoTaskUser.setTaskStatusName("结束");
			}else if(autoTaskUser.getTaskStatus().equals(4)){
				autoTaskUser.setTaskStatusName("cookie或formhash无效");
			}
			if(StringUtils.isNotBlank(autoTaskUser.getBbsName())){
				String startName = autoTaskUser.getBbsName().substring(autoTaskUser.getBbsName().length() / 2).replaceAll("[^x00-xff]|\\w", "*");
				String endName = autoTaskUser.getBbsName().substring(autoTaskUser.getBbsName().length() / 2,autoTaskUser.getBbsName().length());
				autoTaskUser.setBbsName(startName + endName);
			}
		}
		return info;
	}
}
