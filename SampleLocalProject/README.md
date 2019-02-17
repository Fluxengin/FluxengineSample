# SampleLocalProject
エンジンであるFluxengineを用いて、モデル駆動開発を実施することができます。
DSLやプラグイン(java)のサンプルソースが格納されています。

# Dependency
使用言語：java
ビルドツール：gradle & maven

# Version
Fluxengine 1.0.4

# SetUp

1. 当プロジェクトをチェックアウト
2. プロジェクトディレクトリにて次のコマンドを実行する
```
gradlew
```

# Usage
サンプルＤＳＬの実行
  以下eclipseで動作させる前提での内容です

  1. /SampleLocalProject/src/main/java/jp/co/fluxengine/example/apptest/DslTestExecutor.java の実行構成を開く (メニュー＞実行＞実行構成）

  2. 引数タブのVM引数にSetUpで取得したresourcesフォルダのlog4j2.xmlパスを設定する
   ```
    例）-Dlog4j.configurationFile="file:\\\C:\Users\xxx\git\SampleLocalProject\conf\log4j2.xml"
   ```
  3. 環境タブの環境変数に以下を設定する
   ```
    <キー> / <値>
    CONF / C:\Users\xxx\git\SampleLocalProject\conf\
   ```
  4. DslTestExecutor.java に記載されているパスをチェックアウトした環境に合わせる

  5. DslTestExecutor.java を実行する

  6. /SampleLocalProject/out/test-result.json の中に、各ケースが"結果":"true"になることを確認する

     gradleでDSLのテストを実施の場合SampleLocalProjectディレクトリで次のコマンドを実施すること
   ```
     gradlew
     gradlew run -Pargs="src\main\dsl\packet src\test\dsl\packet out test.log"

     ※Windowsコマンドプロンプトの文字コードをUTF-8に変更する場合は次のコマンドを実施すること
     chcp 65001
   ```
# Authors
Fluxengine株式会社

https://www.fluxengine.co.jp/
