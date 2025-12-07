package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * 利用者 テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MUserEntity extends MUserEntityBase {
	/**
	 * コンストラクタ
	 */
	public MUserEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public MUserEntity(MUserEntityBase value) {
		super.setUserId(value.getUserId());
		super.setUserPass(value.getUserPass());
		super.setUserName(value.getUserName());
		super.setDeptCode(value.getDeptCode());
		super.setUserCom(value.getUserCom());
		super.setMailAddr(value.getMailAddr());
		super.setPassLimit(value.getPassLimit());
		super.setDelFlag(value.getDelFlag());
		super.setIdate(value.getIdate());
		super.setUdate(value.getUdate());
	}

}
