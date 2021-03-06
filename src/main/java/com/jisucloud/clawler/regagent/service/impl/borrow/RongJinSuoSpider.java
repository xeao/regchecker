package com.jisucloud.clawler.regagent.service.impl.borrow;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.PapaSpiderConfig;

import java.util.Map;



@Slf4j
@PapaSpiderConfig(
		home = "rjs.com", 
		message = "融金所是一家专注互联网金融服务的P2P网贷平台,提供网上借贷信息的发布,作为网络借贷平台,致力线上资金的良好对接。2013年5月上线以来,融金所p2p网贷平台结合国内金融。", 
		platform = "rjs", 
		platformName = "融金所", 
		tags = { "P2P", "借贷" }, 
		testTelephones = { "15985268900", "18212345678" })
public class RongJinSuoSpider extends PapaSpider {

	

	public boolean checkTelephone(String account) {
		try {
			String url = "https://japidp.rjs.com/japidpweb/v1/userBase/findPassWordBySms.json";
			RequestBody formBody = FormBody.create(MediaType.parse("application/json"), "{\"timestamp\":1560773055131,\"sign\":\"767087235f10533dc6d005790095e82e\",\"platform\":\"android\",\"service_provider\":1,\"network_type\":1,\"data\":{\"userPhone\":\""+account+"\"},\"app_version\":\"v5.5.1\",\"uuid\":\"ffffffff-9215-c700-0000-000021dd011f\",\"channel\":\"wandoujia\",\"phone_model\":\"8692-A00\",\"os_version\":\"4.4.2\"}");
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", "okhttp/3.3.1")
					.addHeader("Host", "japidp.rjs.com")
					.addHeader("uid", "123456")
					.post(formBody)
					.build();
			Response response = okHttpClient.newCall(request).execute();
			String res = response.body().string();
			if (res.contains("成功") || res.contains("不能小于")) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkEmail(String account) {
		return false;
	}

	@Override
	public Map<String, String> getFields() {
		return null;
	}

}
