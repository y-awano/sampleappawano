package pack;

/**
 * 定数クラスです
 * @author USER0223 AWANO
 */
public class Const {
    //DBコネクション定数
    public static final String POSTGRE_DRIVER = "org.postgresql.Driver";
    public static final String POSTGRE_URL = "jdbc:postgresql://localhost/sample";
    public static final String POSTGRE_USER = "postgres";
    public static final String POSTGRE_PASSWORD = "root";

    //SQL文
    //ファイル内容参照
    public static final String SELECT_FILE_CONTENT_SQL = "select file_contents from showfile where file_name = ?";
    //ファイル内容登録
    public static final String INSERT_FILE_SQL = "insert into showfile(file_name, file_contents) values(?, ?)";
    //論理削除
    public static final String ROGICAL_DELETE_SQL = "update showfile set remove_flag = false where file_name = ?";
    //プルダウンに表示する値を参照
    public static final String SELECT_FILE_NAME_SQL = "select id, file_name from showfile where remove_flag = 'true' order by id asc";

    //表示文
    public static final String INSERT_SUCCESS_MESSAGE = "保存に成功しました";
    public static final String DELETE_SUCCESS_MESSAGE = "　を削除しました";
    public static final String CLASS_NOT_FOUND_ERROR_MESSAGE = "DBに接続できません";
    public static final String SQL_ERROR_MESSAGE = "SQLエラーです";
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "ファイルが見つかりません";
    public static final String IO_ERROR_MESSAGE = "ファイル入出力エラーです";
    public static final String ARRAY_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE = "項目数が誤っています";
}
