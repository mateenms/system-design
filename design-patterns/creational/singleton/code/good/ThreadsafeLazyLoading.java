package patterns.singleton.good;

public class ThreadsafeLazyLoading {


    private static ThreadsafeLazyLoading instance = null;
    private  ThreadsafeLazyLoading(){

    }

    public  static ThreadsafeLazyLoading getInstance(){

        if(instance==null){
            synchronized (ThreadsafeLazyLoading.class){
                if (instance==null){
                    instance = new ThreadsafeLazyLoading();
                }
            }
        }

        return instance;
    }
}
