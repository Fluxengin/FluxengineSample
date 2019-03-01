package jp.co.fluxengine.example.register;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import jp.co.fluxengine.stateengine.dsl.DslRegister;
import jp.co.fluxengine.stateengine.parser.DslParser;

/**
 * ローカル開発環境にてFDSL及びプラグインをDataStoreのFDSLリポジトリに登録するサンプルです。
 *
 * ★事前準備
 *   設定ファイル準備
 *     license.lic ... Fluxengineライセンスファイル
 *     package.properties ... 各種プラグインのパッケージ設定。（SampleLocalProjectと同じもの）
 *     dslDataStore.properties ... DSLモジュール登録先のDatastore情報を設定する
 *     log4j2.xml ... ログ出力設定
 *
 *     多言語設定ファイル
 *       language.conf
 *       attribute_ja.properties
 *       method_ja.properties
 *       name_ja.properties
 *       type_ja.properties
 *
 *  VM引数  ※当javaファイル実行時に、以下のVM引数を設定
 *    -Dlog4j.configurationFile=<log4j2.xmlへのパスを設定>
 *
 *  環境変数  ※当javaファイル実行時に、以下の環境変数
 *    CONF ... propertiesファイルなどがまとめられているディレクトリを設定
 *       例) C:\\SampleLocalRegisterProject\\conf\\
 *
 *  FDSL登録に必要な情報を設定
 *    dslRootPath ... 登録対象FDSLのパス
 *    version ... 登録バージョン
 *    startDate ... 適用開始日
 *
 */
public class LocalDslRegister {

	public static void main(String[] args) {

		// 登録対象FDSLのパス
		String dslRootPath = "C:\\git\\FluxengineSample\\SampleLocalProject\\src\\main\\dsl\\packet";

		// 登録バージョン
		String version = "1";

		// 適用開始日
		String startDate = "20190101";

		// 登録実行
		String projectname = System.getProperty("user.dir");
		projectname = projectname.substring(projectname.lastIndexOf(File.separator) + 1, projectname.length());
		DslParser parser = new DslParser(dslRootPath, dslRootPath + File.separator + projectname + ".obj");
		parser.parser();

		Path path = Paths.get(dslRootPath + File.separator + projectname + ".obj");
		if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			DslRegister.main(new String[] { dslRootPath + File.separator + projectname + ".obj", version, startDate });
		}
	}
}
