# SampleDataflowProject
Fluxengineを利用するDataflowパイプラインの登録を実施することができます。

# Dependency
使用言語：java
ビルドツール：gradle & maven

# Version
Fluxengine 1.0.7

# performanceLogDataflowJarフォルダの内容

  1.fluxengine-dataflow-1.0.7.jar
  性能集計ログを入れるdataflow.jar

  2.fluxengine-dataflow-1.0.7-pom.xml
  fluxengine-dataflow-1.0.7.jarのpom設定

# SetUp
プロジェクトを実行するために以下の設定が必要となります。

1. 当プロジェクトをチェックアウト
2. プロジェクトディレクトリにて次のコマンドを実行する
```
gradlew
```

# Usage
サンプルの実行
  以下eclipseで動作させる前提での内容です

  1. Dataflow Pipelineプロジェクトとして本プロジェクトの実行構成を行う
  ```
    メイン
      メイン・クラスに「jp.co.fluxengine.example.dataflow.StarterPipeline」を設定

    Pipeline Arguments
       アカウント、Project IDなどのoptionsを設定

       <ストリーミングモード>
        --runner=DataflowRunner
        --project=<GCPプロジェクトID>
        --stagingLocation=<ステージング先>
        --fromTopic= <受信元Pub/Subトピックス>
        --streaming=true
        【デバッグログ出力設定（本番環境不要）】
            --defaultWorkerLogLevel=DEBUG

       <バッチモード>
        --runner=DataflowRunner
        --project=<GCPプロジェクトID>
        --stagingLocation=<ステージング先>
        --templateLocation= <テンプレートテージング先>
        --streaming=false
        【デバッグログ出力設定（本番環境不要）】
            --defaultWorkerLogLevel=DEBUG

      ※Cloud Shell環境で実行の場合は「/tools/dataflow_job_publisher.sh」を利用すること
  ```
  2.【任意】プラグインファイル(jar)の配置と設定変更
     本サンプルではSampleLocalProjectをパイプラインとして登録している。
     別のプラグインで動作確認を行う場合は下記ファイルの差し替えと伴にpom.xmlを修正すること
  ```
    対象ファイル:
      lib/fluxengine-local-sample-{version}.jar
  ```
  3. resources配下の設定ファイルを修正
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

    effectorDataStore.properties ... 処理失敗エフェクタ情報の回避先を設定する
      namespace=<GCPの名前空間を設定>
      kind=
      projectId=<GCPのプロジェクトIDを設定>
    variant_cache.properties ... マスタ参照バリアントのキャッシュ設定
      maxRecords=キャッシュ上限レコード数（整数）

    fluxengine.properties   ... 設定(将来的、設定ファイルをfluxengine.propertiesに統一する予定がある)
      performanceLogEnabled=false <性能ログ出力かの設定true:出力|false:出力しない>
      sampling_n=100              <性能ログサンプリング標本数,1以上の数値>
      sampling_m=100              <性能ログサンプリング母集団数、1以上の数値>
      collectInterval=5           <性能ログ出力の集計間隔 単位：分 1以上の数値>
      eventRetryTimes=10           <トランザクションエラーの場合のリトライ回数>
  ```
  4. StarterPipeline.java を実行する

  5. 該当プロジェクトにパイプラインが登録されることを確認

# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
