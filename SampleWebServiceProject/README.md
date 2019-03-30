# SampleWebServiceProject
fluxengine-web-serviceのデプロイサンプルです。

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


  1.`/SampleWebServiceProject/src/main/webapp/WEB-INF`配下の設定ファイルを環境に合わせて修正
  ```
     dslDataStore.properties ... DSLモジュール登録先のDatastore情報を設定する
       namespace=<GCPの名前空間を設定>
       kind=
       projectId=<GCPのプロジェクトIDを設定>

     persisterDataStore.properties ... 永続化先のDatastore情報を設定する
       namespace=<GCPの名前空間を設定>
       kind=
       projectId=<GCPのプロジェクトIDを設定>
       retryTimes=リトライ回数

     variant_cache.properties ... マスタ参照バリアントのキャッシュ設定
       maxRecords=キャッシュ上限レコード数（整数）
  ```
  2.SampleWebServiceProjectをGAEにdeployする

  3.GAEのURLにアクセスし、Helloページが表示できたらデプロイ成功
  ```
  URL: https://{Project ID}.appspot.com/
  ```
  4.Fluxengine Web Serviceに対してEvent JSON文字列を送信
  ```
  URL: https://{Project ID}.appspot.com/fluxengine-web/event
  Content-Type: application/json
  Method: POST
  Body: Event JSON

  ```
  5.`["status":"SUCCEED"]`がレスポンスに出力されたら成功

# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
