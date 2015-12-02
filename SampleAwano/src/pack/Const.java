package pack;

/**
 * �萔�N���X�ł�
 * @author USER0223 AWANO
 */
public class Const {
    //DB�R�l�N�V�����萔
    public static final String POSTGRE_DRIVER = "org.postgresql.Driver";
    public static final String POSTGRE_URL = "jdbc:postgresql://localhost/sample";
    public static final String POSTGRE_USER = "postgres";
    public static final String POSTGRE_PASSWORD = "root";

    //SQL��
    //�t�@�C�����e�Q��
    public static final String SELECT_FILE_CONTENT_SQL = "select file_contents from showfile where file_name = ?";
    //�t�@�C�����e�o�^
    public static final String INSERT_FILE_SQL = "insert into showfile(file_name, file_contents) values(?, ?)";
    //�_���폜
    public static final String ROGICAL_DELETE_SQL = "update showfile set remove_flag = false where file_name = ?";
    //�v���_�E���ɕ\������l���Q��
    public static final String SELECT_FILE_NAME_SQL = "select id, file_name from showfile where remove_flag = 'true' order by id asc";

    //�\����
    public static final String INSERT_SUCCESS_MESSAGE = "�ۑ��ɐ������܂���";
    public static final String DELETE_SUCCESS_MESSAGE = "�@���폜���܂���";
    public static final String CLASS_NOT_FOUND_ERROR_MESSAGE = "DB�ɐڑ��ł��܂���";
    public static final String SQL_ERROR_MESSAGE = "SQL�G���[�ł�";
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "�t�@�C����������܂���";
    public static final String IO_ERROR_MESSAGE = "�t�@�C�����o�̓G���[�ł�";
    public static final String ARRAY_INDEX_OUT_OF_BOUNDS_ERROR_MESSAGE = "���ڐ�������Ă��܂�";
}
