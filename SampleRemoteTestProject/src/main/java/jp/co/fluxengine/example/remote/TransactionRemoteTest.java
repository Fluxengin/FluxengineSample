package jp.co.fluxengine.example.remote;

import jp.co.fluxengine.remote.test.CloudStoreSelecter;
import jp.co.fluxengine.remote.test.RemoteRunner;

import java.io.IOException;

/**
 * ローカル開発環境でDataflowジョブのテストを実施し、テスト結果を取得するサンプルです。
 * <p>
 * ★事前準備
 * VM引数  ※当javaファイル実行時に、以下のVM引数を設定
 * -Dlog4j.configurationFile=<log4j2.xmlへのパスを設定>
 * <p>
 * 環境変数
 * GOOGLE_APPLICATION_CREDENTIALS=<クレデンシャルファイルのパスを設定>
 * <p>
 * データ定義の修正(プログラム修正)
 * ・テストデータ投入
 * eventJosn ... 投入データの定義
 * topic ... 投入先のPub/Subトピック定義
 * projectID ... GCPプロジェクトID
 * times ... テストデータ投入回数
 * <p>
 * ・永続化データ取得
 * namespace ... 取得先DataStoreのNameSpace
 * kind ... namespaceに付属の分類
 * exportFileName ... 取得先ファイル名称
 */
public class TransactionRemoteTest {

    public static void main(String[] args) throws IOException {

        //****************************************************************
        //*  永続化データ取得
        //****************************************************************
        String namespace = "TRANSACTION_CONSISTENCY_TEST";
        String kind = "PERSISTER";

        // 抽出先エンティティ指定
        CloudStoreSelecter cloudStoreSelecter = new CloudStoreSelecter(namespace, kind);

        // 出力先を指定し、ファイル保存
        cloudStoreSelecter.exportEntityDataToFile("./testBefore.txt");

        //****************************************************************
        //*  テストデータ投入
        //****************************************************************
        // 投入データの定義
        String eventA = "[{\"eventName\":\"パケットイベント\",\"namespace\":\"event/パケットイベント\", \"createTime\":null, \"property\":{\"端末ID\":\"C01\",\"日時\":\"2019/06/10 10:00:01\",\"使用量\":100}}]";
        String eventB = "[{\"eventName\":\"パケットイベント\",\"namespace\":\"event/パケットイベント\", \"createTime\":null, \"property\":{\"端末ID\":\"C01\",\"日時\":\"2019/06/10 10:00:01\",\"使用量\":200}}]";

        // 投入先のPub/Subトピック定義
        String topic = "projects/fluxengine-test/topics/transaction-consistency-test";

        // GCPプロジェクトID
        String projectID = "fluxengine-test";

        // GCPプロジェクトID設定
        RemoteRunner.setProjectId(projectID);

        RemoteRunner.publishOneTime(eventA, topic);

        sleep(2000);

        RemoteRunner.publishOneTime(eventB, topic);

        sleep(1000);

        cloudStoreSelecter.exportEntityDataToFile("./testInter1.txt");

        sleep(2000);

        cloudStoreSelecter.exportEntityDataToFile("./testInter2.txt");

        sleep(3000);

        cloudStoreSelecter.exportEntityDataToFile("./testInter3.txt");

        sleep(30000);

        cloudStoreSelecter.exportEntityDataToFile("./testAfter.txt");

    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
