# SampleDataflowProject
Fluxengineを利用するDataflowパイプラインの登録を実施することができます。

# Dependency
使用言語：java
ビルドツール：gradle & maven

# Version
Fluxengine 1.0.2

# SetUp
プロジェクトを実行するために以下の設定が必要となります。

  1. 当プロジェクトをチェックアウト
  2. ビルドを実施（現時点ではmavenプロジェクトなので、maven installを実施してください）

# Usage
サンプルＤＳＬの実行
  以下eclipseで動作させる前提での内容です

  1. Cloud SDKとCloud Dataflow のインストール
  ```
    https://cloud.google.com/dataflow/docs/quickstarts/quickstart-java-eclipseを参照すること
      Cloud SDKインストール    ... 上記のURLの4番
      Cloud Dataflowプラグイン ... 上記のURLの7番
  ```
  2. Dataflow Pipelineプロジェクトとして本プロジェクトの実行構成を行う
  ```
    メイン
      メイン・クラスに「jp.co.fluxengine.example.dataflow.StarterPipeline」を設定

    Pipeline Arguments
      アカウント、Project IDなどのoptionsを設定
      Pipline Optionsには「jp.co.fluxengine.gcp.dataflow.EventOptions」を設定
         fromTopicに受信先のpub/subイベントを設定する
  ```
  3.【任意】プラグインファイル(jar)の配置と設定変更
     本サンプルではSampleLocalProjectをパイプラインとして登録している。
     別のプラグインで動作確認を行う場合は下記ファイルの差し替えと伴にpom.xmlを修正すること
  ```
    対象ファイル:
      lib/fluxengine-local-sample-1.0.1.jar
  ```
  4. resources配下の設定ファイルを修正
  ```
    dslDataStore.properties ... DSLモジュール登録先のDatastore情報を設定する
      namespace=<GCPの名前空間を設定>
      kind=
      projectId=<GCPのプロジェクトIDを設定>

    persisterDataStore.properties ... 永続化先のDatastore情報を設定する
      namespace=<GCPの名前空間を設定>
      kind=
      projectId=<GCPのプロジェクトIDを設定>

    illegalEventDataStore.properties ... 処理失敗イベント情報の回避先を設定する
      namespace=<GCPの名前空間を設定>
      kind=
      projectId=<GCPのプロジェクトIDを設定>
  ```
  5. StarterPipeline.java を実行する

  6. 該当プロジェクトにパイプラインが登録されることを確認

# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
