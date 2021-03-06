package com.tieba.test;

import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.libsgh.tieba.api.TieBaApi;
import com.github.libsgh.tieba.model.ReplyInfo;

public class BaiDuApiTest {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static String bduss = "";
	private static String stoken = "";
	private static String username = "";
	private static TieBaApi api = null;
    @BeforeClass
    public static void runOnceBeforeClass() {
        bduss = "";
        stoken = "";
        username = "除氺";
        api = TieBaApi.getInstance();
    }
    
	/**
	 * 回帖
	 */
	@Test
	public void reply() {
		logger.info(api.reply(bduss, "6263231235", "重庆力帆", "路过", 0).toString());
	}
	
	/**
	 * 获取首页帖子tid数组
	 */
	@Test
	public void getIndexTList() {
		logger.info(api.getIndexTList("重庆力帆").toString());
	}
	
	/**
	 * 执行签到
	 */
	@Test
	public void oneBtnToSign() {
		//Map<String, Object> msg = api.oneBtnToSign(bduss, stoken);
		Map<String, Object> msg = api.oneBtnToSign(bduss);
		String result = JSONObject.toJSONString(msg);
		logger.info(result);
	}
	
	/**
	 * 执行签到
	 */
	@Test
	public void signDo() {
		Map<String, Object> map = api.signOneTieBa("姜敏京", 282280, bduss);
		String result = JSONObject.toJSONString(map);
		logger.info(result);
		
	}
	
	/**
	 * 获取用户所有的贴吧（贴吧数多会稍慢）
	 */
	@Test
	public void getHideTbs() {
		List<Map<String, Object>> list = api.getHideTbs(username);
		String result = JSONObject.toJSONString(list);
		logger.info(result);
		
	}
	
	@Test
	public void tbs() {
		logger.info(api.getTbs(bduss));
	}
	/**
	 * 获取我喜欢的贴吧
	 */
	@Test
	public void getMyLikedTB() {
		logger.info(JSONObject.toJSONString(api.getMyLikedTB(bduss, stoken)));
	}
	
	/**
	 * 获取用户信息
	 */
	@Test
	public void getUserInfo() {
		logger.info(JSONObject.toJSONString(api.getUserInfo(bduss, stoken)));
	}
	
	/**
	 * 获取用户头像
	 */
	@Test
	public void getHeadImg() {
		logger.info(api.getHeadImg(username));
	}
	
	/**
	 * 知道签到
	 */
	@Test
	public void zhiDaoSign() {
		logger.info(api.zhiDaoSign(bduss));
	}
	
	/**
	 * 文库签到
	 */
	@Test
	public void wenKuSign() {
		logger.info(api.wenKuSign(bduss));
	}
	
	/**
	 * 查看回复我或艾特我的信息
	 */
	@Test
	public void getMsg() {
		logger.info(JSONObject.toJSONString(api.getMsg(bduss, "reply", 1)));
		//logger.info(JSONObject.toJSONString(api.getMsg(bduss, "at", 1)));
	}
	
	/**
	 * 楼中楼回复 结合getMsg、floorpid可以实现机器人自动回复艾特
	 */
	@Test
	public void replyFloor() {
		logger.info(api.replyFloor(bduss, "tid", "fname", "···", 0 , "pid").toString());
	}
	
	/**
	 * 获取楼层pid
	 */
	@Test
	public void floorpid() {
		List<ReplyInfo> list = api.getMsg(bduss, "reply", 1);
		for (ReplyInfo replyInfo : list) {
			logger.info(api.floorpid(bduss, replyInfo.getThread_id(), replyInfo.getPost_id()));
		}
	}
	
	/**
	 * 获取用户信息
	 */
	@Test
	public void getUserProfile() {
		String result = api.getUserProfile("数学書");
		logger.info(result);
	}
	
	/**
	 * 登录测试
	 */
	@Test
	public void loginTest() {
		String account = "xxx@qq.com";
		String password = "xxx.xxx";
		String verifyCode = "";//验证码
		String codeString = "";//验证码code
		String cookies = "";//登录的cookie
		String token = "";//token
		Map<String, Object> map= api.getBaiDuLoginCookie(account, password, verifyCode, codeString, cookies, token);
		String result = JSONObject.toJSONString(map);
		logger.info(result);
	}
	
	/**
	 * 关注一个贴吧
	 */
	@Test
	public void focus() {
		api.focus(bduss, "bug");
	}
	
	/**
	 * 关注一个贴吧
	 */
	@Test
	public void focus2() {
		logger.info(""+api.focus2(bduss, stoken, api.getFid("bug")));
	}
	
	/**
	 * 取消关注一个贴吧
	 */
	@Test
	public void unfocus() {
		api.unfocus(bduss, "bug");
	}
	
	/**
	 * 取消关注一个贴吧
	 */
	@Test
	public void unfocus2() {
		logger.info(""+api.unfocus2(bduss, stoken, api.getFid("bug")));
	}
	
	/**
	 * 获取登录二维码
	 */
	@Test
	public void getQRCodeUrl() {
		logger.info(JSON.toJSONString(api.getQRCodeUrl()));
	}
	
	/**
	 * 获取关注的用户列表
	 */
	@Test
	public void getFollowPage() {
		logger.info(api.getFollowPage(bduss, 4).toString());
	}
	
	/**
	 * 获取关注的用户列表
	 */
	@Test
	public void getFollowList() {
		logger.info(api.getFollowList(bduss).toString());
	}
	
	/**
	 * 获取贴吧粉丝列表
	 */
	@Test
	public void getFansPage() {
		logger.info(api.getFansList(bduss).toString());
	}
	
	/**
	 * 移除粉丝
	 */
	@Test
	public void removeFans() {
		logger.info(api.removeFans(bduss,"fans_uid").toString());
	}
	
	/**
	 * 检查登录有效性
	 */
	@Test
	public void isLogin() {
		logger.info(""+api.islogin(bduss, stoken));
		logger.info(""+api.islogin(bduss));
	}
	
	/**
	 * 二维码登录获取cookie(bduss,stoken)
	 */
	@Test
	public void getCookieFromQRCode() {
		logger.info(JSON.toJSONString(api.getCookieFromQRCode("v")));
	}
	
	/**
	 * 举报
	 */
	@Test
	public void jubao() {
		logger.info(api.jubao(bduss, "123649521597","10004",""));
	}
	
	/**
	 * 查询是否关注某个贴吧
	 */
	@Test
	public void isFocus() {
		logger.info(""+api.isFocus("bug", bduss, stoken));
		logger.info(""+api.isFocus("bug", bduss));
	}
	
	/**
	 * 根据百度盘分享链接获取完整用户名
	 */
	@Test
	public void getFullNameByPanUrl() {
		logger.info(api.getFullNameByPanUrl("https://pan.baidu.com/wap/init?surl=NGAyTDnx2JNAw9Lj5-oD5w"));
	}
	
	/**
	 * 名人堂助攻
	 */
	@Test
	public void support() {
		logger.info(api.support(bduss, "柯南"));
	}
	
	/**
	 * 获取封禁原因
	 */
	@Test
	public void getPrisionReasionList() {
		logger.info(api.prisionReasonList(bduss, "home", "uid").toString());
	}
	
	/**
	 * 封禁
	 */
	@Test
	public void commitprison() {
		logger.info(api.commitprison(bduss, "", "快乐女声", 1 ,"reason"));
	}

}
