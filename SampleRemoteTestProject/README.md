# SampleRemoteTestProject
ローカル開発環境でDataflowジョブのテストを実施し、テスト結果を取得するサンプルです。

# Dependency
使用言語：java
ビルドツール：gradle & maven

# Version
Fluxengine 1.0.2

# SetUp

1. 当プロジェクトをチェックアウト
2. プロジェクトディレクトリにて次のコマンドを実行する
```
gradlew
```

# Usage
サンプルプロジェクトの実行
  以下eclipseで動作させる前提での内容です

  1. /SampleRemoteTestProject/src/main/java/jp/co/fluxengine/example/remote/RemoteTestExecutor.java の実行構成を開く (メニュー＞実行＞実行構成）

  2. 引数タブのVM引数にSetUpで取得したresourcesフォルダのlog4j2.xmlパスを設定する
   ```
    例）-Dlog4j.configurationFile="file:\\\C:\Users\xxx\git\SampleLocalProject\conf\log4j2.xml"
   ```
  3. 環境タブの環境変数に以下を設定する
   ```
    <キー> / <値>
    GOOGLE_APPLICATION_CREDENTIALS / クレデンシャルファイルのパス
   ```
  4. RemoteTestExecutor.java に記載されているデータ投入先や出力先ファイル名を環境に合わせる

  5. RemoteTestExecutor.java を実行する

# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
