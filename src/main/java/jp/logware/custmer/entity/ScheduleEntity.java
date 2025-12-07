package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * 予定 テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ScheduleEntity extends ScheduleEntityBase {
	/**
	 * コンストラクタ
	 */
	public ScheduleEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public ScheduleEntity(ScheduleEntityBase value) {
		super.setUserId(value.getUserId());
		super.setDate(value.getDate());
		super.setSeq(value.getSeq());
		super.setStartTime(value.getStartTime());
		super.setEndTime(value.getEndTime());
		super.setTitle(value.getTitle());
		super.setBody(value.getBody());
		super.setIdate(value.getIdate());
		super.setUdate(value.getUdate());
	}

}
