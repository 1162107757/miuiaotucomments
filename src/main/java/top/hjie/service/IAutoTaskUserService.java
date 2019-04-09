package top.hjie.service;


import com.github.pagehelper.PageInfo;

import top.hjie.pojo.AutoTaskUser;

public interface IAutoTaskUserService {

	AutoTaskUser addAutoCommentTask(AutoTaskUser autoTaskUser);

	PageInfo<AutoTaskUser> getAutoCommentTaskList(String userCode, Integer page);

}
