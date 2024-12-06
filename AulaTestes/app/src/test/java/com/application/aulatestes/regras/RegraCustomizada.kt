package com.application.aulatestes.regras

import org.junit.rules.TestWatcher
import org.junit.runner.Description

class RegraCustomizada(val meuLog: String) : TestWatcher() {

    var log: String = meuLog

    override fun starting(description: Description?) {
        super.starting(description)
        println("==starting - $log")
        //Configurar uma Regra, que automaticamente faz uma ação, ex: Salva um LOG
    }

    override fun finished(description: Description?) {
        super.finished(description)
        println("==finished - $log")
        //Configurar uma Regra, que automaticamente faz uma ação, ex: Salva um LOG
    }
}