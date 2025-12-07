package jp.logware.custmer.logic.report;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.logware.custmer.common.CommonUtility;
import jp.logware.custmer.common.Param;
import jp.logware.custmer.common.Utility;
import jp.logware.custmer.dao.CommentDao;
import jp.logware.custmer.dao.MUserDao;
import jp.logware.custmer.dao.ReportDao;
import jp.logware.custmer.entity.CommentEntity;
import jp.logware.custmer.entity.ReportEntity;
import jp.logware.custmer.form.report.EditReportActionForm;
import jp.logware.custmer.form.report.ListReportActionForm;
import jp.logware.custmer.subform.CommentValue;
import jp.logware.custmer.subform.DayReportValue;

/**
 * 顧客カルテ編集 ロジッククラス
 *
 * @author Shigeru.Ishihama
 * @version 1.0
 */

public class EditReportLogic {
	/**
	 * データベースコネクション
	 */
	private Connection dbConn = null;

	/**
	 * コンストラクタ
	 *
	 * @param conn
	 */
	public EditReportLogic(Connection conn) {
		dbConn = conn;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @param String date
	 * @param String seq
	 * @return EditScheduleActionForm
	 */
	public ListReportActionForm getListForm(String userId, String date) throws Exception {
		ListReportActionForm form = new ListReportActionForm();

		MUserDao mUserDao = new MUserDao(dbConn);
		Map<String,String> userNameMap = mUserDao.getMap();

		ReportDao reportDao = new ReportDao(dbConn);
		List<ReportEntity> reportList = reportDao.getList(userId, Utility.toDate(date));
		for (ReportEntity entity : reportList) {
			DayReportValue item = new DayReportValue();
			item.setUserId(entity.getUserId());
			item.setUserName(userNameMap.get(entity.getUserId()));
			item.setViewDate(Utility.getDate(entity.getDate()));
			item.setSeq(String.valueOf(entity.getSeq()));
			item.setStartTime(CommonUtility.getViewTimeZeroString(entity.getStartTime()));
			item.setEndTime(CommonUtility.getViewTimeZeroString(entity.getEndTime()));
			item.setTitle(entity.getTitle());
			item.setCustName(entity.getCustName());
			item.setKind(Param.KIND_REPORT);
			form.getReportList().add(item);
		}

		return form;
	}

	/**
	 * ActionFormクラスへ値を代入
	 *
	 * @param String userId
	 * @param String date
	 * @param String seq
	 * @return EditChartActionForm
	 */
	public EditReportActionForm getEditForm(String userId, String date, String seq) throws Exception {
		EditReportActionForm form = new EditReportActionForm();

		ReportDao reportDao = new ReportDao(dbConn);
		CommentDao commentDao = new CommentDao(dbConn);
		MUserDao mUserDao = new MUserDao(dbConn);

		Map<String,String> userNameMap = mUserDao.getMap();

		ReportEntity entity = reportDao.getValue(userId, Utility.toDate(date), Integer.parseInt(seq));
		List<CommentEntity> commentList = commentDao.getListSort(userId, Utility.toDate(date), Integer.parseInt(seq), "SEQ_COMMENT");
		List<CommentValue> commentValueList = new ArrayList<CommentValue>();
		for (CommentEntity comment : commentList) {
			CommentValue comVal = new CommentValue();
			comVal.setCommentUser(userNameMap.get(comment.getRegUserId()));
			comVal.setComment(comment.getComment());
			commentValueList.add(comVal);
		}
		if (entity != null) {
			form.setUserId(entity.getUserId());
			form.setUserName(userNameMap.get(entity.getUserId()));
			form.setViewDate(getDateString(entity.getDate()));
			form.setSeq(String.valueOf(entity.getSeq()));
			form.setStartTime(entity.getStartTime());
			form.setEndTime(entity.getEndTime());
			form.setStartTimeView(CommonUtility.getViewTimeString(entity.getStartTime()));
			form.setEndTimeView(CommonUtility.getViewTimeString(entity.getEndTime()));
			form.setBeforeTime(String.valueOf(entity.getPreTime()));
			form.setMoveTime(String.valueOf(entity.getTrvTime()));
			form.setAfterTime(String.valueOf(entity.getAftTime()));
			form.setReportType(entity.getTypeCode());
			form.setTitle(entity.getTitle());
			form.setCustName(entity.getCustName());
			form.setCustDept(entity.getCustDept());
			form.setCustUserName(entity.getCustPersons());
			form.setFact(entity.getFact());
			form.setGuess(entity.getGuess());
			form.setNext(entity.getNext());
			form.setApplovalUserId(entity.getAplUserId());
			form.setApplovalUser(userNameMap.get(entity.getAplUserId()));
			form.setApplyFlag(entity.getAplFlag());
			form.setApplovalMessage(entity.getAplComment());
			form.setCommentList(commentValueList);
		}

		return form;
	}

	/**
	 * ActionFormクラスからＤＢへ値を新規登録
	 *
	 * @param form EditChartActionForm
	 */
	public void registData(EditReportActionForm form) throws Exception {
		try {
			ReportDao reportDao = new ReportDao(dbConn);
			ReportEntity entity = new ReportEntity();
			entity.setUserId(form.getUserId());
			entity.setDate(Utility.toDate(form.getViewDate()));
			entity.setSeq(getNewChartSeq(form.getUserId(), Utility.toDate(form.getViewDate())));
			entity.setStartTime(form.getStartTime());
			entity.setEndTime(form.getEndTime());
			entity.setPreTime(Utility.toDouble(form.getBeforeTime()));
			entity.setTrvTime(Utility.toDouble(form.getMoveTime()));
			entity.setAftTime(Utility.toDouble(form.getAfterTime()));
			entity.setTypeCode(form.getReportType());
			entity.setTitle(form.getTitle());
			entity.setCustName(form.getCustName());
			entity.setCustDept(form.getCustDept());
			entity.setCustPersons(form.getCustUserName());
			entity.setFact(form.getFact());
			entity.setGuess(form.getGuess());
			entity.setNext(form.getNext());
			entity.setAplUserId(form.getApplovalUserId());
			entity.setAplFlag(form.getApplyFlag());
			entity.setAplComment(form.getApplovalMessage());
			entity.setIdate(Utility.getTimestampNow());
			entity.setUdate(Utility.getTimestampNow());
			reportDao.setValue(entity);
			form.setSeq(String.valueOf(entity.getSeq()));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの更新
	 *
	 * @param form EditChartActionForm
	 */
	public void updateData(EditReportActionForm form) throws Exception {
		try {
			ReportDao reportDao = new ReportDao(dbConn);
			ReportEntity entity = reportDao.getValue(form.getUserId(), Utility.toDate(form.getViewDate()), Integer.parseInt(form.getSeq()));
			entity.setUserId(form.getUserId());
			entity.setDate(Utility.toDate(form.getViewDate()));
			entity.setSeq(Utility.toInt(form.getSeq()));
			entity.setStartTime(form.getStartTime());
			entity.setEndTime(form.getEndTime());
			entity.setPreTime(Utility.toDouble(form.getBeforeTime()));
			entity.setTrvTime(Utility.toDouble(form.getMoveTime()));
			entity.setAftTime(Utility.toDouble(form.getAfterTime()));
			entity.setTypeCode(form.getReportType());
			entity.setTitle(form.getTitle());
			entity.setCustName(form.getCustName());
			entity.setCustDept(form.getCustDept());
			entity.setCustPersons(form.getCustUserName());
			entity.setFact(form.getFact());
			entity.setGuess(form.getGuess());
			entity.setNext(form.getNext());
			entity.setAplUserId(form.getApplovalUserId());
			entity.setAplFlag(form.getApplyFlag());
			entity.setAplComment(form.getApplovalMessage());
			entity.setUdate(Utility.getTimestampNow());
			reportDao.setValue(entity);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 承認コメントの登録 ActionFormクラスからＤＢの更新
	 *
	 * @param form EditChartActionForm
	 */
	public void setComment(EditReportActionForm form) throws Exception {
		try {
			CommentDao commentDao = new CommentDao(dbConn);
			List<CommentEntity> commentList = commentDao.getList(form.getUserId(), Utility.toDate(form.getViewDate()), Integer.parseInt(form.getSeq()));
			int seqComment = 0;
			for (CommentEntity comment : commentList) {
				if (seqComment < comment.getSeqComment()) {
					seqComment = comment.getSeqComment();
				}
			}
			CommentEntity entity = new CommentEntity();
			entity.setUserId(form.getUserId());
			entity.setDate(Utility.toDate(form.getViewDate()));
			entity.setSeq(Integer.parseInt(form.getSeq()));
			entity.setSeqComment(seqComment + 1);
			entity.setRegUserId(form.getCommentUser());
			entity.setComment(form.getCommentMessage());
			entity.setIdate(Utility.getTimestampNow());
			entity.setUdate(Utility.getTimestampNow());

			commentList.add(entity);
			commentDao.setList(commentList);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 承認解除 ActionFormクラスからＤＢの更新
	 *
	 * @param form EditChartActionForm
	 */
	public void setRelease(EditReportActionForm form) throws Exception {
		try {
			ReportDao reportDao = new ReportDao(dbConn);
			ReportEntity entity = reportDao.getValue(form.getUserId(), Utility.toDate(form.getViewDate()), Integer.parseInt(form.getSeq()));
			entity.setAplUserId("");
			entity.setAplFlag(Param.MODE_OFF);
			entity.setUdate(Utility.getTimestampNow());
			reportDao.setValue(entity);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * ActionFormクラスからＤＢの削除
	 *
	 * @param form EditChartActionForm
	 */
	public void removeData(EditReportActionForm form) throws Exception {
		try {
			ReportDao reportDao = new ReportDao(dbConn);
			reportDao.removeValue(form.getUserId(), Utility.toDate(form.getViewDate()), Integer.parseInt(form.getSeq()));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 参照変換処理
	 *
	 * @param date Date
	 * @return String
	 */
	public String getDateString(Date date) throws Exception {
		return Utility.getDate(date);
	}

	/**
	 * 新規変換処理
	 *
	 * @param seq int
	 * @return String
	 */
	private int getNewChartSeq(String userId, Date date) throws Exception {
		ReportDao reportDao = new ReportDao(dbConn);
		List<ReportEntity> list = reportDao.getListSort(userId, date, "SEQ DESC LIMIT(1)");
		int n = 1;
		if (list.size() > 0) {
			ReportEntity info = list.get(0);
			n = info.getSeq() + 1;
		}
		return n;
	}
}
