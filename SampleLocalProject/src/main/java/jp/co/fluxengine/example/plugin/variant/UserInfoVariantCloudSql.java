package jp.co.fluxengine.example.plugin.variant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jp.co.fluxengine.example.CloudSqlPool;
import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Variant;

@Variant("rule/性能検証ルール#クラウドSQL情報バリアント")
public class UserInfoVariantCloudSql {

	private static final Logger log = LogManager.getLogger(UserInfoVariantCloudSql.class);

	@DslName("get")
	public Map<String, Object> get(String id) {


		log.debug("UserInfoVariantCloudSql:" + id);
		long n =5368709120l;
		 try (Connection conn = CloudSqlPool.getDataSource().getConnection()) {
		     PreparedStatement voteStmt =  conn.prepareStatement(
		             "SELECT name , content  FROM entries where name = ?");
		         voteStmt.setString(1, id);

		         // Execute the statement
		         ResultSet voteResults = voteStmt.executeQuery();
		         // Convert a ResultSet into Vote objects
		         while (voteResults.next()) {
		             log.debug("UserInfoVariantCloudSql data あります。" + voteResults.getString("content"));

		             if ("mdc".equals(voteResults.getString("content"))) {
		                 n= 1111;
		             }
		           break;
		         }
		 } catch (SQLException e) {
            throw new RuntimeException(e);

        }
		//ダミ－データ
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("ユーザーID", id);
		m.put("パケット上限", n);
		return m;
	}



}


