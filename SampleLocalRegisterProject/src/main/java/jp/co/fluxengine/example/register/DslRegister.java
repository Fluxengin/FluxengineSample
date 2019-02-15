package jp.co.fluxengine.example.register;

import java.io.File;

import jp.co.fluxengine.stateengine.parser.DslParser;

public class DslRegister {

	public static void main(String[] args) {

		//①環境変数にCONF= "f:/conf" のように設定ファイルを格納するフォルダを設定する

		/**
		 * 下記のファイルが必要です。
		 *  attribute_ja.properties
		 *  method_ja.properties
		 *  name_ja.properties
		 *  package.properties
		 *  type_ja.properties
		 *  dslDataStore.properties
		 *  language.conf
		 *  log4j2.xml
		 *  license.lic
		 */

		//②Dsl-rootを設定します。

		String dslRootPath = "C:\\Users\\mdc\\git\\FluxengineSample\\SampleLocalProject\\src\\main\\dsl\\packet\\";

		String projectname = System.getProperty("user.dir");
		String pn = projectname.substring(projectname.lastIndexOf(File.separator) + 1, projectname.length());
		DslParser dslParser = new DslParser(dslRootPath, dslRootPath + File.separator + pn + ".obj");
		dslParser.parser();

		//③バージョンと適用開始を設定する。
		String version = "1";
		String startDate = "20190101";

		jp.co.fluxengine.stateengine.dsl.DslRegister
				.main(new String[] { dslRootPath + File.separator + pn + ".obj", version, startDate });

	}
}
