/*
Abstração
Herança
Polimorfismo
Encapsulamento
*/

class Usuario {

    //Atributos
    String nome;
    String email;
    String usuario;
    String senha;

    //Métodos = "Com retorno" e "Sem retorno"
    void cadastrar(){
        System.out.println("usuário cadastrado");
    }

    void logar(){
        System.out.println("usuário logado");
    }

    /*void calcularIMC(double peso, double altura){
        double imc = peso / (altura * altura);
        System.out.println("imc: " + imc);
    }*/

    double calcularIMC(double peso, double altura){
        double imc = peso / (altura * altura);
        return imc;
    }
}

class Scratch {
    public static void main(String[] args) {

        //Instaciar na tela de cadastro
        Usuario usuario = new Usuario();
        //usuario.calcularIMC(70, 1.72);
        double imcComRetorno = usuario.calcularIMC(70, 1.72);
        System.out.println("imc: " + imcComRetorno);

        /*//Variáveis de instância
        String nome = "danillo";
        usuario.email = "danillo@gmail.com";
        usuario.senha = "1234";
        usuario.cadastrar();

        //Instanciar na tela de login
        Usuario usuarioLogin = new Usuario();

        //Variáveis de instância
        usuario.email = "danillo@gmail.com";
        usuario.senha = "1234";
        usuario.logar();*/

    }
}