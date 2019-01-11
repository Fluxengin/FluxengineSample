# SampleDataflowProject
Fluxengineを利用するDataflowパイプラインの登録を実施することができます。

# Dependency
使用言語：java
ビルドツール：maven (のちにgradle対応)

# Version
Fluxengine 1.0.1

# SetUp
プロジェクトを実行するために以下の設定が必要となります。

  1. 当プロジェクトをチェックアウト

  2. ビルドを実施（現時点ではmavenプロジェクトなので、maven installを実施してください）

# Usage
サンプルＤＳＬの実行
  以下eclipseで動作させる前提での内容です

  1. /SampleDataflowProject/src/main/java/jp/co/fluxengine/example/dataflow/StarterPipeline.java の実行構成を開く (メニュー＞実行＞実行構成）

  2. 引数タブのVM引数にSetUpで取得したresourcesフォルダのlog4j2.xmlパスを設定する

          例）-Dlog4j.configurationFile="file:\\\C:\Users\xxx\git\SampleLocalProject\conf\log4j2.xml"

  3. 環境タブの環境変数に以下を設定する

    <キー> / <値>

    CONF / C:\Users\xxx\git\SampleLocalProject\conf\
    GOOGLE_APPLICATION_CREDENTIALS \ サービスアカウントキーファイルへのパス

  4. 実行オプション

    --project=<YOUR_PROJECT_ID>
    --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>    例) gs://<バケット名>/
    --runner=DataflowRunner
    --fromTopic=受信先のトピック                             例) projects/<プロジェクト名>/topics/<トピック名

  5. dslDataStore.properties ... DSLモジュール登録先のDatastore情報を設定する
     namespace=<GCPの名前空間を設定>
     kind=
     PERSISTERprojectId=<GCPのプロジェクトIDを設定>

  6. persisterDataStore.properties ... 永続化先のDatastore情報を設定する
     namespace=<GCPの名前空間を設定>
     kind=
     PERSISTERprojectId=<GCPのプロジェクトIDを設定>

  7. DslTestExecutor.java を実行する

  8. 該当プロジェクトにパイプラインが登録されることを確認

# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
