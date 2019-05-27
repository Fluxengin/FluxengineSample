package jp.co.fluxengine.example.dataflowlog;

import jp.co.fluxengine.gcp.dataflow.logging.FluxengineDataflowLogProcessor;

/**
 * Dataflowにパイプラインをデプロイするサンプルです。
 *
 * ★事前準備
 *
 *    Dataflow Pipelineプロジェクトとして本プロジェクトの実行構成を行う
 *      メイン
 *         メイン・クラスに「jp.co.fluxengine.example.dataflowlog.StarterPipeline」を設定
 *
 *         <ストリーミングモード>
 *           --runner=DataflowRunner
 *           --project=<GCPプロジェクトID>
 *           --stagingLocation=<ステージング先>
 *           --fromTopic=<受信元Pub/Subトピックス>
 *           --collectInterval=<ログを収集・出力する間隔（分）>
 *           --streaming=true
 *           【デバッグログ出力設定（本番環境不要）】
 *               --defaultWorkerLogLevel=DEBUG
 *
 *   【任意】プラグインファイル(jar)の配置と設定変更
 *      本サンプルではSampleLocalProjectをパイプラインとして登録している。
 *      別のプラグインで動作確認を行う場合は下記ファイルの差し替えと伴にpom.xmlを修正すること
 *
 *        <対象ファイル>
 *          lib/fluxengine-local-sample-{version}.jar
 *
 */
public class StarterPipeline {
	public static void main(String[] args) {
		FluxengineDataflowLogProcessor.main(args);
	}
}
