package pack;

/**
 * ファイル入出力webアプリ(サンプル)用bean
 * @author AWANO
 */
public class ConnectBean {
    /**ファイルのパス*/
    private String file;

    /**ID(csv用サンプル)*/
    private String id;

    /**名前(csv用サンプル)*/
    private String name;

    /**ナンバー(csv用サンプル)*/
    private String number;

    /**
     * ファイル内容の取得
     * @return file
     */
    public String getFile() {
        return this.file;
    }

    /**
     * ファイル内容の設定
     * @param file
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * IDの取得
     * @return　id
     */
    public String getId() {
        return id;
    }

    /**
     * IDの設定
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 名前の取得
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 名前の設定
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ナンバーの取得
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * ナンバーの設定
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
