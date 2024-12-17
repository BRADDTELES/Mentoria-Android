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

    //Construtor
    //Usuario(){} //Construtor Vazio
    Usuario( String email, String senha ){
        this.email = email;
        this.senha = senha;
    }

    //Métodos = "Com retorno" e "Sem retorno"
    void cadastrar(){
        System.out.println("usuário cadastrado: " + email + " senha: ");
    }

    void logar(){
        System.out.println("usuário logado");
    }

    /*void calcularIMC(double peso, double altura){
        double imc = peso / (altura * altura);
        System.out.println("imc: " + imc);
    }*/

    double calcularIMC(double peso, double altura){
        /* Pode se ter dentro de métodos com retorno, por exemplo:
        Listar usuário,
        pode ter if/else, pode ter o for, pode ter try/catch,
        pode ter um método que retorna um boolean, uma String, um Double e assim por diante...
        */
        double imc = peso / (altura * altura);
        return imc;
    }
}

class Scratch {
    public static void main(String[] args) {

        //Instaciar na tela de cadastro
        Usuario usuario = new Usuario("danillo@gmail.com", "1234");//Instanciando, construindo o objeto
        usuario.cadastrar();

        /*usuario.calcularIMC(70, 1.72);
        double imcComRetorno = usuario.calcularIMC(70, 1.72);
        System.out.println("imc: " + imcComRetorno);*/

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