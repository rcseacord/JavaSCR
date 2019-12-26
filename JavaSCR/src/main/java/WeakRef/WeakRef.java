package WeakRef;

import java.lang.ref.WeakReference;

public class WeakRef {

  class Engine {
    int count=0;
    public void run()
    {
      System.out.print("\r");
      System.out.print("Engine "+count++);
    }
  }

  Engine EngineStrong;

  public static void main(String[] args) {

    StackTraceElement[] stack = Thread.currentThread ().getStackTrace ();
    StackTraceElement main = stack[stack.length - 1];
    String mainClass = main.getClassName ();
    int mode=0;

    if (args.length !=1)
    {
      System.out.println(mainClass+": Missing Argument  noref | local | class | classgc");
      System.exit(0);
    }

    else if (args[0].equals("noref")) mode=1;
    else if (args[0].equals("local")) mode=2;
    else if (args[0].equals("class")) mode=3;
    else if (args[0].equals("classgc")) mode=4;
    else
    {
      System.out.println(mainClass+": Invalid argument "+args[0]+" must be either  noref | local | class | classgc");
      System.exit(0);
    }

    WeakRef demo=new WeakRef();
    demo.MainRun(mode);
  }

  public void MainRun(int mode)
  {
    Engine EngineLocal;
    WeakReference<Engine> EngineWeak=null;
    // EngineLocal=new Engine();
    if (mode==2)
    {
      EngineLocal=new Engine();
      EngineWeak=new WeakReference<Engine> (EngineLocal);
      System.out.println("Mode local ref");
      System.out.println();
    }

    while(mode!=7)
    {
      Runtime.getRuntime().gc();
      EngineWeak.get().run();
    }


  }

}

/*


import java.lang.ref.WeakReference;

public class DemoWeak {

  class Engine {
    int count=0;
    public void run()
    {
     // System.out.print("\33[1A\33[2K");
      System.out.printf("\r");
      System.out.println("Engine "+count++);
    }
  }

  Engine EngineStrong;

  public static void main(String[] args) {

    StackTraceElement[] stack = Thread.currentThread ().getStackTrace ();
    StackTraceElement main = stack[stack.length - 1];
    String mainClass = main.getClassName ();
    int mode=0;

    if (args.length !=1)
    {
      System.out.println(mainClass+": Missing Argument  noref | local | class | classgc");
      System.exit(0);
    }

    else if (args[0].equals("noref")) mode=1;
    else if (args[0].equals("local")) mode=2;
    else if (args[0].equals("class")) mode=3;
    else if (args[0].equals("classgc")) mode=4;
    else
    {
      System.out.println(mainClass+": Invalid argument "+args[0]+" must be either  noref | local | class | classgc");
      System.exit(0);
    }

    DemoWeak demo=new DemoWeak();
    demo.MainRun(mode);
  }

  public void MainRun(int mode)
  {
    Engine EngineLocal;
    WeakReference<Engine> EngineWeak=null;
    if (mode==1)
    {
      EngineWeak=new WeakReference<Engine> (new Engine());
      System.out.println("Mode noref");
    }
    else if (mode==2)
    {
      EngineLocal=new Engine();
      EngineWeak=new WeakReference<Engine> (EngineLocal);
      System.out.println("Mode local ref");
    }
    else if (mode==3)
    {
      EngineStrong=new Engine();
      EngineWeak=new WeakReference<Engine> (EngineStrong);
      System.out.println("Mode class strong ref");
    }
    else if (mode==4)
    {
      EngineStrong=new Engine();
      EngineWeak=new WeakReference<Engine> (EngineStrong);
      System.out.println("Mode class strong ref with gc");
    }
    else
    {
      System.out.println("Invalid Mode");
      System.exit(0);
    }
    System.out.println();
    while(true)
    {
      if (mode==4) Runtime.getRuntime().gc();
      EngineWeak.get().run();
    }
  }

}

*/
