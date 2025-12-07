package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * 部署 テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MDeptEntity extends MDeptEntityBase {
	/**
	 * コンストラクタ
	 */
	public MDeptEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public MDeptEntity(MDeptEntityBase value) {
		super.setDeptCode(value.getDeptCode());
		super.setDeptName(value.getDeptName());
		super.setIdate(value.getIdate());
		super.setUdate(value.getUdate());
	}

}
