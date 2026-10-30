package patterns.singleton.good;

public class LoggerLazzy {

    private static  LoggerLazzy instance = null;
    private LoggerLazzy(){
    }

    public static  LoggerLazzy getInstance(){
        if(instance==null){
            instance= new LoggerLazzy();
        }
        return  instance;
    }
}
