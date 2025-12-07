package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * カルテ分類 テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MTypeEntity extends MTypeEntityBase {
	/**
	 * コンストラクタ
	 */
	public MTypeEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public MTypeEntity(MTypeEntityBase value) {
		super.setTypeCode(value.getTypeCode());
		super.setTypeName(value.getTypeName());
		super.setIdate(value.getIdate());
		super.setUdate(value.getUdate());
	}

}
