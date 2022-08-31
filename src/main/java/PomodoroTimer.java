import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PomodoroTimer {


    public static int test=0;
    public static void main(String[] args) throws InterruptedException{

        String[] cmd = new Scanner(System.in).nextLine().split(" ");

        int work = 50;
        int breake = 10;
        int sizebreak = 30;
        int sizework = 30;
        int help = 0;
        int count = 1;
        int multiplier = 1;

        for(int i=0; i < cmd.length;i++){
            switch (cmd[i]) {
                case "--help" -> {
                    System.out.println(
                            "\n\nPomodoro - ñäåëàé ñâîå âðåìÿ áîëåå ýôôåêòèâíûì\n");
                    System.out.println(
                            "	-w <time>: âðåìÿ ðàáîòû, ñêîëüêî õî÷åøü ðàáîòàòü.\n");
                    System.out.println(
                            "	-b <time>: âðåìÿ îòäûõà, ñêîëüêî õî÷åøü îòäûõàòü.\n");
                    System.out.println(
                            "	-count <count>: êîëè÷åñòâî èòåðàöèé.\n");
                    System.out.println(
                            "	-m <multiplier>: êîëè÷åñòâî èòåðàöèé.\n");
                    System.out.println(
                            "	--help: ìåíþ ïîìîùè.\n");
                    help = 1;
                }
                case "-w" -> work = Integer.parseInt(cmd[++i]);
                case "-b" -> breake = Integer.parseInt(cmd[++i]);
                case "-count" -> count = Integer.parseInt(cmd[++i]);
                case "-m" -> multiplier = Integer.parseInt(cmd[++i]);
            }
        }
        if(help == 0){
            long startTime = System.currentTimeMillis();
            for (int i = 1; i <= count; i++) {
                timer(work, breake, sizebreak, sizework);
                work *= multiplier;
                breake *= multiplier;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Pomodor òàéìåð èñòåê: " + (endTime-startTime)/(1000 * 60)+ " min");
        }

    }

    public static void timer(int work, int breake, int sizebreak, int sizework) throws InterruptedException{

        printProgress("Work Progress::  ", work, sizework);

        printProgress("Break Progress:: ", breake, sizebreak);
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60 * time / size;
        rep = 60 * time / length;
        int stretchb = size / (3 * time);
        for(int i=1; i <= rep; i++){
            double x = i;
            x = 1.0/3.0 *x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretchb;
            double percent = (x/w) * 1000;
            x /=stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent+"% " + (" ").repeat(5 - (String.valueOf(percent).length())) +"[" + ("#").repeat(i) + ("-").repeat(rep - i)+"]    ( " + x +"min / " + time +"min )"+  "\r");
            if(test == 0){
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }
}
