package cn.spacexc.memodays.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.spacexc.memodays.model.MemoDay
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val realm = Realm.open(RealmConfiguration.create(schema = setOf(MemoDay::class)))
    val memoDays = realm.query(MemoDay::class).find().asFlow().map {
        it.list.toList()
    }

    fun addDay(day: MemoDay) {
        viewModelScope.launch {
            realm.write {
                copyToRealm(day)
            }
        }
    }
}