package cp.lablessons.trabalho.ex2

// exercicio 2.3
object Ex2_3 extends App {
    // @volatile garante que as variáveis sejam visíveis a todos os threads de forma consistente
    @volatile var t: Int = 0
    @volatile var ka: Int = 1
    @volatile var kb: Int = 2
    @volatile var kc: Int = 3
    @volatile var kd: Int = 4
    @volatile var ke: Int = 5
    @volatile var fome1: Boolean = false
    @volatile var fome2: Boolean = false
    @volatile var fome3: Boolean = false
    @volatile var fome4: Boolean = false
    @volatile var fome5: Boolean = false


    // Função que simula ações não críticas
    def conversation(process: String): Unit = {
        println(s"$process: conversation actions")
        Thread.sleep(1000) // Simula ações não críticas com sleep
    }

    // Função que simula ações críticas
    def eat(process: String): Unit = {
        println(s"$process: eating..")
        Thread.sleep(1000) // Simula ações críticas com sleep
    }

    class P1 extends Thread { // First, we need to create a Thread object to allocate the memory for the stack and thread state
        override def run () : Unit = {
            while (true) {
                conversation("P1")
                while (t != 0){
                    // skip
                }
                t = 1;
                println("P1: grab_sushi_tray")
                fome1 = true
                while (ka != 1) {
                    while (fome2 == true) {
                        // skip
                    }
                    ka = 1
                }
                //grab_stick_left
                while (ke != 1) {
                    while (fome5 == true) {
                        // skip
                    }
                    ke = 1
                }
                //grab_stick_right
                println("P1: return_sushi_tray")
                t = 0;
                eat("P1")
                fome1 = false
            }
        }
    }

    class P2 extends Thread { // First, we need to create a Thread object to allocate the memory for the stack and thread state
        override def run () : Unit = {
            while (true) {
                conversation("P2")
                while (t != 0){
                    // skip
                }
                t = 1;
                println("P2: grab_sushi_tray")
                fome2 = true
                while (kb != 1) {
                    while (fome3 == true) {
                        // skip
                    }
                    kb = 1
                }
                //grab_stick_left
                while (ka != 1) {
                    while (fome1 == true) {
                        // skip
                    }
                    ka = 1
                }
                //grab_stick_right
                println("P2: return_sushi_tray")
                t = 0;
                eat("P2")
                fome2 = false
            }
        }
    }

    class P3 extends Thread { // First, we need to create a Thread object to allocate the memory for the stack and thread state
        override def run () : Unit = {
            while (true) {
                conversation("P3")
                while (t != 0){
                    // skip
                }
                t = 1;
                println("P3: grab_sushi_tray")
                fome3 = true
                while (kc != 1) {
                    while (fome4 == true) {
                        // skip
                    }
                    kc = 1
                }
                //grab_stick_left
                while (kb != 1) {
                    while (fome2 == true) {
                        // skip
                    }
                    kb = 1
                }
                //grab_stick_right
                println("P3: return_sushi_tray")
                t = 0;
                eat("P3")
                fome3 = false
            }
        }
    }

    class P4 extends Thread { // First, we need to create a Thread object to allocate the memory for the stack and thread state
        override def run () : Unit = {
            while (true) {
                conversation("P4")
                while (t != 0){
                    // skip
                }
                t = 1;
                println("P4: grab_sushi_tray")
                fome4 = true
                while (kd != 1) {
                    while (fome5 == true) {
                        // skip
                    }
                    kd = 1
                }
                //grab_stick_left
                while (kc != 1) {
                    while (fome3 == true) {
                        // skip
                    }
                    kc = 1
                }
                //grab_stick_right
                println("P4: return_sushi_tray")
                t = 0;
                eat("P4")
                fome4 = false
            }
        }
    }

    class P5 extends Thread { // First, we need to create a Thread object to allocate the memory for the stack and thread state
        override def run () : Unit = {
            while (true) {
                conversation("P5")
                while (t != 0){
                    // skip
                }
                t = 1;
                println("P5: grab_sushi_tray")
                fome5 = true
                while (ke != 1) {
                    while (fome1 == true) {
                        // skip
                    }
                    ke = 1
                }
                //grab_stick_left
                while (kd != 1) {
                    while (fome4 == true) {
                        // skip
                    }
                    kd = 1
                }
                //grab_stick_right
                println("P5: return_sushi_tray")
                t = 0;
                eat("P5")
                fome5 = false
            }
        }
    }
    // Cria e inicia os threads para P1 e P2
    val p1Thread = new P1()
    val p2Thread = new P2()
    val p3Thread = new P3()
    val p4Thread = new P4()
    val p5Thread = new P5()

    p1Thread.start()
    p2Thread.start()
    p3Thread.start()
    p4Thread.start()
    p5Thread.start()

    // Junta os threads (espera que terminem, o que não acontecerá neste exemplo de loop infinito)
    p1Thread.join()
    p2Thread.join()
    p3Thread.join()
    p4Thread.join()
    p5Thread.join()

}