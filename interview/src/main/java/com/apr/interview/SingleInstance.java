package com.apr.interview;



public class SingleInstance {
    private static SingleInstance instance;


    //lazy mode
    public static SingleInstance getInstance() {
        if (instance == null){
            synchronized (SingleInstance.class){
                if (instance == null){
                    instance = new SingleInstance();

                }
            }
        }
        return instance;
    }

    //hungry mode
    static SingleInstance hungryInstance = new SingleInstance();

    public static SingleInstance getDefault(){
        return hungryInstance;
    }

    //静态内部类
    public static SingleInstance getDef(){
        return InnerSingleInstance.def;
    }

    private static class InnerSingleInstance{
        private static SingleInstance def = new SingleInstance();
    }



}

