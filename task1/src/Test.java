public class Test {
    @MyAnnotation(a=2, b=5)
    public void calc(int param1, int param2){
        int sum = param1+param2;
        System.out.println(sum);

      }


}
