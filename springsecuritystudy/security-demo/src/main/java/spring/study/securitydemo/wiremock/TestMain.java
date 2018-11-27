package spring.study.securitydemo.wiremock;
/**
 * 用线程对int[] 排序
 */
public class TestMain  implements  Runnable{

    public TestMain(int num) {
        this.num = num;
    }

    private int num;

    public static void main(String[] args) {
        int[] h={451,56,4526,15,854,456};
        for(int i=0;i<h.length;i++){
            TestMain testMain = new TestMain(h[i]);
            Thread thread = new Thread(testMain);
            thread.start();
        }
    }


    @Override
    public void run() {
        try{
            Thread.sleep(num);
            System.out.println(num);
        }catch (Exception e){

        }
    }
}
