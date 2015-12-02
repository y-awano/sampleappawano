package pack;

import static pack.Const.*;
import java.sql.*;
import java.util.Properties;

/**
 * DBコネクションクラスです。
 * @author USER0223 AWANO
 * @return conn Connection
 */
public class DBConnection {
    public static Connection getConnection() throws Exception {
        //JDBCドライバのロード
        Class.forName(POSTGRE_DRIVER);

        //各設定
        String url = POSTGRE_URL;
        Properties props = new Properties();
        props.setProperty("user", POSTGRE_USER);
        props.setProperty("password", POSTGRE_PASSWORD);

        //データベースに接続
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }
}
