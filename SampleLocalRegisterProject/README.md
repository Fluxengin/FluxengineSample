# SampleLocalRegisterProject
ローカル開発環境にてFDSL及びプラグインをDataStoreのFDSLリポジトリに登録するサンプルです。

# Dependency
使用言語：java
ビルドツール：gradle & maven

# Version
Fluxengine 1.0.6

# SetUp

1. 当プロジェクトをチェックアウト
2. プロジェクトディレクトリにて次のコマンドを実行する
```
gradlew
```
3. ライセンスファイルを配置する（要問い合わせ）


# Usage
サンプルプロジェクトの実行
  以下eclipseで動作させる前提での内容です

  1. /SampleLocalRegisterProject/src/main/java/jp/co/fluxengine/example/register/LocalDslRegister.java の実行構成を開く (メニュー＞実行＞実行構成）

  2. 引数タブのVM引数にSetUpで取得したresourcesフォルダのlog4j2.xmlパスを設定する
   ```
    例）-Dlog4j.configurationFile="file:\\\C:\Users\xxx\git\SampleLocalRegisterProject\conf\log4j2.xml"
   ```
  3. 環境タブの環境変数に以下を設定する
   ```
    <キー> / <値>
    GOOGLE_APPLICATION_CREDENTIALS / クレデンシャルファイルのパス
   ```
  4. LocalDslRegister.java に記載されている登録先設定を環境に合わせる

  5. LocalDslRegister.java を実行する
   ```
   ※Cloud Shell環境で実行の場合は「/tools/regisdsl.sh」を利用すること
   ```
# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
