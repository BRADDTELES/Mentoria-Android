package com.application.aularealm.model

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Usuario : RealmObject {

    @PrimaryKey
    var id: ObjectId = ObjectId.create()

    var nome = ""
    var idade = 0

}