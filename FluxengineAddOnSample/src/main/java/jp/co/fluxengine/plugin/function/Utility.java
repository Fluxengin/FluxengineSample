package jp.co.fluxengine.plugin.function;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jp.co.fluxengine.stateengine.annotation.DslName;
import jp.co.fluxengine.stateengine.annotation.Function;

@Function("ユーティリティ")
public class Utility {


	//DSLから呼び出すにはstaticメソッドである必要がある

	@DslName("文字列連結")
	public static String strCat(String str1,String str2) {
		return "ユーザー定義【" + str1 + ":" + str2 + "】";
	}

	@DslName("文字列連結２")
	public static String strCat2(String str1,String str2) {
		return "ユーザー定義 文字列連結２【" + str1 + ":" + str2 + "】";
	}


	@DslName("フォーマット")
	public static String format(String format, String param) {
System.out.println("フォーマット: format=" + format + ", params=" + param);
//		for (String p : params) {
//			format = format.replace("{}", p);
//		}
		return format.replace("{0}", param);
	}
	@DslName("フォーマット2")
	public static String format(String format, String param, String param2) {
System.out.println("フォーマット2: format=" + format + ", params=" + param);
		return format.replace("{0}", param).replace("{1}", param2);
	}

	@DslName("状態判定")
	public static String match(String state, String compare, String tStr, String fStr) {
System.out.println("使用文字列判定: state=" + state + ", compare=" + compare + ", tStr=" + tStr + ", fStr=" + fStr);
		return state.equals(compare) ? tStr : fStr;
	}

	@DslName("日付変換")
	public static LocalDate getLocalDate(String date) {
		return LocalDate.parse(date);
	}
	@DslName("日時変換")
	public static LocalDateTime getLocalDateTime(String datetime) {
		return LocalDateTime.parse(datetime);
	}


    @DslName("各種引数確認")
    public static boolean get(String str, Integer num, String enumValue, boolean bl, LocalDate date, LocalDateTime datetime) {

    	StringBuilder sb = new StringBuilder();
    	sb.append("\n--------------------------各種引数確認関数\n");
    	sb.append("str:" + str + "\n");
    	sb.append("num:" + num + "\n");
    	sb.append("enumValue:" + enumValue + "\n");
    	sb.append("bl:" + bl + "\n");
    	sb.append("date:" + date + "\n");
    	sb.append("datetime:" + datetime + "\n");
    	sb.append("--------------------------\n");
    	System.out.println(sb.toString());

    	return true;
    }

    @DslName("配列引数確認")
    public static boolean getArray(List<String> strList, List<Integer> numList) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("\n--------------------------配列引数確認関数\n");
    	sb.append("strList\n");
    	for (String s : strList) {
        	sb.append("  " + s + "\n");

		}
    	sb.append("numList\n");
    	for (Integer i : numList) {
        	sb.append("  " + i + "\n");
		}
    	sb.append("--------------------------\n");
    	System.out.println(sb.toString());

    	return false;
    }

}
