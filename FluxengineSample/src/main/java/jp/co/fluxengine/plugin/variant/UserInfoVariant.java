package jp.co.fluxengine.plugin.variant;

import java.util.HashMap;
import java.util.Map;

import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Variant;

@Variant("variant/ユーザー情報#ユーザー情報")
public class UserInfoVariant {

    @DslName("get")
    public Map<String, Object> get(String id) {
    	//TODO 永続化しているデータを取得したり、加工処理を記載
    	System.out.println("UserInfoVariant:" + id);

		//ダミ－データ
    	Map<String, Object> m = new HashMap<String, Object>();
    	m.put("ユーザーID", "uid12345");
    	m.put("パケット上限", Long.valueOf(5368709120l));
        return m;
    }
}
