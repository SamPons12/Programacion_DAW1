package Project6;

public abstract class SerVivo {
    protected int años;
    protected double estatura;

    public SerVivo(int años, double estatura){
        this.años = años;
        this.estatura = estatura;
    }

    @Override
    public String toString(){
        String s = "Ser vivo con" + años + "años y mide" + estatura;
        return s;
    }

}
