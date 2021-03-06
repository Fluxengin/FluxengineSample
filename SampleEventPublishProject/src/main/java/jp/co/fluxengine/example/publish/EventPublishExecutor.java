package jp.co.fluxengine.example.publish;

import java.lang.reflect.Method;

import jp.co.fluxengine.example.plugin.read.DailyDataReader;
import jp.co.fluxengine.publisher.CsvPublisher;
import jp.co.fluxengine.publisher.JsonPublisher;
import jp.co.fluxengine.publisher.ReadPublisher;

/**
 * ローカル開発環境で指定のPub/Subに対してイベントを投入するサンプルです。
 *
 * ★イベントの投入は次の方法で行います：
 *   CSVデータを用いたイベント投入
 *     CSVファイルから投入データを読み込み、指定のトピックに投入する
 *
 *   JSON形式イベント投入
 *     JSON形式のイベントデータを指定のトピックに投入する
 *
 *   Readプラグインを利用したイベント投入
 *     Readプラグインで投入データを取得し、指定のトピックに投入する
 *
 *
 * ★事前準備
 *  VM引数  ※当javaファイル実行時に、以下のVM引数を設定
 *    -Dlog4j.configurationFile=<log4j2.xmlへのパスを設定>
 *
 *  環境変数
 *    GOOGLE_APPLICATION_CREDENTIALS=<クレデンシャルファイルのパスを設定>
 *    CONF ... propertiesファイルなどがまとめられているディレクトリを設定
 *             例) C:\\SampleLocalProject\\conf\\
 *                 ※Fluxengine設定ファイルの他に業務処理依存の設定ファイルもここに置くこと
 *
 *  データ定義の修正(プログラム修正)
 *    CSVデータを用いたイベント投入
 *      csvFileFullPath ... 投入データの定義
 *
 *    JSON形式イベント投入
 *      eventJosn ... 投入データの定義
 *
 *    Readプラグインを利用したイベント投入
 *      namespace ... イベントのネームスペース
 *      eventName ... イベント名
 *      readClazz ... Readプラグイン実装クラス
 *      method ... データ取得処理を行うメソッド
 *      args ... データ取得メソッド引数に設定する値
 *
 *  データ定義の修正(設定ファイル修正)
 *    conf/publisher.properties
 *      投入先プロジェクトとPub/Subトピックの設定
 *
 *    conf/csv.properties
 *       投入対象CSVの設定
 *         ※CSVデータを利用の場合
 *
 */
public class EventPublishExecutor {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {

		EventPublishExecutor executor = new EventPublishExecutor();
		// CSVデータを用いたイベント投入
		executor.publishCSVData();

		// JSON形式イベント投入
		executor.publishJsonEvent();

		// Readプラグインを利用したイベント投入
		executor.publishWithReadPlugin();
	}

	/* CSVデータを用いたイベント投入 */
	private void publishCSVData() {
		// 読み込み対象CSVの格納先を指定
		String csvFileFullPath = "input/event.csv";
		// データ投入
		CsvPublisher publisher = new CsvPublisher(csvFileFullPath);
		publisher.publish();
	}

	/* JSON形式イベント投入 */
	private void publishJsonEvent() {
		// 投入データの定義
		String eventJosn = "[{\"eventName\":\"パケットイベント\", \"namespace\":\"event/パケットイベント\", \"createTime\":null, \"property\":{\"端末ID\":\"C01\",\"日時\":\"2018/11/10 00:00:01\",\"使用量\":500}}] ";
		// データ投入
		JsonPublisher publisher = new JsonPublisher(eventJosn);
		publisher.publish();
	}

	/* Readプラグインを利用したイベント投入 */
	private void publishWithReadPlugin() throws NoSuchMethodException, SecurityException {

		// イベントのネームスペース
		String namespace = "event/パケットイベント";

		// イベント名
		String eventName = "パケットイベント";

		// Readプラグイン実装クラス
		Class<?> readClazz = DailyDataReader.class;

		// データ取得処理を行うメソッド
		Method method = readClazz.getMethod("getList", String.class);

		// データ取得メソッド引数に設定する値
		Object[] args = new Object[] { "C01" };

		// データ投入
		ReadPublisher publisher = new ReadPublisher(namespace, eventName, readClazz, method, args);
		publisher.publish();
	}

}
