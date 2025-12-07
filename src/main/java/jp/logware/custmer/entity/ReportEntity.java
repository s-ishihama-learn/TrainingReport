package jp.logware.custmer.entity;

/**
 * データ格納拡張クラス<br>
 * 日報 テーブル拡張
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class ReportEntity extends ReportEntityBase {
	/**
	 * コンストラクタ
	 */
	public ReportEntity() {
	}

	/**
	 * コンストラクタ
	 */
	public ReportEntity(ReportEntityBase value) {
		super.setUserId(value.getUserId());
		super.setDate(value.getDate());
		super.setSeq(value.getSeq());
		super.setStartTime(value.getStartTime());
		super.setEndTime(value.getEndTime());
		super.setPreTime(value.getPreTime());
		super.setTrvTime(value.getTrvTime());
		super.setAftTime(value.getAftTime());
		super.setTypeCode(value.getTypeCode());
		super.setTitle(value.getTitle());
		super.setCustName(value.getCustName());
		super.setCustDept(value.getCustDept());
		super.setCustPersons(value.getCustPersons());
		super.setFact(value.getFact());
		super.setGuess(value.getGuess());
		super.setNext(value.getNext());
		super.setAplFlag(value.getAplFlag());
		super.setAplUserId(value.getAplUserId());
		super.setAplComment(value.getAplComment());
		super.setIdate(value.getIdate());
		super.setUdate(value.getUdate());
	}

}
