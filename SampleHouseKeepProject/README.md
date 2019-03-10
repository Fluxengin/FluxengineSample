# SampleHouseKeepProject
fluxengine-dataflow-housekeepとfluxengine-dataflow-housekeep-servletのデプロイサンプルです。

# Dependency
使用言語：java
ビルドツール：gradle & maven

# Version
Fluxengine 1.0.5

# SetUp

1. 当プロジェクトをチェックアウト
2. プロジェクトディレクトリにて次のコマンドを実行する
```
gradlew
```

# Usage

  以下GAEで動作させる前提での内容です。

  1. resources配下の設定ファイルを修正
  ```
    persisterDataStore.properties ... 永続化先のDatastore情報を設定する
      namespace=<GCPの名前空間を設定>
      kind=
      projectId=<GCPのプロジェクトIDを設定>
      retryTimes=<リトライ回数>

    houseKeep.properties ... ハウスキーピング対象期間を指定する
      common.duration_to=<共通削除対象期間To>  例) common.duration_to=today()
      <永続化部品名>.duration_to=              例) パケット積算データ.duration_to=today()
  ```

  2. Housekeepバッチジョブを作成する
  ```
    ＜対象＞
    jp.co.fluxengine.gcp.dataflow.housekeep.HouseKeepProcessor

    ＜オプション＞
    --runner=DataflowRunner
    --project=<GCPプロジェクトID>
    --stagingLocation=<ステージング先>
    --templateLocation= <テンプレートテージング先>
    --streaming=false
  ```

  3. `/SampleHouseKeepProject/src/main/webapp/WEB-INF`配下の設定ファイルを環境に合わせて修正
  ```
    cron.xml ... スケジューラを設定する
       url=/fluxengine-dataflow-batch?event=<イベントのネームスペース#イベント名>
       ※URLエンコードを行うこと

    housekeepJob.properties ... イベントパラメータを設定する
       projectId=<バッチタイプジョブの実行先プロジェクトID>
       region=<バッチタイプジョブのりリージョン>
       jobNamePrefix=<バッチタイプジョブ名称のプレフィックス（ジョブ名はプレフィックス+ yyyyMMdd-HHmmss-SSSで作成する）>
       templateLocation=<バッチタイプジョブテンプレートのステージング先>
  ```
  4. SampleHouseKeepProjectをGAEにdeployする

  5. GAEのURLにアクセスし、Helloページが表示できたらデプロイ成功

  6. CRONジョブを実行し、ステータスに「成功しました」が表示したらバッチタイプジョブが実行成功


# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
