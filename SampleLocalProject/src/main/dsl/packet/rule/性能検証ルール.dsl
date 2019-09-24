persister Persister保存:
    使用量: number
    persist(Persister保存イベント.端末ID):
        lifetime: today()

persist Persister保存:
    使用量: 100
    watch(Persister保存イベント):

event Persister保存イベント:
    端末ID: string

event エフェクタダミー送信イベント:
    端末ID: string

event エフェクタ送信イベント:
    端末ID: string


event イベントのみ:
    端末ID: string

event マスターダミー参照イベント:
    端末ID: string

event クラウドDB参照イベント:
    端末ID: string

struct マスタダミー情報バリアント:
    ユーザーID: string
    パケット上限: number
    get(マスターダミー参照イベント.端末ID):
        cache: today()

struct クラウドSQL情報バリアント:
    ユーザーID: string
    パケット上限: number
   # get(クラウドDB参照イベント.端末ID):
    get("test"):
        cache: today()

rule マスターダミー参照ルール:
    1 == マスタダミー情報バリアント.パケット上限:
    watch(マスターダミー参照イベント):

rule クラウドマスター参照SQLルール:
     1 == クラウドSQL情報バリアント.パケット上限:
     watch(クラウドDB参照イベント):

effector ダミー送信:
    ユーザーID: string
    日時: datetime
    メッセージ: string

effect ダミー送信:
    ユーザーID: エフェクタダミー送信イベント.端末ID
    日時: now()
    メッセージ: "エフェクタ実行"
    watch(エフェクタダミー送信イベント):

effector DB書き込み送信:
    ユーザーID: string
    日時: datetime
    メッセージ: string

effect DB書き込み送信:
    ユーザーID: エフェクタ送信イベント.端末ID
    日時: now()
    メッセージ: "エフェクタ実行"
    watch(エフェクタ送信イベント):

event mysqlInsertイベント:
    端末ID: string
#effectMySQL w:
#  sql: mysql/hoge.sql
#  params:
#      check_item_id<bigint>: クラウドSQL情報バリアント.パケット上限
##      check_item_id<bigint>: 1
#      check_ctgr_item_detail_id<bigint>: 2
#      checked_date_time<timestamp>: now()
#      alert_message<varchar>: mysqlInsertイベント.端末ID
#      real_num_rate<bigint>: 3
#  watch(mysqlInsertイベント):


event onlyMysqlInsertイベント:
    端末ID: string

effectMySQL wonly:
  sql: mysql/hoge.sql
  params:
      check_item_id<bigint>: 1
      check_ctgr_item_detail_id<bigint>: 2
      checked_date_time<timestamp>: now()
      alert_message<varchar>: onlyMysqlInsertイベント.端末ID
      real_num_rate<bigint>: 3
  watch(onlyMysqlInsertイベント):


event onlyMysqlInsertWithVariantイベント:
    端末ID: string

effectMySQL wonlyVariant:
  sql: mysql/hoge.sql
  params:
      check_item_id<bigint>: クラウドSQL情報バリアント.パケット上限
      check_ctgr_item_detail_id<bigint>: 2
      checked_date_time<timestamp>: now()
      alert_message<varchar>: onlyMysqlInsertWithVariantイベント.端末ID
      real_num_rate<bigint>: 3
  watch(onlyMysqlInsertWithVariantイベント):

event Persister参照イベント:
    端末ID: string

rule Persister参照ルール:
    0 < Persister参照.使用量:
    watch(Persister参照イベント):


#永続化しないように
event Persister参照DummyEvent:
    端末ID: string

persister Persister参照:
    使用量: number
    persist(Persister参照イベント.端末ID):
        lifetime: today()

persist Persister参照:
    使用量: 100
    watch(Persister参照DummyEvent):

number 累積データ: PersistermysqlInsert.使用量 + 1
persister PersistermysqlInsert:
    使用量: number
    persist(mysqlInsertイベント.端末ID):
        lifetime: today()

persist PersistermysqlInsert:
    使用量: 累積データ
    watch(mysqlInsertイベント):

event redisイベント:
    端末ID: string

effector redisデータ出力:

effect redisデータ出力:
    watch(redisイベント):


string m:
    sql: mysql/read1.sql
    params:
        corpus<varchar>: イベントのみ.端末ID
    cache: today()

string n:
    イベントのみ.端末ID == "a":
        sql: mysql/read1.sql
        params:
            corpus<varchar>: イベントのみ.端末ID
    else:
        sql: mysql/read1.sql
        params:
            corpus<varchar>: イベントのみ.端末ID
    cache: today()


struct xx:
    name: string
    age: string
    birthday: date
    createtime: datetime
    イベントのみ.端末ID == "a":
        sql: mysql/read2.sql
        params:
            corpus<varchar>: イベントのみ.端末ID
    else:
        sql: mysql/read2.sql
        params:
            corpus<varchar>: イベントのみ.端末ID
    cache: today()

struct yy:
    name: string
    age: string
    birthday: date
    createtime: datetime
    sql: mysql/read2.sql
    params:
        corpus<varchar>: イベントのみ.端末ID
    cache: today()

rule mysqlrule:
    xx.name == イベントのみ.端末ID:
    watch(イベントのみ):