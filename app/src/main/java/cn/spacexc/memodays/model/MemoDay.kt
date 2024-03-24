package cn.spacexc.memodays.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class MemoDay : RealmObject {
    /*enum class MemoDayType(val index: Int) {
        COUNT_UP(0),
        COUNT_DOWN(1),
        COUNT_BETWEEN(2)
    }*/


    @PrimaryKey
    var id = ObjectId()
    var name = ""
    var targetTime: Long = System.currentTimeMillis()
    var createdAt = 0L
    /*private var typeName: String = MemoDayType.COUNT_UP.name
    private var type: MemoDayType
        get() = MemoDayType.valueOf(typeName)
        set(value) {
            typeName = value.name
        }*/
    var color: String = "#FFA64E"


    constructor(
        name: String,
        targetTime: Long,
        //type: MemoDayType,
        color: String = "#FFA64E"
    ): this() {
        this.name = name
        this.targetTime = targetTime
        //this.type = type
        this.color = color
    }

    constructor()
}