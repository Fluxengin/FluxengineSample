package jp.co.fluxengine.example.dataflow;

import jp.co.fluxengine.gcp.dataflow.StreamProcessor;

/**
 * Dataflowにパイプラインをデプロイするサンプルです。
 *
 * ★事前準備
 *
 *    Cloud SDKとCloud Dataflow のインストール
 *    https://cloud.google.com/dataflow/docs/quickstarts/quickstart-java-eclipseを参照すること
 *      Cloud SDKインストール    ... 上記のURLの4番
 *      Cloud Dataflowプラグイン ... 上記のURLの7番
 *
 *    Dataflow Pipelineプロジェクトとして本プロジェクトの実行構成を行う
 *      メイン
 *         メイン・クラスに「jp.co.fluxengine.example.dataflow.StarterPipeline」を設定
 *      Pipeline Arguments
 *         アカウント、Project IDなどのoptionsを設定
 *         Pipline Optionsには「jp.co.fluxengine.gcp.dataflow.EventOptions」を設定
 *           fromTopicに受信先のpub/subイベントを設定する
 *
 *   【任意】プラグインファイル(jar)の配置と設定変更
 *      本サンプルではSampleLocalProjectをパイプラインとして登録している。
 *      別のプラグインで動作確認を行う場合は下記ファイルの差し替えと伴にpom.xmlを修正すること
 *
 *        <対象ファイル>
 *          lib/fluxengine-local-sample-1.0.1.jar
 *
 *    resources配下の設定ファイルを修正
 *      dslDataStore.properties ... DSLモジュール登録先のDatastore情報を設定する
 *        namespace=<GCPの名前空間を設定>
 *        kind=
 *        projectId=<GCPのプロジェクトIDを設定>
 *
 *      persisterDataStore.properties ... 永続化先のDatastore情報を設定する
 *        namespace=<GCPの名前空間を設定>
 *        kind=
 *        projectId=<GCPのプロジェクトIDを設定>
 *
 *      illegalEventDataStore.properties ... 処理失敗イベント情報の回避先を設定する
 *        namespace=<GCPの名前空間を設定>
 *        kind=
 *        projectId=<GCPのプロジェクトIDを設定>
 *
 */
public class StarterPipeline {
	public static void main(String[] args) {
		StreamProcessor.main(args);
	}
}
