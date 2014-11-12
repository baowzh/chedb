package com.forum.servlet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.forum.model.ModelPublicItem;
import com.forum.model.ModelSysItem;
import com.forum.model.ModelProviderItem;
import com.forum.model.ModelProvider;


public class NoDbData {
	
	/**
	 * 查询系统提供的服务类型
	 * @param level 商家级别，商家分为两类，1为修理厂，2为配件销售商
	 * @return
	 */
	static public List<ModelSysItem> querySysItemClassList(int level) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		label = new ModelSysItem();
		label.setLabelId("101");
		label.setTitle("车表清洗");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("102");
		label.setTitle("漆面美容");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("103");
		label.setTitle("外部装饰");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("104");
		label.setTitle("轮胎");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("105");
		label.setTitle("保养");
		list.add(label);
		
		return list;
	}
	
	/**
	 * 查询某个服务类下的服务
	 * @param parent 父类
	 * @return
	 */
	static public List<ModelSysItem> querySysItemByClassId(String itemClassId) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		if (itemClassId.equals("101")) {
		label = new ModelSysItem();
		label.setLabelId("101001");
		label.setParentId("101");
//		label.setSelect(1);
		label.setTitle("洗车");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101002");
		label.setParentId("101");
		label.setTitle("精致洗车");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101003");
		label.setParentId("101");
		label.setTitle("全自动电脑洗车");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101004");
		label.setParentId("101");
		label.setTitle("车内清洁");
		list.add(label);
		} else if (itemClassId.equals("102")) {
		label = new ModelSysItem();
		label.setLabelId("102001");
		label.setParentId("102");
		label.setTitle("漆面打蜡");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("102002");
		label.setParentId("102");
		label.setTitle("划痕修复");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("102003");
		label.setParentId("102");
		label.setTitle("新车开蜡");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("102004");
		label.setParentId("102");
		label.setTitle("漆面封釉");
		list.add(label);
		
		} else if (itemClassId.equals("103")) {
		label = new ModelSysItem();
		label.setLabelId("103001");
		label.setParentId("103");
		label.setTitle("全车贴膜");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("103002");
		label.setParentId("103");
		label.setTitle("贴安全膜");
//		label.setSelect(1);
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("103003");
		label.setParentId("103");
		label.setTitle("天使眼");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("103004");
		label.setParentId("103");
		label.setTitle("底盘装甲");
		list.add(label);
		
		} else if (itemClassId.equals("104")) {
		label = new ModelSysItem();
		label.setLabelId("104001");
		label.setParentId("104");
		label.setTitle("补胎");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("104002");
		label.setParentId("104");
		label.setTitle("四轮定位");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("104003");
		label.setParentId("104");
		label.setTitle("换胎");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("104004");
		label.setParentId("104");
		label.setTitle("轮毂");
		list.add(label);
		
		} else if (itemClassId.equals("105")) {
		label = new ModelSysItem();
		label.setLabelId("105001");
		label.setParentId("105");
		label.setTitle("小保养");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("105002");
		label.setParentId("105");
		label.setTitle("大保养");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("105003");
		label.setParentId("105");
		label.setTitle("发动机清洗");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("105004");
		label.setParentId("105");
		label.setTitle("火花塞");
		list.add(label);
		}
		return list;
	}
	
	static public List<ModelSysItem> querySysItemById(String itemClassId) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
//		if (itemClassId.equals("101")) {
		label = new ModelSysItem();
		label.setLabelId("101001");
		label.setParentId("101");
//		label.setSelect(1);
		label.setTitle("洗车");
		list.add(label);
		return list;
		}
		
	/**
	 * 获得评价标签
	 * @param level 商家级别
	 * @return
	 */
	static public List<ModelSysItem> queryAppraiseLabel(int level) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		label = new ModelSysItem();
		label.setLabelId("101");
		label.setTitle("服务态度好");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("102");
		label.setTitle("操作工序细致认真");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("103");
		label.setTitle("老板人好，价格好商量");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("104");
		label.setTitle("价格低，服务好");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("105");
		label.setTitle("地方好找");
		list.add(label);
		
		return list;
	}
	
	/**
	 * 获得车主对某个商家的评价，并将人数拼接起来，没有人评价的不包含
	 * @param providerId 商家ID
	 * @return
	 */
	static public List<ModelSysItem> queryProviderAppraiseLabel(String providerId) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		label = new ModelSysItem();
		label.setLabelId("101");
		label.setTitle("服务态度好（12人）");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("102");
		label.setTitle("操作工序细致认真（10人）");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("103");
		label.setTitle("老板人好，价格好商量（6人）");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("104");
		label.setTitle("价格低，服务好（3人）");
		list.add(label);
		
		label = new ModelSysItem();
		label.setLabelId("105");
		label.setTitle("地方好找（1人）");
		list.add(label);
		
		return list;
	}

	/**
	 * 根据商家id查询商家录入的服务种类（即系统的服务标签，每个服务种类下可以有多个服务项目）
	 * @param providerId
	 * @return
	 */
	static public List<ModelSysItem> queryProviderSysItem(String providerId) {
		ModelSysItem label;
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();
		
		label = new ModelSysItem();
		label.setLabelId("101001");
		label.setParentId("101");
//		label.setSelect(1);
		label.setTitle("洗车");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101002");
		label.setParentId("101");
		label.setTitle("精致洗车");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101003");
		label.setParentId("101");
		label.setTitle("全自动电脑洗车");
		list.add(label);
		label = new ModelSysItem();
		label.setLabelId("101004");
		label.setParentId("101");
		label.setTitle("车内清洁");
		list.add(label);
		
		return list;
	}
	
	/**
	 * 根据商家id和用户选择，查询商家的服务项目
	 * @param providerId
	 * @param appItem
	 * @return
	 */
	static public List<ModelSysItem> queryProviderItemBySysItemId(String providerId, String sysItemId) {
		List<ModelSysItem> list = new ArrayList<ModelSysItem>();		
		
		List<String> labellist = new ArrayList<String>();
//		ModelSysItem item = new ModelSysItem();
//		item.setLabelId(sysItemId);
		labellist.add(sysItemId);
		List<ModelProviderItem> itemlist = queryProviderItemByProviderId(providerId, labellist);
		
		for (int i=0; i<itemlist.size(); i++) {
			ModelSysItem lbl = new ModelSysItem();
			lbl.setLabelId(itemlist.get(i).getItemId());
			lbl.setParentId(sysItemId);
	//		lbl.setSelect(1);
			lbl.setTitle(itemlist.get(i).getTitle());
			list.add(lbl);
			
		}
		return list;
	}
	
	/**
	 * 查询商家
	 * @param appItemList 用户设置的过滤条件，如果为空，则认为没有设置任何条件
	 * @param sort 排序：0不排序；1按距离；2按口碑；3按价格
	 * @param page
	 * @return
	 */
	static public List<ModelProvider> queryProviderList(int level, List<String> appItemIdList, int sort) {
		List<ModelProvider> list = new ArrayList<ModelProvider>();
//		if (labellist!=null) {
//			for (int i=0; i<labellist.size(); i++) {
//			Log.v("PublicData", "queryProviderList(), Select Label("+i+") = "+labellist.get(i).getTitle());
//			}
//		}
		for (int i=0; i<100; i++) {
			ModelProvider item = new ModelProvider();
//			item.setItemId(""+i);
			item.setTitle("汽车服务商家-"+i);
			item.setSummary("性质：行业认证值得信赖企业。\n规模：公司成立于03年，现有员工38人。\n服务宗旨：为广大车主提供最优质、最便捷、最安全的汽车保养维修服务。");
			item.setAddr("呼和浩特市回民区永明路32号，嘉禾大厦对面，XX大酒店旁边。");
			item.setRemark("说明信息。");
			item.setPhone("15247126745");
			item.setBrowse(i*11);
			item.setBusiness(i*7);
			if (i%4==0) 
				item.setLiansuo(1);
			if (i%4==1) 
				item.setS4(1);
			if (i%4==2) 
				item.setRenzheng(1);
//			item.setPrice(i*110);
//			item.setDiscount(9);
			// 40.853174; y=111.717143
			double rx = Math.random()-0.6;
			double ry = Math.random()-0.6;
			double x = rx*0.08;
			double y = ry*0.1;
			item.setLatitude(40.853174 + x);
			item.setLongitude(111.717143 + y);
			
			item.setScore((float)4.5);
			item.setScoreCount(38);
			list.add(item);
		}
		return list;
	}
	
	/**
	 * 根据用户输入进行查询
	 * @param search 查询条件，只有数字则认为是商家号，有汉字则为商家名称
	 * @param page
	 * @return
	 */
	static public List<ModelProvider> queryProviderListBySearch(String searchStr) {
		List<ModelProvider> list = new ArrayList<ModelProvider>();
//		return list;
		for (int i=1; i<14; i++) {
			ModelProvider item = new ModelProvider();
			item.setProviderId((i+3000000)+"");
			item.setTitle("搜索汽车服务商家-"+i);
//			item.setSummary("性质：行业认证值得信赖企业。\n地址：呼和浩特海东路32号华晨酒店东巷口\n服务宗旨：为广大车主提供最优质、最便捷、最安全的汽车保养维修服务。");
			item.setAddr("呼和浩特市回民区永明路32号，嘉禾大厦对面，XX大酒店旁边。");
			item.setPhone("15247126745");
			item.setBrowse(i*11);
			item.setBusiness(i*7);
			item.setScore((float)4.6);
			item.setScoreCount(38);

			// 40.853174; y=111.717143
			double rx = Math.random()-0.6;
			double ry = Math.random()-0.6;
			double x = rx*0.08;
			double y = ry*0.1;
			item.setLatitude(40.853174 + x);
			item.setLongitude(111.717143 + y);
			
			list.add(item);
		}
		return list;
	}
	
	/**
	 * 根据用户ID查询该用户所对应的商家数据，只有7位的商家号才有商家数据，一个用户只对应一个商家，车主用户没有对应的商家
	 * @param userId 用户ID
	 * @return
	 */
	static public ModelProvider queryProviderByUserId(String userId) {
		ModelProvider info = new ModelProvider();
		
		info.setProviderId("10201");
		info.setTitle("华晨进口汽车维修服务部");
		info.setScore((float)4.6);
		info.setScoreCount(125);
		info.setBrowse(436);
		info.setBusiness(231);
		info.setSummary("性质：行业认证值得信赖企业。\n地址：呼和浩特海东路32号华晨酒店东巷口\n服务宗旨：为广大车主提供最优质、最便捷、最安全的汽车保养维修服务。");
		info.setAddr("呼和浩特市回民区永明路32号，嘉禾大厦对面，XX大酒店旁边。");
		info.setPhone("15247126745");
		return info;
	}
	
	/**
	 * 根据商家ID查询商家数据
	 * @param providerId
	 * @return
	 */
	static public ModelProvider queryProviderById(String providerId) {
		ModelProvider info = new ModelProvider();
		
		info.setProviderId(providerId);
		info.setTitle("华晨进口汽车维修服务部");
		info.setScore((float)4.6);
		info.setScoreCount(125);
		info.setBrowse(436);
		info.setBusiness(231);
		info.setSummary("性质：行业认证值得信赖企业。\n地址：呼和浩特海东路32号华晨酒店东巷口\n服务宗旨：为广大车主提供最优质、最便捷、最安全的汽车保养维修服务。");
		info.setAddr("呼和浩特市回民区永明路32号，嘉禾大厦对面，XX大酒店旁边。");
		info.setPhone("15247126745");
		return info;
	}
	
	/**
	 * 根据项目id查询项目数据
	 * @param itemId
	 * @return
	 */
	static public ModelProviderItem queryProviderItemById(String itemId) {
		ModelProviderItem item = new ModelProviderItem();
		item.setItemId("10000");
		item.setTitle("某个项目-轮胎四轮定位");
		item.setSummary("汽车的转向车轮、转向节和前轴三者之间的安装具有一定的相对位置，这种具有一定相对位置的安装叫做转向车轮定位，也称前轮定位。");
//		item.setBrowse(11);
		item.setBusiness(7);
		item.setPrice(110);
		item.setPriceOld(110+100);
		
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		String d1=fnum.format(item.getPrice()); 
		String d2 = fnum.format(item.getPriceOld()); 
//		item.setStrPrice(d1 + "元（原价" + d2+"元）");
		
//		item.setScore((float)4.6);
//		item.setScoreCount(38);
		
		return item;
	}
	
	/**
	 * 根据用户选择的过滤条件在某个商家中查询项目
	 * @param providerId
	 * @param labellist
	 * @return
	 */
	static public List<ModelProviderItem> queryProviderItemByProviderId(String providerId, List<String> sysItemIdList) {
		List<ModelProviderItem> list = new ArrayList<ModelProviderItem>();

		int n = 7;
		if (sysItemIdList!=null) {
//			if (labellist)
			n = 3;
		}
		for (int i=1; i<n; i++) {
			ModelProviderItem item = new ModelProviderItem();
			item.setItemId(""+i);
			item.setTitle("四轮定位-"+i);
			item.setSummary("汽车的转向车轮、转向节和前轴三者之间的安装具有一定的相对位置，这种具有一定相对位置的安装叫做转向车轮定位，也称前轮定位。");
//			item.setBrowse(i*11);
			item.setBusiness(i*7);
			item.setPrice(i*110);
			item.setPriceOld(i*110+100);
			
			DecimalFormat fnum = new DecimalFormat("##0.00"); 
			String d1=fnum.format(item.getPrice()); 
			String d2 = fnum.format(item.getPriceOld()); 
//			item.setStrPrice(d1 + "元（原价" + d2+"元）");
			
//			item.setScore((float)4.6);
//			item.setScoreCount(38);
			list.add(item);
		}
		return list;
	}
	
	/**
	 * 查询项目的详细信息
	 * @param item
	 * @return
	 */
	static public ModelProviderItem queryProviderItemDetailById(String itemId) {
		ModelProviderItem item = new ModelProviderItem();
//		if (item.getItemId() != null) {
//			detail.setItemId(id);
			item.setRemark("当车辆使用很长时间后，用户发现方向转向沉重、发抖、跑偏、不正、不归位或者轮胎单边磨损，波状磨损，块状磨损，偏磨等不正常磨损，以及用户驾驶时，车感漂浮、颠簸、摇摆等现象出现时，就应该考虑检查一下车轮定位值，看看是否偏差太多，及时进行修理。\n\n建议最少要每一年去做一次轮胎定位或是一万公里做一次定位。如果用车时间较长，像是出租车、货车之类的最好半年或三个月就做一次定位。\n\n前轮定位包括主销后倾角、主销内倾角、前轮外倾角和前轮前束四个内容。后轮定位包括车轮外倾角和逐个后轮前束。这样前轮定位和后轮定位总起来说叫车轮定位，也就是常说的四轮定位。车轮定位的作用是使汽车保持稳定的直线行驶和转向轻便，并减少汽车在行驶中轮胎和转向机件的磨损。");
//		} else {
//			item.setInfo1("《这里可以描述项目的详细信息，如使用的原材料、操作步骤等。这样的信息可以让车主放心，增加信任感。》");
			
//		}
		return item;
//		return detail;
	}
	
	/**
	 * 根据用户id查询该用户的业务记录
	 * @param userId
	 * @param page
	 * @return
	 */
//	static public List<ModelBuynote> queryBuynoteByUserId(String userId, int page) {
//		List<ModelBuynote> list = new ArrayList<ModelBuynote>();
//		if (userId.equals("999999999")) {
////			return list;
//		}
//		int n = 10;
//		if (page==3) {
//			n = 0;
//		}
//		for (int i=0; i<n; i++) {
//			ModelBuynote note = new ModelBuynote();
//			note.setItemId("102012"+(i+page*10));
//			note.setItemName("项目名称-"+i);
//			note.setProviderId("10101"+i);
//			note.setProviderName("商家名称"+i);
//			if (i%3==0)
//				note.setScore(4);
//			note.setPrice(212.00);
//			note.setDate("2014-05-12 16:30");
//			note.setUserId("1023220"+i);
//			note.setUserName("车主昵称"+i);
//			list.add(note);
//		}
//		return list;
//	}
	/**
	 * 
	 * @param type
	 * @return
	 */
	static public List<ModelPublicItem> queryStringList(int type) {
		List<ModelPublicItem> list = new ArrayList<ModelPublicItem>();
		ModelPublicItem single;
		if (type == 1) {
			// 投诉类
//			single = new ModelPublicItem("101", "商家涉嫌价格欺诈，收费不按标注的来");
			single = new ModelPublicItem();
			single.setId("101");
			single.setTitle("商家涉嫌价格欺诈，收费不按标注的来");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("102");
			single.setTitle("做不了的服务项目也在这上面写着");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("103");
			single.setTitle("商家不存在，或是地图标记错误");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("104");
			single.setTitle("服务态度恶劣");
			list.add(single);
//			list.add(new ModelSingleTitle("101", "商家涉嫌价格欺诈，收费不按标注的来"));
//			list.add(new ModelSingleTitle("102", "做不了的服务项目也在这上面写着"));
//			list.add(new ModelSingleTitle("103", "商家不存在，或是地图标记错误"));
//			list.add(new ModelSingleTitle("104", "服务态度恶劣"));
		} else if (type == 2) {
			// 意见反馈
			single = new ModelPublicItem();
			single.setId("201");
			single.setTitle("看不到我想看的信息");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("202");
			single.setTitle("再大的折扣我也不想去");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("203");
			single.setTitle("程序太慢，总让我等");
			list.add(single);
			
			single = new ModelPublicItem();
			single.setId("204");
			single.setTitle("这界面也太丑了");
			list.add(single);
//			list.add(new ModelSingleTitle("201", "看不到我想看的信息"));
//			list.add(new ModelSingleTitle("202", "再大的折扣我也不想去"));
//			list.add(new ModelSingleTitle("203", "程序太慢，总让我等"));
//			list.add(new ModelSingleTitle("204", "这界面也太丑了"));
		} else if (type == 3) {
			single = new ModelPublicItem();
			single.setId("301");
			single.setTitle("我如何把我的服务店信息录进去，能让车主看到？");
			list.add(single);
			single = new ModelPublicItem();
			single.setId("302");
			single.setTitle("“认购”是什么意思？");
			list.add(single);
			single = new ModelPublicItem();
			single.setId("303");
			single.setTitle("我是车主，我“认购”了，如何保证服务质量？");
			list.add(single);
//			list.add(new ModelSingleTitle("301", "我如何把我的服务店信息录进去，能让车主看到？"));
//			list.add(new ModelSingleTitle("302", "“认购”是什么意思？"));
//			list.add(new ModelSingleTitle("303", "我是车主，我“认购”了，如何保证服务质量？"));
		}
		return list;
	}
	
}
