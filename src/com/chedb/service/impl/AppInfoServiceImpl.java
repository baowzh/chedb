package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.AppinfoDao;
import com.chedb.service.AppInfoService;
import com.forum.model.ModelPublicItem;
import com.forum.model.ModelVersionInfo;

@Service("appInfoServiceImpl")
@Transactional
public class AppInfoServiceImpl implements AppInfoService {
	@Resource(name = "appinfoDaoImpl")
	private AppinfoDao appinfoDao;

	@Override
	public List<ModelPublicItem> getAppinfoListByType(String type)
			throws Exception {
		// TODO Auto-generated method stub
		return this.getAppinfoListByType(type);
	}

	@Override
	public ModelVersionInfo getgetNowVersion(String appId) throws Exception {
		// TODO Auto-generated method stub
		return this.getgetNowVersion(appId);
	}

	@Override
	public boolean commitComplain(String type, String strSingleTitleList,
			String userId, String text) throws Exception {
		// TODO Auto-generated method stub
		return this.appinfoDao.userCommit("complain", strSingleTitleList, text, userId);
	}

	@Override
	public boolean commitFreeback(String type, String strSingleTitleList,
			String userId, String text) throws Exception {
		// TODO Auto-generated method stub
		return this.appinfoDao.userCommit("freeback", strSingleTitleList, text, userId);
	}

}
