package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * お知らせ テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class InfoEntity extends InfoEntityBase {
	/**
	 * コンストラクタ
	 */
	public InfoEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public InfoEntity(InfoEntityBase value) {
		super.setDate(value.getDate());
		super.setSeq(value.getSeq());
		super.setBody(value.getBody());
		super.setLimitDate(value.getLimitDate());
		super.setIdate(value.getIdate());
		super.setUdate(value.getUdate());
	}

}
