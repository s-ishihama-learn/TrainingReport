package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * 休日 テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class MHolidayEntity extends MHolidayEntityBase {
	/**
	 * コンストラクタ
	 */
	public MHolidayEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public MHolidayEntity(MHolidayEntityBase value) {
		super.setDate(value.getDate());
		super.setNote(value.getNote());
		super.setKind(value.getKind());
		super.setIdate(value.getIdate());
		super.setUdate(value.getUdate());
	}
}
