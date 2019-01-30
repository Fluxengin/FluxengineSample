package jp.co.fluxengine.example.remote;

import java.io.IOException;
import java.time.LocalDate;

import jp.co.fluxengine.remote.test.CloudStoreSelecter;
import jp.co.fluxengine.remote.test.RemoteRunner;

/**
 * ローカル開発環境でDataflowジョブのテストを実施し、テスト結果を取得するサンプルです。
 *
 * ★事前準備
 *  VM引数  ※当javaファイル実行時に、以下のVM引数を設定
 *    -Dlog4j.configurationFile=<log4j2.xmlへのパスを設定>
 *
 *  環境変数
 *    GOOGLE_APPLICATION_CREDENTIALS=<クレデンシャルファイルのパスを設定>
 *
 *  データ定義の修正(プログラム修正)
 *    ・テストデータ投入
 *      eventJosn ... 投入データの定義
 *      topic ... 投入先のPub/Subトピック定義
 *      projectID ... GCPプロジェクトID
 *      times ... テストデータ投入回数
 *
 *    ・永続化データ取得
 *      namespace ... 取得先DataStoreのNameSpace
 *      kind ... namespaceに付属の分類
 *      exportFileName ... 取得先ファイル名称
 *
 */
public class RemoteTestExecutor {

	public static void main(String[] args) throws IOException {

		//****************************************************************
		//*  テストデータ投入
		//****************************************************************
		// 投入データの定義
		String eventJosn = "[{\"eventName\":\"パケットイベント\",\"namespace\":\"event/パケットイベント\", \"createTime\":null, \"property\":{\"端末ID\":\"{string}\",\"日時\":\"{datatime}\",\"使用量\":{number}}}]";

		// 投入先のPub/Subトピック定義
		String topic = "projects/project/topics/event";

		// GCPプロジェクトID
		String projectID = "project";

		// 投入回数
		int times = 5;

		// GCPプロジェクトID設定
		RemoteRunner.setProjectId(projectID);

		// 各種プレースホルダの初期データ設定
		// 文字
		RemoteRunner.setStringInitValue("Campaign");

		// 日付
		RemoteRunner.setDateInitValue(LocalDate.of(2019, 1, 1));

		// 数値
		RemoteRunner.setNumberInitValue(1.0);

		// 実行
		RemoteRunner.publishPlactAuto(eventJosn, topic, times);

		// 実行(テストデータ自動生成を利用せずに直ちに投入)
		// RemoteRunner.publishOneTime(eventJosn, topic);

		//****************************************************************
		//*  永続化データ取得
		//****************************************************************
		String namespace = "NAMESPACE";
		String kind = "PERSISTER";
		String exportFileName = "/test.json";

		// 抽出先エンティティ指定
		CloudStoreSelecter cloudStoreSelecter = new CloudStoreSelecter(namespace, kind);

		// 出力先を指定し、ファイル保存
		cloudStoreSelecter.exportEntityDataToFile(exportFileName);

	}
}
