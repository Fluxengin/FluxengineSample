# SampleDataflowBatchProject
fluxengine-dataflow-batch-servletのデプロイサンプルです。

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

# Usage

  以下GAEで動作させる前提での内容です。


  1.`/SampleDataflowBatchProject/src/main/webapp/WEB-INF`配下の設定ファイルを環境に合わせて修正
  ```
     cron.xml ... スケジューラを設定する
       url=/fluxengine-dataflow-batch?event=<イベントのネームスペース#イベント名>
       ※URLエンコードを行うこと

     job.properties ... イベントパラメータを設定する
       projectId=<バッチタイプジョブの実行先プロジェクトID>
       region=<バッチタイプジョブのりリージョン>
       jobNamePrefix=<バッチタイプジョブ名称のプレフィックス（ジョブ名はプレフィックス+ yyyyMMdd-HHmmss-SSSで作成する）>
       templateLocation=<バッチタイプジョブテンプレートのステージング先>
       eventName=<投入先イベント名＋ネームスペース（複数の場合は半角カンマ区切り）>
       {イベント}. property=<投入先イベントの属性名>
       {イベント}. type=<イベント各属性のデータタイプ（{イベント}. property順）>
       {イベント}.value=<イベント各属性に設定する値（{イベント}. property順）>
  ```
  2.SampleDataflowBatchProjectをGAEにdeployする

  3.GAEのURLにアクセスし、Helloページが表示できたらデプロイ成功

  4.CRONジョブを実行し、ステータスに「成功しました」が表示したらバッチタイプジョブが実行成功


# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
