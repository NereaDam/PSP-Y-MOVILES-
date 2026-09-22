
    void main()  {

        Semaphore semaphore = new Semaphore(3);

        Runnable tareaCoche = () -> {

            try {
                semaphore.acquire();
                System.out.println("Consigo plaza");
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("aparcado");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            semaphore.release();
            System.out.println("Me voy");

        };

        for (int i = 0; i < 5; i++) {
            Thread coche = new Thread(tareaCoche);
            coche.start();

        }

    }