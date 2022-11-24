export persister パケット積算データ<ユーザーID>:
    使用量: number
    persist(ユーザーID):
        lifetime: today()

#お客さんがmysql利用しているため、mysql検証のため
struct xx:
    name: string
    パケットイベント.端末ID == "tid112233":
        sql: mysql/read2.sql
        params:
            corpus<varchar>: イベントのみ.端末ID
    else:
        sql: mysql/read2.sql
        params:
            corpus<varchar>: イベントのみ.端末ID
    cache: today()


rule mysqlrule:
    xx.name == パケットイベント.端末ID:
    watch(パケットイベント):
