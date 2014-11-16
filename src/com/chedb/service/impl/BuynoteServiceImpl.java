package com.chedb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.BuynoteDao;
import com.chedb.service.BuynoteService;
import com.forum.model.ModelBusinote;
import com.forum.util.GsonUtil;
import com.google.gson.reflect.TypeToken;
@Service("buynoteServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class BuynoteServiceImpl implements BuynoteService {
	@Resource(name = "buynoteDaoImpl")
	private BuynoteDao buynoteDao;

	@Override
	public List<ModelBusinote> queryBuynoteByUserId(String userId, Integer page)
			throws Exception {
		// TODO Auto-generated method stub
		return this.buynoteDao.getBuynoteListByUser(userId, page);
	}

	@Override
	public List<ModelBusinote> queryBuynoteByProviderId(String providerId,
			Integer page) throws Exception {
		// TODO Auto-generated method stub
		return this.buynoteDao.getBuynoteListByProviderId(providerId,page);
	}

	@Override
	public ModelBusinote queryBusinoteById(String busiNo) throws Exception {
		// TODO Auto-generated method stub
		return this.buynoteDao.getBusinoteById(busiNo);
	}

	@Override
	public void updateBusinoteInfo(String busiNo, String newStatus, String score)
			throws Exception {
		// TODO Auto-generated method stub
		this.buynoteDao.updateBusinoteInfo(busiNo, newStatus, score);

	}

	@Override
	public boolean buyService(String buynoteStr) throws Exception {
		// TODO Auto-generated method stub
		ModelBusinote buynote = GsonUtil.getGson().fromJson(buynoteStr,
				new TypeToken<ModelBusinote>() {}.getType());
		
		// 添加 一条购买记录
		if (buynoteDao.addBusi(buynote) == true) {
			return true;
		}
		return false;
	}

}
