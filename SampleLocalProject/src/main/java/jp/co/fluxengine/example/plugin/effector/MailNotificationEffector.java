package jp.co.fluxengine.example.plugin.effector;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Effector;
import jp.co.fluxengine.stateengine.annotation.Post;

@Effector("effector/ユーザー通知#メール送信,rule/性能検証ルール#ダミー送信")
public class MailNotificationEffector implements Serializable {

	/**
     *
     */
    private static final long serialVersionUID = 1917569925801632400L;

    private static final Logger log = LogManager.getLogger(MailNotificationEffector.class);

	@DslName("ユーザーID")
	private String userId;

	@DslName("日時")
	private LocalDateTime now;

	@DslName("メッセージ")
	private String message;

	@Post
	public void post() {
		StringBuilder sb = new StringBuilder();
		sb.append("■■アラート:");
		sb.append(now);
		sb.append(" ");
		sb.append(message);
		log.debug(sb.toString());
	}
}