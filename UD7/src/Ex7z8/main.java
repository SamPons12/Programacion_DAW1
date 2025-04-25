public class main {
    @SuppressWarnings("removal")
    public static void main(String[] args) {

        Integer1 value = new Integer1(6);
        Integer1 obj = new Integer1(6);
        System.out.println(value.byteValue());
        System.out.println(Integer1.compare(5, 6));
        System.out.println(value.compareTo(new Integer(6)));
        System.out.println(value.doubleValue());
        System.out.println(value.floatValue());
        System.out.println(value.intValue());
        System.out.println(value.longValue());
        System.out.println(value.equals(obj));
        System.out.println(Integer1.max(5, 6));
        System.out.println(Integer1.min(5, 6));
        System.out.println(value.shortValue());
        System.out.println(Integer1.sum(5, 6));
        System.out.println(Integer1.parseInt("45"));
        System.out.println(Integer1.valueOf(5));
        System.out.println(Integer1.valueOf(6));







        







    }

}
