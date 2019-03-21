// The MIT License (MIT)
//
// Copyright (c) 2018 Robert C. Seacord
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//

package exp06j;

import java.util.concurrent.ThreadLocalRandom;

class ReadCommand implements Command {
  @Override
  public void execute() {
    System.out.println("Read");
  }
}
class WriteCommand implements Command {
  @Override
  public void execute() {
    System.out.println("Write");
  }
}

// Sleeps when executed
class SleepCommand implements Command {
  @Override
  public void execute() throws InterruptedException {
    System.out.println("Sleep");
    Thread.sleep(1000);
  }
}

public class CommandFactory {
  public Command getCommand() {
    if (ThreadLocalRandom.current().nextBoolean()) {
      return new ReadCommand();
    }
    else if (ThreadLocalRandom.current().nextBoolean()) {
      return new WriteCommand();
    }
    // special case:  no command ready to be completed
    else {
      return null;
      // return new SleepCommand();
    }
  }
} // end class CommandFactory

class Program {
  public static void main(String[] args) throws InterruptedException {
    CommandFactory factory = new CommandFactory();
    //noinspection InfiniteLoopStatement
    while (true) {
      Command command = factory.getCommand();
      // Since the CommandFactory can return null commands,
      // clients are obligated to check if the command received
      // is null and if it is, sleep for 1 second.
      if (command != null) {
        command.execute();
      }
      else {
        System.out.println("Sleep");
        Thread.sleep(1000);
      }
    }
  }
}