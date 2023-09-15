package lab1.B;

import javax.swing.*;

class MyThread extends Thread {
    private int increment;
    private JSlider slider;

    public MyThread(JSlider slider, int increment, int priority) {
        this.slider = slider;
        this.increment = increment;
        setPriority(1);
    }

    @Override
    public void run() {
        while(!interrupted()){
            synchronized (slider){
                if (increment == 10 && slider.getValue() != 10)
                    slider.setValue(slider.getValue() - 1);
                if (increment == 90 && slider.getValue() != 90)
                    slider.setValue(slider.getValue() + 1);
            }
            try{
                sleep(10);
            }
            catch (InterruptedException e){
                System.out.println(getName());
                this.interrupt();
            }
        }
    }
}

public class Main {
    static volatile int semaphore = 0;
    static volatile JSlider slider;
    static JButton startButton1;
    static JButton startButton2;
    static JButton stopButton1;
    static JButton stopButton2;
    static MyThread thread1;
    static MyThread thread2;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        slider = new JSlider();
        slider.setBounds(20, 60, 460, 50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        frame.add(slider);

        startButton1 = new JButton("Start first thread");
        startButton1.setBounds(50, 200, 150, 50);
        frame.add(startButton1);
        startButton1.addActionListener(e -> {
            if (semaphore == 1){
                JOptionPane.showMessageDialog(frame, "Another thread is running currently!");
            } else {
                thread1 = new MyThread(slider, 10, 1);
                semaphore = 1;
                thread1.start();
                startButton1.setEnabled(false);
                stopButton1.setEnabled(true);
            }
        });

        startButton2 = new JButton("Start second thread");
        startButton2.setBounds(250, 200, 150, 50);
        frame.add(startButton2);
        startButton2.addActionListener(e -> {
            if (semaphore == 1){
                JOptionPane.showMessageDialog(frame, "Another thread is running currently!");
            } else {
                thread2 = new MyThread(slider, 90, 10);
                semaphore = 1;
                thread2.start();
                startButton2.setEnabled(false);
                stopButton2.setEnabled(true);
            }
        });

        stopButton1 = new JButton("Stop first thread");
        stopButton1.setBounds(50,300, 150, 50);
        stopButton1.setEnabled(false);
        frame.add(stopButton1);
        stopButton1.addActionListener(e -> {
            semaphore = 0;
            thread1.interrupt();
            startButton1.setEnabled(true);
            stopButton1.setEnabled(false);
        });

        stopButton2 = new JButton("Stop second thread");
        stopButton2.setBounds(250, 300, 150, 50);
        stopButton2.setEnabled(false);
        frame.add(stopButton2);
        stopButton2.addActionListener(e -> {
            semaphore = 0;
            thread2.interrupt();
            startButton2.setEnabled(true);
            stopButton2.setEnabled(false);
        });

        frame.setVisible(true);
    }
}