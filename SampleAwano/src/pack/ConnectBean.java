package pack;

/**
 * �t�@�C�����o��web�A�v��(�T���v��)�pbean
 * @author AWANO
 */
public class ConnectBean {
    /**�t�@�C���̃p�X*/
    private String file;

    /**ID(csv�p�T���v��)*/
    private String id;

    /**���O(csv�p�T���v��)*/
    private String name;

    /**�i���o�[(csv�p�T���v��)*/
    private String number;

    /**
     * �t�@�C�����e�̎擾
     * @return file
     */
    public String getFile() {
        return this.file;
    }

    /**
     * �t�@�C�����e�̐ݒ�
     * @param file
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * ID�̎擾
     * @return�@id
     */
    public String getId() {
        return id;
    }

    /**
     * ID�̐ݒ�
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * ���O�̎擾
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * ���O�̐ݒ�
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * �i���o�[�̎擾
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * �i���o�[�̐ݒ�
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
