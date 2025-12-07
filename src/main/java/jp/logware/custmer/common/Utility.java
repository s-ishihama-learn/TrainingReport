package jp.logware.custmer.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 共通処理クラス
 * @author Shigeru.Ishihama
 * @version 1.0
 */
public class Utility {
	/**
	 * java.sql.Dateへの変換処理
	 * @param value 日付文字列
	 */
	public static java.sql.Date toDate( String value ){
		java.sql.Date date = null;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date udate = format.parse(value.replaceAll("/", "-"));
			date = new java.sql.Date(udate.getTime());
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * java.sql.Timeへの変換処理
	 * @param value 時刻文字列
	 */
	public static java.sql.Time toTime( String value ){
		java.sql.Time time = null;
		try {
			if( value.length() == 5 ){
				value = value + ":00";
			}
			DateFormat format = new SimpleDateFormat("HH:mm:ss");
			java.util.Date date = format.parse(value);
			time = new java.sql.Time(date.getTime());
		} catch (Exception e) {
		}
		return time;
	}

	/**
	 * java.sql.Timestampへの変換処理
	 * @param value 日時文字列
	 */
	public static java.sql.Timestamp toTimestamp( String value ){
		java.sql.Timestamp time = null;
		try {
			if( value.length() == 16 ){
				value = value + ":00";
			}
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date = format.parse(value.replaceAll("/", "-"));
			time = new java.sql.Timestamp(date.getTime());
		} catch (Exception e) {
		}
		return time;
	}

	/**
	 * int型への変換処理
	 * @param value 数値(int)文字列
	 * @return int型数値 引数がint文字列でない場合は0を返す。
	 */
	public static int toInt( String value ){
	    try{
	        return Integer.parseInt(value);
	    } catch (Exception e) {
	        return 0;
	    }
	}

	/**
	 * 現在の日を返す。
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getNowDate() {
		return getNow("yyyy-MM-dd");
	}

	/**
	 * 現在の日時を返す。
	 *
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getNowDateTime() {
		return getNow("yyyy-MM-dd HH:mm");
	}

	/**
	 * 現在の日時を返す。
	 *
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDateTimeSec() {
		return getNow("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 現在の時刻を返す。
	 *
	 * @return HH:mm
	 */
	public static String getNowTime() {
		return getNow("HH:mm");
	}

	/**
	 * 現在の時刻を返す。
	 *
	 * @return HH:mm:ss
	 */
	public static String getNowTimeSec() {
		return getNow("HH:mm:ss");
	}

	/**
	 * 現在の日をDate型で返す。
	 *
	 * @return Date
	 */
	public static java.sql.Date getDateNow() {
		return toDate( getNowDateTimeSec() );
	}

	/**
	 * 現在の時刻をTime型で返す。
	 *
	 * @return Time
	 */
	public static java.sql.Time getTimeNow() {
		return toTime( getNowDateTimeSec() );
	}

	/**
	 * 現在の日時をTimestamp型で返す。
	 *
	 * @return Timestamp
	 */
	public static java.sql.Timestamp getTimestampNow() {
		return toTimestamp( getNowDateTimeSec() );
	}

	/**
	 * 現在の日時を指定したフォーマットで返す。
	 *
	 * @param format フォーマット<br>
	 *            ex) yyyy-MM-dd HH:mm:ss
	 * @return フォーマット変換した文字列
	 */
	private static String getNow(String format) {
		Calendar cal = Calendar.getInstance();
		java.util.Date now = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(now);
	}

	/**
	 * 指定したjava.sql.Dateを日付文字列に変換する。
	 * @param date java.sql.Date
	 * @return yyyy-MM-dd
	 */
	public static String getDate( Date date ){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(date);
	}

	/**
	 * 指定したjava.sql.Timestampを日時文字列に変換する。
	 * @param date java.sql.Timestamp
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getDateTime( Timestamp date ){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return fmt.format(date);
	}

	/**
	 * 指定したjava.sql.Timestampを日時文字列に変換する。
	 * @param date java.sql.Timestamp
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeSec( Timestamp date ){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fmt.format(date);
	}

	/**
	 * 指定したjava.sql.Timestampを日付文字列に変換する。
	 * @param date java.sql.Timestamp
	 * @return yyyy-MM-dd
	 */
	public static String getDate( Timestamp date ){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(date);
	}

	/**
	 * 指定したjava.sql.Timestampを時刻文字列に変換する。
	 * @param date java.sql.Timestamp
	 * @return HH:mm
	 */
	public static String getTime( Timestamp date ){
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
		return fmt.format(date);
	}

	/**
	 * 指定したjava.sql.Timestampを時刻文字列に変換する。
	 * @param date java.sql.Timestamp
	 * @return HH:mm:ss
	 */
	public static String getTimeSec( Timestamp date ){
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		return fmt.format(date);
	}

	/**
	 * long型への変換処理
	 * @param value 数値(long)文字列
	 * @return long型数値 引数がlong文字列でない場合は0を返す。
	 */
	public static long toLong( String value ){
	    try{
	        return Long.parseLong(value);
	    } catch (Exception e) {
	        return 0L;
	    }
	}

	/**
	 * float型への変換処理
	 * @param value 数値(float)文字列
	 * @return float型数値 引数がfloat文字列でない場合は0.0を返す。
	 */
	public static float toFloat( String value ){
	    try{
	        return Float.parseFloat(value);
	    } catch (Exception e) {
	        return 0.0f;
	    }
	}

	/**
	 * double型への変換処理
	 * @param value 数値(double)文字列
	 * @return double型数値 引数がdouble文字列でない場合は0を返す。
	 */
	public static double toDouble( String value ){
	    try{
	        return Double.parseDouble(value);
	    } catch (Exception e) {
	        return 0.0;
	    }
	}

	/**
	 * 暗号化メソッド
	 *
	 * @param text 暗号化する文字列
	 * @return 暗号化文字列
	 */
	public static String encrypt(String text) {
		// 変数初期化
		String strResult = null;

		try {
			// 文字列をバイト配列へ変換
			byte[] byteText = text.getBytes("UTF-8");

			// 暗号化キーと初期化ベクトルをバイト配列へ変換
			byte[] byteKey = Param.ENCRYPT_KEY.getBytes("UTF-8");
			byte[] byteIv = Param.ENCRYPT_IV.getBytes("UTF-8");

			// 暗号化キーと初期化ベクトルのオブジェクト生成
			SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(byteIv);

			// Cipherオブジェクト生成
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			// Cipherオブジェクトの初期化
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			// 暗号化の結果格納
			byte[] byteResult = cipher.doFinal(byteText);

			// Base64へエンコード
			strResult = Base64.getEncoder().encodeToString(byteResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 暗号化文字列を返却
		return strResult;
	}

	/**
	 * 復号化メソッド
	 *
	 * @param text 復号化する文字列
	 * @return 復号化文字列
	 */
	public static String decrypt(String text) {
		// 変数初期化
		String strResult = null;

		try {
			// Base64をデコード
			byte[] byteText = Base64.getDecoder().decode(text);

			// 暗号化キーと初期化ベクトルをバイト配列へ変換
			byte[] byteKey = Param.ENCRYPT_KEY.getBytes("UTF-8");
			byte[] byteIv = Param.ENCRYPT_IV.getBytes("UTF-8");

			// 復号化キーと初期化ベクトルのオブジェクト生成
			SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(byteIv);

			// Cipherオブジェクト生成
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			// Cipherオブジェクトの初期化
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			// 復号化の結果格納
			byte[] byteResult = cipher.doFinal(byteText);

			// バイト配列を文字列へ変換
			strResult = new String(byteResult, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 復号化文字列を返却
		return strResult;
	}
}
