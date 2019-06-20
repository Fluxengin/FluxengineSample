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
    get(マスター参照イベント.端末ID):
        cache: today()

struct クラウドSQL情報バリアント:
    ユーザーID: string
    パケット上限: number
    get(クラウドマスター参照イベント.端末ID):
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