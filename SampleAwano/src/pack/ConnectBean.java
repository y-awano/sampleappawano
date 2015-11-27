package pack;

/**
 * サンプルwebアプリ用bean
 * @author AWANO
 *
 */

public class ConnectBean {
	/**
	 * ファイル内容
	 */
	private String file;

	/**
	 * ID(csvファイルを想定)
	 */
	private String id;

	/**
	 * 名前(csvファイルを想定)
	 */
	private String name;

	/**
	 * ナンバー(csvファイルを想定)
	 */
	private String number;


	/**
	 * ファイル内容の設定
	 * @param file
	 */
	public void setFile(String file) {
		this.file = file;
	}
	/**
	 * ファイル内容の取得
	 * @return
	 */

	public String getFile() {
		return this.file;
	}

	/**
	 * IDの取得
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * IDの設定
	 * @param file
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 名前の取得
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名前の設定
	 * @param file
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ナンバーの取得
	 * @return
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * ナンバーの設定
	 * @param file
	 */
	public void setNumber(String number) {
		this.number = number;
	}
}
