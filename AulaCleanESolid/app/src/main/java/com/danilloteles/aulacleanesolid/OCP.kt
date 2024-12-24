package com.danilloteles.aulacleanesolid

interface Contrato {
    fun calcularSalario() : Double
}

class ContratoFlex : Contrato {
    //Atributos diferentes
    fun calcularSalarioFlex() : Double {
        // salario - desconto + imposto -> CÁLCULO
        return 2100.0
    }

    override fun calcularSalario(): Double {
        return calcularSalarioFlex()
    }
}

class ContratoPJ : Contrato {
    //Atributos diferentes
    fun calcularSalarioPJ() : Double {
        // salario - porcentagem de desconto será imposto -> CÁLCULO
        return 2000.0
    }

    override fun calcularSalario(): Double {
        return calcularSalarioPJ()
    }
}

class ContratoCLT : Contrato {
    //Atributos diferentes
    fun calcularSalarioCLT() : Double {
        // salario - porcentagem de desconto -> CÁLCULO 
        return 1300.0
    }

    override fun calcularSalario(): Double {
        return calcularSalarioCLT()
    }
}

class ContratoEstagio : Contrato {
    //Atributos diferentes
    fun calcularSalarioEstagio() : Double {
        // salario sem desconto -> CÁLCULO 
        return 600.0
    }

    override fun calcularSalario(): Double {
        return calcularSalarioEstagio()
    }
}

class FolhaPagamento(
    private val contrato: Contrato
) {
    
    fun calcularSalarioFuncionario() : Double {
        /*if ( contrato is ContratoCLT ) {
            return contrato.calcularSalarioCLT()
        }else if ( contrato is ContratoEstagio ) {
            return contrato.calcularSalarioEstagio()
        }*/
        return contrato.calcularSalario()
    }
    
}

fun main() {
    
    val contratoCLT = ContratoCLT()//Contrato e ContratoCLT
    val contratoEstagio = ContratoEstagio()//Contrato e ContratoEstagio
    val contratoPJ = ContratoPJ()//Contrato e ContratoPJ
    val contratoFlex = ContratoFlex()//Contrato e ContratoPJ

    val folhaPagamento = FolhaPagamento( contratoFlex )
    
    val calculo = folhaPagamento.calcularSalarioFuncionario()
    println("Salário: $calculo")

}