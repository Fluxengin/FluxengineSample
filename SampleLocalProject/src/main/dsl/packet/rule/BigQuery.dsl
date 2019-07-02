import パケットイベント: event/パケットイベント


readBigQuery bq:
    select:
        name<string> : string
        weight<int>: number
        flg<boolean>: bool
        createtime<timestamp>: datetime
        day<date>: date
        time<time>: datetime
    sql: bigquery/hoge.sql
    #params:
    #    weight<int>: パケットイベント.使用量
    watch(パケットイベント):




rule r:
    bq.word_count > 10:
    watch(bq):