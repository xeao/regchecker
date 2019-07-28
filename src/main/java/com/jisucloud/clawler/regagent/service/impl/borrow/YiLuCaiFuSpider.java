package com.jisucloud.clawler.regagent.service.impl.borrow;

import com.google.common.collect.Sets;
import com.jisucloud.clawler.regagent.interfaces.PapaSpider;
import com.jisucloud.clawler.regagent.interfaces.UsePapaSpider;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Map;
import java.util.Set;

@Slf4j
@UsePapaSpider
public class YiLuCaiFuSpider extends PapaSpider {

	@Override
	public String message() {
		return "一路财富致力于为中产家庭和中小企业提供资产配置、智能投顾、组合投资和金融SaaS, 产品包括公/私募基金、固定收益类理财、保险和信托等。";
	}

	@Override
	public String platform() {
		return "yilucaifu";
	}

	@Override
	public String home() {
		return "yilucaifu.com";
	}

	@Override
	public String platformName() {
		return "一路财富";
	}

	@Override
	public String[] tags() {
		return new String[] {"p2p", "借贷"};
	}

	@Override
	public boolean checkTelephone(String account) {
		try {
			String url = "https://www.yilucaifu.com/passport/existUserByColumn.html?r=0.3003344292527901";
			FormBody formBody = new FormBody
	                .Builder()
	                .add("column", account)
	                .build();
			Request request = new Request.Builder().url(url)
					.addHeader("User-Agent", CHROME_USER_AGENT)
					.addHeader("Referer", "https://www.yilucaifu.com/passport/reg.html")
					.post(formBody)
					.build();
			Response response = okHttpClient.newCall(request).execute();
			if (response.body().string().contains("failed")) {
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

	@Override
	public Set<String> getTestTelephones() {
		return Sets.newHashSet("18210538513", "13991808887");
	}

}
