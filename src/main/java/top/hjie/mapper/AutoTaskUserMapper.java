package top.hjie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import top.hjie.pojo.AutoTaskUser;

@org.apache.ibatis.annotations.Mapper
public interface AutoTaskUserMapper extends Mapper<AutoTaskUser> {

	// 获取未进行的任务
	AutoTaskUser getAutoTaskUserUnlocck();

	// 用户上锁
	void updateAutoTaskUserLocck(Long atuId);

	void updateAutoTaskUserUnLocck(Long atuId);

	AutoTaskUser getAutoTaskUserByCookie(String cookie);

	AutoTaskUser getAutoTaskUserByIpAddress(String ipAddress);

	List<AutoTaskUser> getAutoCommentTaskList(@Param("userCode")String userCode);

	Integer getAllStartIngUser();

	void updateAutoTaskUserTaskStatus(@Param("atuId")Long atuId, @Param("taskStatus")int taskStatus);
	
}
