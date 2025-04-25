public class Integer1 {
    int value;

    public Integer1(int value){
        this.value = value;
    }

    public byte byteValue(){
        return (byte)value;
    }

    public static int compare(int x, int y){
        if (x > y) {
            return x;
        }else if (y > x) {
            return y;
        }
        return 0;
     }

    public int compareTo(Integer anotherInteger){
        if (value > anotherInteger) {
            return value;
        }else if (anotherInteger > value) {
            return anotherInteger;
        }
        return 0;
     }
    
    public double doubleValue(){
        return (double)value;
     }

    public boolean equals(Object obj){
        if (getClass() != obj.getClass()) {
            return false;
        }else{
            return true;
        }
     }

    public float floatValue(){
        return (float)value;
     }

    public int intValue(){
        return (int)value;
     }

    public long longValue(){
        return (long)value;
     }

    public static int max(int a, int b){
        if (a > b) {
            return a;
        }else if(b > a){
            return b;
        }
        return a;
     }

    public static int min(int a, int b){
        if (a < b) {
            return a;
        }else if(b < a){
            return b;
        }
        return a;
     }

    public static int parseInt(String s){
        int resultado = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            resultado = resultado * 10 + (c - '0');
        }
        return resultado;
     }

    public short shortValue(){
        return (short)value;
     }

    public static int sum(int a, int b){
        return a + b;
     }

    @Override
    public String toString(){
        return toString(value);
     }

    public static String toString(int i){
        return toString(i);
     }

    @SuppressWarnings("removal")
    public static Integer valueOf(int i){
        return new Integer(i);
    }
    
    @SuppressWarnings("removal")
    public static Integer valueOf(String s){
        return new Integer(s);
    }
    

}
