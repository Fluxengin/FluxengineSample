package jp.co.fluxengine.example.plugin.variant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Variant;

@Variant("rule/性能検証ルール#クラウドSQL情報バリアント")
public class UserInfoVariantCloudSql {

	private static final Logger log = LogManager.getLogger(UserInfoVariantCloudSql.class);

    private static final String CLOUD_SQL_CONNECTION_NAME = System.getenv(
            "CLOUD_SQL_CONNECTION_NAME");
    private static final String DB_USER ;
    private static final String DB_PASS;
    private static final String DB_NAME;

    private static final int MAXIMUM_POOLSIZE;

    private static final int MINIMUM_IDLE;

    private static final int IDLE_TIMEOUT;

    private static final int MAX_LIFETIME;

    public static DataSource pool;

    static {
        Properties props = new Properties();
        if (StringUtils.isNotEmpty(System.getenv().get("CONF"))) {
            load(System.getenv().get("CONF") + File.separator + "application-jdbc.properties",props);
        } else {
            InputStream in = UserInfoVariantCloudSql.class.getResourceAsStream("/" + "application-jdbc.properties");
            if (in != null) {
                load(in,props);
            }
        }

        DB_USER = props.getProperty("DB_USER");
        DB_PASS = props.getProperty("DB_PASS");
        DB_NAME = props.getProperty("DB_NAME");
        MAXIMUM_POOLSIZE = Integer.valueOf(props.getProperty("MAXIMUM_POOLSIZE"));
        MINIMUM_IDLE= Integer.valueOf(props.getProperty("MINIMUM_IDLE"));
        IDLE_TIMEOUT= Integer.valueOf(props.getProperty("IDLE_TIMEOUT"));
        MAX_LIFETIME= Integer.valueOf(props.getProperty("MAX_LIFETIME"));
        props = null;

        pool = createConnectionPool();
    }

	private static DataSource createConnectionPool() {


	    // [START cloud_sql_mysql_servlet_create]
	    // The configuration object specifies behaviors for the connection pool.
	    HikariConfig config = new HikariConfig();

	    // Configure which instance and what database user to connect with.
	    config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
	    config.setUsername(DB_USER); // e.g. "root", "postgres"
	    config.setPassword(DB_PASS); // e.g. "my-password"

	    // For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated connections.
	    // See https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory for details.
	    config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
	    config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);
	    config.addDataSourceProperty("useSSL", "false");

	    // ... Specify additional connection properties here.
	    // [START_EXCLUDE]

	    // [START cloud_sql_mysql_servlet_limit]
	    // maximumPoolSize limits the total number of concurrent connections this pool will keep. Ideal
	    // values for this setting are highly variable on app design, infrastructure, and database.
	    config.setMaximumPoolSize(MAXIMUM_POOLSIZE);
	    // minimumIdle is the minimum number of idle connections Hikari maintains in the pool.
	    // Additional connections will be established to meet this value unless the pool is full.
	    config.setMinimumIdle(MINIMUM_IDLE);
	    // [END cloud_sql_mysql_servlet_limit]

	    // [START cloud_sql_mysql_servlet_timeout]
	    // setConnectionTimeout is the maximum number of milliseconds to wait for a connection checkout.
	    // Any attempt to retrieve a connection from this pool that exceeds the set limit will throw an
	    // SQLException.
	    config.setConnectionTimeout(IDLE_TIMEOUT); // 10 seconds
	    // idleTimeout is the maximum amount of time a connection can sit in the pool. Connections that
	    // sit idle for this many milliseconds are retried if minimumIdle is exceeded.
	    config.setIdleTimeout(MAX_LIFETIME); // 10 minutes
	    // [END cloud_sql_mysql_servlet_timeout]

	    // [START cloud_sql_mysql_servlet_backoff]
	    // Hikari automatically delays between failed connection attempts, eventually reaching a
	    // maximum delay of `connectionTimeout / 2` between attempts.
	    // [END cloud_sql_mysql_servlet_backoff]

	    // [START cloud_sql_mysql_servlet_lifetime]
	    // maxLifetime is the maximum possible lifetime of a connection in the pool. Connections that
	    // live longer than this many milliseconds will be closed and reestablished between uses. This
	    // value should be several minutes shorter than the database's timeout value to avoid unexpected
	    // terminations.
	    config.setMaxLifetime(1800000); // 30 minutes
	    // [END cloud_sql_mysql_servlet_lifetime]

	    // [END_EXCLUDE]

	    // Initialize the connection pool using the configuration object.
	    DataSource pool = new HikariDataSource(config);
	    // [END cloud_sql_mysql_servlet_create]
	    return pool;
	  }

	@DslName("get")
	public Map<String, Object> get(String id) {

		log.debug("UserInfoVariantCloudSql:" + id);
		long n =5368709120l;
		 try (Connection conn = pool.getConnection()) {
		     PreparedStatement voteStmt =  conn.prepareStatement(
		             "SELECT name        , content  FROM variant where name ='" + id);
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


    public static void load(String fileName, Properties props) {
        FileInputStream in;
        try {
            in = new FileInputStream(fileName);
            props.load(new InputStreamReader(in, "utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load(InputStream in,Properties props) {
        try {
            props.load(new InputStreamReader(in, "utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
CREATE TABLE entries (name VARCHAR(255), content VARCHAR(255),
   PRIMARY KEY(name));
    INSERT INTO entries (name, content) values ("C0000000001", "mdc");

*/
