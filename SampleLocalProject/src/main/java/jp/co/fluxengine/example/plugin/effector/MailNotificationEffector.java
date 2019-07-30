package jp.co.fluxengine.example.plugin.effector;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Effector;
import jp.co.fluxengine.stateengine.annotation.Post;

@Effector("effector/ユーザー通知#メール送信,rule/性能検証ルール#ダミー送信")
public class MailNotificationEffector {

    private static final Logger log = LoggerFactory.getLogger(MailNotificationEffector.class);

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