package cp.lablessons.trabalho.ex1

object Hyman extends App {
    
    import java.time.LocalDateTime

    @volatile var k: Int = 1
    @volatile var b1: Boolean = false
    @volatile var b2: Boolean = false

    def nonCriticalActions(process: String): Unit = {
        println(s"P$process: Non-critical actions")
        // println(s"${LocalDateTime.now()}: $process")
        Thread.sleep(1000) 
    }

    def criticalActions(process: String): Unit = {
        println(s"P$process: Entering critical section!")
        // println(s"${LocalDateTime.now()}: $process")
        Thread.sleep(1000) 
        println(s"P$process: Exiting critical section!")
    }

    def process (id: Int) : Thread = {
        new Thread(() => {
            while (true) {
                // Non critical actions
                nonCriticalActions(s"$id")
                if (id == 1) b1 = true else b2 = true
                while (k != id) {
                    while ((if (id == 1) b2 else b1) == true) {
                        // skip
                    }
                    k = id
                }
                // Critical actions
                criticalActions(s"$id") 
                if (id == 1) b1 = false else b2 = false
            }
        })
    }
    
    def main(): Unit = {
        val p1 = process(1)
        val p2 = process(2)

        p1.start()
        p2.start()

        p1.join()
        p2.join()
    }

    main()
}