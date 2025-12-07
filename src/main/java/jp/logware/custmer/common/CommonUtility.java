package jp.logware.custmer.common;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import beachstone.date.DateUtility;
import beachstone.date.SqlDateUtility;

public class CommonUtility {
	/**
	 * 参照変換処理
	 * @param date Date
	 * @return String
	 */
	public static String getDateString( Date date ) throws Exception {
		return Utility.getDate( date );
	}

	/**
	 * 時間を表示用文字列に変換する
	 * @param time
	 * @return
	 */
	public static String getViewTimeZeroString( String time ){
		String str = time.substring(0,2) + ":" + time.substring(2,4);
		return str;
	}

	/**
	 * 時間を表示用文字列に変換する（先頭ゼロなし）
	 * @param time
	 * @return
	 */
	public static String getViewTimeString( String time ){
		String str = time.substring(0,2) + ":" + time.substring(2,4);
		if( str.substring(0,1).equals("0") ){
			str = str.substring(1,5);
		}
		return str;
	}

	/**
	 * 表示用日付文字列に変換する
	 * @param date
	 * @return
	 */
	public static String getViewDate( String date ){
		DateFormat dfm = new SimpleDateFormat("yyyy年MM月");
		java.util.Date utilDate = SqlDateUtility.getSqlDate(date);
		return dfm.format( utilDate );
	}

	/**
	 * 表示用日付文字列に変換する
	 * @param date
	 * @return
	 */
	public static String getViewDay( String date ){
		DateFormat dfm = new SimpleDateFormat("yyyy年MM月dd日(E)");
		java.util.Date utilDate = SqlDateUtility.getSqlDate(date);
		return dfm.format( utilDate );
	}

	/**
	 * 新規ボタン用スクリプト文字列（月、スケジュール）
	 * @param userId
	 * @param date
	 * @return
	 */
	public static String[] getNewLinkStringMonthSchedule( String userId, String date ){
		return getNewLinkStringMonth( userId, date, "Schedule", true);
	}

	/**
	 * 新規ボタン用スクリプト文字列（月、カルテ）
	 * @param userId
	 * @param date
	 * @return
	 */
	public static String[] getNewLinkStringMonthChart( String userId, String date ){
		return getNewLinkStringMonth( userId, date, "Chart", true);
	}

	/**
	 * 新規ボタン用スクリプト文字列（月、スケジュール）
	 * @param userId
	 * @param date
	 * @return
	 */
	public static String[] getNewLinkStringWeekSchedule( String userId, String date ){
		return getNewLinkStringMonth( userId, date, "Schedule", false);
	}

	/**
	 * 新規ボタン用スクリプト文字列（月、カルテ）
	 * @param userId
	 * @param date
	 * @return
	 */
	public static String[] getNewLinkStringWeekChart( String userId, String date ){
		return getNewLinkStringMonth( userId, date, "Chart", false);
	}

	/**
	 * 新規ボタン用スクリプト文字列（月）
	 * @param userId
	 * @param date
	 * @return
	 */
	private static String[] getNewLinkStringMonth( String userId, String date, String type, boolean isMonth ){
		String[] range = null;
		if( isMonth ){
			range = DateUtility.getMonth( date );
		} else {
			range = DateUtility.getWeek( date );
		}
		String[] newLinkString = new String[range.length];
		for( int i=0; i<range.length; i++ ){
			newLinkString[i] = "javascript:doNew" + type +"('" + userId + "','" + range[i] + "')";
		}
		return newLinkString;
	}

	/**
	 * 文字列内の改行コードをhtml:brに変換する
	 * @param str
	 * @return
	 */
	public static String getViewBrString( String str ){
		return str.replaceAll("\r", "<br>");
	}

	/**
	 * 時刻の大小比較
	 * @param time1
	 * @param time2
	 * @return true:OK false:NG
	 */
	public static boolean checkTimeCompere( String time1, String time2 ){
		int t1 = Integer.parseInt(time1);
		int t2 = Integer.parseInt(time2);
		if( t1 < 700 ){
			t1 += 2400;
		}
		if( t2 <= 700 ){
			t2 += 2400;
		}
		if( t1 < t2 ){
			return true;
		} else {
			return false;
		}
	}
}
