/*
Abstração
Herança
Poli -> muitas  - morfismo -> formas
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
        System.out.println("usuário cadastrado email: " + email + " senha: " + senha );
    }

    //Assinatura de métodos: nome + parametros e tipo
    void logar(){
        System.out.println("logar usuário email: " + email + " senha: " + senha);
    }

    void logar( String telefone ){//Sobrecargas de métodos
        System.out.println("Envia SMS, confirma SMS aí sim logado com telefone: " + telefone);
    }

    void logar( int token ){//Sobrecargas de métodos
        System.out.println("logar usuário por token: " + token);
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

class Animal {//Super classe, classe pai

    String nome;
    String cor;

    void correr(){
        System.out.println("Correr");
    }

}

class Cachorro extends Animal {//Filha, subclasse
    public void latir(){
        correr();
        System.out.println("latir");
    }

}

class Passaro extends Animal {

    public void voar(){
        System.out.println("Voar");
    }

}

class Scratch {
    public static void main(String[] args) {

        Cachorro cachorro = new Cachorro();
        cachorro.latir();
        cachorro.correr();

        Passaro passaro = new Passaro();
        passaro.voar();

        //Instaciar na tela de cadastro
        /*Usuario usuario = new Usuario("danillo@gmail.com", "1234");//Instanciando, construindo o objeto
        usuario.logar();
        usuario.logar("1199999999999");
        usuario.logar(1234456789);
        //usuario.cadastrar();*/

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