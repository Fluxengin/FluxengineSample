package jp.co.fluxengine.example.plugin.effector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.fluxengine.example.CloudSqlPool;
import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Effector;
import jp.co.fluxengine.stateengine.annotation.Post;
import jp.co.fluxengine.stateengine.starter.simple.SimpleStarter;

@Effector("rule/性能検証ルール#DB書き込み送信")
public class MailNotificationCloudSqlEffector {

    private static final Logger log =  LoggerFactory.getLogger(SimpleStarter.class);

    @DslName("ユーザーID")
    private String userId;

    @DslName("日時")
    private LocalDateTime now;

    @DslName("メッセージ")
    private String message;

    @Post
    public void post() {

        if (log.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("■■アラート:");
            sb.append(now);
            sb.append(" ");
            sb.append(message);
            log.debug(sb.toString());
        }
        try (Connection conn = CloudSqlPool.getDataSource().getConnection()) {

            // PreparedStatements can be more efficient and project against injections.
            PreparedStatement voteStmt = conn.prepareStatement(
                "INSERT INTO effector2 (userid, message, createTime, m1 , m2, m3, m4, m5, m6, m7,m8) VALUES (?, ? ,? ,'a1', 'a2', 'a3', 'a4', 'a5', 'a6', 'a7','a8');");

            voteStmt.setString(1, userId);
            voteStmt.setString(2, message);
            voteStmt.setTimestamp(3, Timestamp.valueOf(now));


            // Finally, execute the statement. If it fails, an error will be thrown.
            voteStmt.execute();

          } catch (SQLException ex) {
              throw new RuntimeException(ex);
          }
    }

}
/**
*
CREATE TABLE effector (userid VARCHAR(255), message VARCHAR(255),createTime timestamp ,
messageId INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(messageId));

CREATE TABLE effector2(userid VARCHAR(255), message VARCHAR(255),createTime timestamp ,
messageId INT NOT NULL AUTO_INCREMENT, m1 VARCHAR(255), m2 VARCHAR(255), m3 VARCHAR(255),
m4 VARCHAR(255), m5 VARCHAR(255), m6 VARCHAR(255), m7 VARCHAR(255), m8 VARCHAR(255),PRIMARY KEY(messageId) ,
  KEY `FK_check_results_0` (`m1`),
  KEY `FK_check_results_1` (`m2`),
  KEY `FK_check_results_2` (`m3`),
  KEY `FK_check_results_3` (`m4`),
  KEY `FK_check_results_4` (`m5`),
  KEY `FK_check_results_5` (`m6`),
  KEY `FK_check_results_6` (`m7`),
  KEY `FK_check_results_7` (`m8`)
) ENGINE=InnoDB AUTO_INCREMENT=15939991 DEFAULT CHARSET=utf8mb4

*/
