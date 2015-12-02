package pack;

import static pack.Const.*;
import java.sql.*;
import java.util.Properties;

/**
 * DB�R�l�N�V�����N���X�ł��B
 * @author USER0223 AWANO
 * @return conn Connection
 */
public class DBConnection {
    public static Connection getConnection() throws Exception {
        //JDBC�h���C�o�̃��[�h
        Class.forName(POSTGRE_DRIVER);

        //�e�ݒ�
        String url = POSTGRE_URL;
        Properties props = new Properties();
        props.setProperty("user", POSTGRE_USER);
        props.setProperty("password", POSTGRE_PASSWORD);

        //�f�[�^�x�[�X�ɐڑ�
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }
}
