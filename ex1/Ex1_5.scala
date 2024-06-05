package cp.lablessons.trabalho.ex1

object Ex1_5 extends App {

  import java.util.concurrent.atomic.AtomicBoolean

  val lock = new AtomicBoolean(false) // false lock está livre
  val N = 4 

  // Dummy critical 
  def criticalActions(processName: String): Unit = {
    println(s"$processName is executing critical actions")
    Thread.sleep(1000)
  }

  def process(id: Int): Thread = {
    new Thread(() => {
      for (n <- 0 until N) {
        // Non-critical actions
        println(s"Process $id performing non-critical actions in iteration $n")
        Thread.sleep(1000) // Simulate non-critical work
        // Try to acquire the lock
        while (!lock.compareAndSet(false, true)) {
            // Busy-wait until the lock is acquired
        }

        // Critical section
        try {
            println(s"Process $id in critical section iteration number: $n")
            criticalActions(s"Process $id") 
        } 
        finally {
            // Release the lock
            println(s"Process $id done with critical section")
            lock.set(false)
        }
      }
    })
  }

  def main(): Unit = {
    val p0 = process(0)
    val p1 = process(1)

    p0.start()
    p1.start()
    
    p0.join()
    p1.join()
  }

  main()
}