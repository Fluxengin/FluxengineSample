//package jp.co.fluxengine.example.apptest;
//
//import jp.co.fluxengine.stateengine.test.TestDsl;
//
///**
// * ローカル開発環境でFDSLをテストするサンプルです。
// *
// * ★事前準備
// *  package.properties
// *    variantやeffectorなどのプラグインを格納するパッケージ名を記載
// *
// *  VM引数  ※当javaファイル実行時に、以下のVM引数を設定
// *    -Dlog4j.configurationFile=<log4j2.xmlへのパスを設定>
// *
// *  環境変数  ※当javaファイル実行時に、以下の環境変数
// *    CONF ... propertiesファイルなどがまとめられているディレクトリを設定
// *             例) C:\\SampleLocalProject\\conf\\
// *                 ※Fluxengine設定ファイルの他に業務処理依存の設定ファイルもここに置くこと
// */
//public class DslTestExecutor {
//
//	public static void main(String[] args) throws Exception {
//
//		/*
//		 * プロジェクトのベースディレクトリを設定
//		 */
//		String baseDir = "C:\\01_private_workspace\\git\\FluxengineSample\\SampleLocalProject\\";
//
//		/*
//		 * ■プログラム引数（起動引数に設定しても構いません）
//		 * ※絶対パスで指定してください
//		 *  1:検証対象のDSLルートディレクトリ
//		 *  2:テストコード（test DSL）のルートディレクトリ
//		 *  3:コンパイル後のモジュール格納および、test実施結果の出力ディレクトリ
//		 *  4:ログ出力先を指定（ファイルを指定）
//		 */
//		TestDsl.main(
//				new String[] {
//						baseDir + "src\\main\\dsl\\packet",
//						baseDir + "src\\test\\dsl\\packet",
//						baseDir + "out",
//						baseDir + "debug.log",
//				});
//	}
//
//}
