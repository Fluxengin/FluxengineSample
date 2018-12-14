package jp.co.fluxengine.plugin.effector;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Effector;
import jp.co.fluxengine.stateengine.annotation.Post;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Effector("effector/検証エフェクタ#エフェクタ")
@Effector("effector/ユーザー通知#メール送信")
public class MailNotificationEffector {

	private static final Logger log = LogManager.getLogger(MailNotificationEffector.class);

	@DslName("ユーザーID")
	private String userId;

	@DslName("日時")
	private LocalDateTime now;

	@DslName("メッセージ")
	private String message;

    @Post
    public void post() {
    	//TODO メール送信処理にデータを渡したり



    	//debug
    	StringBuilder sb = new StringBuilder();
    	sb.append("■■メール送信\n");

    	for (Field field : this.getClass().getDeclaredFields()) {
    		try {
				field.setAccessible(true);
				sb.append(field.getName() + " = " + field.get(this) + "\n");
    		} catch (IllegalAccessException e) {
    			sb.append(field.getName() + " = " + "access denied\n");
    		}
		}
    	log.debug(sb.toString());
    }
}