package cp.lablessons.trabalho.ex1

// Peterson's algorithm is a concurrent programming
// algorithm for mutual exclusion that allows two
// processes to share a single-use resource without
// conflict, using only shared memory for communication.
// It was formulated by Gary L. Peterson in 1981.

// Two processes use a respective "flag" to
// indicate that they want to enter a critical
// section. However using just that could lead to
// a deadlock. So they use a tie-breaker "turn" to
// indicate whose turn it is to wait. So, each
// process says it wants to enter CS but also that
// it is its turn to wait. In the end, a process
// only waits if the other process wants to enter
// CS as well as it is its own turn to wait. This
// tie-breaker prevents deadlock.

object Ex1_4 extends App {

    import java.time.LocalDateTime

    @volatile var flags: Array[Boolean] = Array(false, false)
    @volatile var turn: Int = 0
    var N = 4 
    var lock = new Object() 

    def nonCriticalActions(process: String): Unit = {
        println(s"P$process: Non-critical actions")
        // println(s"${LocalDateTime.now()}: $process")
        Thread.sleep(1000) 
    }

    def criticalActions(process: String): Unit = {
        println(s"$process: In critical section!")
        // println(s"${LocalDateTime.now()}: $process")
        Thread.sleep(1000) 
        println(s"$process: Exiting critical section!")
    }

    def process (i: Int) : Thread = {
        new Thread(() => {
            val j = 1 - i
            for (n <- 0 until N) {
                flags(i) = true
                turn = j;       
                while (flags(j) && turn == j) {}
                // critical section, busy wait
                lock.synchronized { 
                    println(s"Process $i entering in CS iteration number: $n")
                    criticalActions(s"Process $i") 
                    println(s"Process $i done CS") // unlock
                }
                flags(i) = false
            }
        })
    }

    def main(): Unit = {
        val p1 = process(1)
        val p0 = process(0)

        p0.start()
        p1.start()

        p0.join()
        p1.join()
    }
    main()
}