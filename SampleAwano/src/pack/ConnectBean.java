package pack;

/**
 * �T���v��web�A�v���pbean
 * @author AWANO
 *
 */

public class ConnectBean {
	/**
	 * �t�@�C�����e
	 */
	private String file;

	/**
	 * ID(csv�t�@�C����z��)
	 */
	private String id;

	/**
	 * ���O(csv�t�@�C����z��)
	 */
	private String name;

	/**
	 * �i���o�[(csv�t�@�C����z��)
	 */
	private String number;


	/**
	 * �t�@�C�����e�̐ݒ�
	 * @param file
	 */
	public void setFile(String file) {
		this.file = file;
	}
	/**
	 * �t�@�C�����e�̎擾
	 * @return
	 */

	public String getFile() {
		return this.file;
	}

	/**
	 * ID�̎擾
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * ID�̐ݒ�
	 * @param file
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ���O�̎擾
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���O�̐ݒ�
	 * @param file
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * �i���o�[�̎擾
	 * @return
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * �i���o�[�̐ݒ�
	 * @param file
	 */
	public void setNumber(String number) {
		this.number = number;
	}
}
