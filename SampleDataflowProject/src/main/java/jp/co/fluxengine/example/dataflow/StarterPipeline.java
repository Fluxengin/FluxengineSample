package jp.co.fluxengine.example.dataflow;

import jp.co.fluxengine.gcp.dataflow.FluxengineDataflowProcessor;

/**
 * Dataflowにパイプラインをデプロイするサンプルです。
 *
 * ★事前準備
 *
 *    Dataflow Pipelineプロジェクトとして本プロジェクトの実行構成を行う
 *      メイン
 *         メイン・クラスに「jp.co.fluxengine.example.dataflow.StarterPipeline」を設定
 *
 *         <ストリーミングモード>
 *           --runner=DataflowRunner
 *           --project=<GCPプロジェクトID>
 *           --stagingLocation=<ステージング先>
 *           --fromTopic= <受信元Pub/Subトピックス>
 *           --streaming=true
 *           【デバッグログ出力設定（本番環境不要）】
 *               --defaultWorkerLogLevel=DEBUG
 *
 *         <バッチモード>
 *           --runner=DataflowRunner
 *           --project=<GCPプロジェクトID>
 *           --stagingLocation=<ステージング先>
 *           --templateLocation= <テンプレートテージング先>
 *           --streaming=false
 *           【デバッグログ出力設定（本番環境不要）】
 *              --defaultWorkerLogLevel=DEBUG
 *
 *   【任意】プラグインファイル(jar)の配置と設定変更
 *      本サンプルではSampleLocalProjectをパイプラインとして登録している。
 *      別のプラグインで動作確認を行う場合は下記ファイルの差し替えと伴にpom.xmlを修正すること
 *
 *        <対象ファイル>
 *          lib/fluxengine-local-sample-{version}.jar
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
 *        retryTimes=<リトライ回数>
 *
 *      illegalEventDataStore.properties ... 処理失敗イベント情報の回避先を設定する
 *        namespace=<GCPの名前空間を設定>
 *        kind=
 *        projectId=<GCPのプロジェクトIDを設定>
 *        retryTimes=<リトライ回数>
 *
 *      effectorDataStore.properties ... 処理失敗エフェクタ情報の回避先を設定する
 *        namespace=<GCPの名前空間を設定>
 *        kind=
 *        projectId=<GCPのプロジェクトIDを設定>
 *        retryTimes=<リトライ回数>
 *
 *      variant_cache.properties ... マスタ参照バリアントのキャッシュ設定
 *        maxRecords=キャッシュ上限レコード数（整数）
 *
 */
public class StarterPipeline {
	public static void main(String[] args) {
		FluxengineDataflowProcessor.main(args);
	}
}
