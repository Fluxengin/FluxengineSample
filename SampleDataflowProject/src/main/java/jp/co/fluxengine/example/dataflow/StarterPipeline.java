package jp.co.fluxengine.example.dataflow;

import jp.co.fluxengine.gcp.dataflow.StreamProcessor;

/**
 * Dataflowにパイプラインをデプロイするサンプルです。
 *
 * ★事前準備
 *   【任意】プラグインファイル(jar)の配置と設定変更
 *      本サンプルではSampleLocalProjectをパイプラインとして登録している。
 *      別のプラグインで動作確認を行う場合は下記ファイルの差し替えと伴にpom.xmlを修正すること
 *
 *        <対象ファイル>
 *          lib/fluxengine-local-sample-1.0.1.jar
 *
 *    VM引数  ※当javaファイル実行時に、以下のVM引数を設定
 *      -Dlog4j.configurationFile=<log4j2.xmlへのパスを設定>
 *
 *    環境変数  ※当javaファイル実行時に、以下の環境変数
 *      CONF ... propertiesファイルなどがまとめられているディレクトリを設定
 *          例) C:\\SampleLocalProject\\conf\\
 *              ※Fluxengine設定ファイルの他に業務処理依存の設定ファイルもここに置くこと
 *      GOOGLE_APPLICATION_CREDENTIALS ... サービスアカウントキーファイルへのパス
 *
 *    dslDataStore.properties ... DSLモジュール登録先のDatastore情報を設定する
 *       namespace=<GCPの名前空間を設定>
 *       kind=
 *       PERSISTERprojectId=<GCPのプロジェクトIDを設定>
 *
 *    persisterDataStore.properties ... 永続化先のDatastore情報を設定する
 *       namespace=<GCPの名前空間を設定>
 *       kind=
 *       PERSISTERprojectId=<GCPのプロジェクトIDを設定>
 *
 *    実行オプション
 *      --project=<YOUR_PROJECT_ID>
 *      --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>    例) gs://<バケット名>/
 *      --runner=DataflowRunner
 *      --fromTopic=受信先のトピック   例) projects/<プロジェクト名>/topics/<トピック名
 */
public class StarterPipeline {
	public static void main(String[] args) {
		StreamProcessor.main(args);
	}
}
