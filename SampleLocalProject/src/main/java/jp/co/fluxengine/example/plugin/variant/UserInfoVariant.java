package jp.co.fluxengine.example.plugin.variant;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Variant;

@Variant("variant/ユーザー情報#ユーザー情報,rule/性能検証ルール#マスタダミー情報バリアント")
public class UserInfoVariant {

	private static final Logger log = LoggerFactory.getLogger(UserInfoVariant.class);


	@DslName("get")
	public Map<String, Object> get(String id) {
		log.debug("UserInfoVariant:" + id);
		//ダミ－データ
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("ユーザーID", id);
		m.put("パケット上限", Long.valueOf(5368709120l));
		return m;
	}
}
