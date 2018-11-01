import java.util.Arrays;

public class Test {



        public static void main(String[] args) {

            //源数组
            int[] arr = {10,30,40,20,80};
            //调用复制方法
            int[] newArr = arrcopy(arr,10);
            System.out.println(Arrays.toString(newArr));
        }
        public static int[] arrcopy(int[] original, int newLength ){
            int [] newArr = new int[newLength];
            System.arraycopy(original, 0,newArr, 0, Math.min(original.length,newLength));
            return newArr;

        }

}
