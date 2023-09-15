package lab1.A;

import javax.swing.*;

class MyThread extends Thread {
    private int increment;
    private JSlider slider;
    private int count;
    private static int brick = 1000000;

    public MyThread(JSlider slider, int increment) {
        this.slider = slider;
        this.increment = increment;
        setPriority(1);
    }

    @Override
    public void run() {
        while(!interrupted()){
            synchronized (slider){
                ++count;
                if(count > brick){
                    slider.setValue((int)slider.getValue() + increment);
                    count = 0;
            }
        }
        }
    }
}

class MySpinner extends JSpinner {

    public MySpinner(SpinnerModel spinnerModel, int x, int y) {
        super(spinnerModel);
        this.setBounds(x, y, 100, 70);
        this.setValue(1);
    }

    public void changeEvent(MyThread thread) {
        this.addChangeListener(e -> {
            int changedValue = (int) this.getValue();
            thread.setPriority(changedValue);
        });
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Task A");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(null);

        // adding slider
        JSlider slider = new JSlider();
        slider.setBounds(50, 60, 300, 50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        frame.add(slider);

        // adding button
        JButton btn = new JButton("Start");
        btn.setBounds(100, 300, 200, 50);
        frame.add(btn);

        // adding Thread Priority spinenrs
        SpinnerModel sm1 = new SpinnerNumberModel(1, 1, 10, 1);
        SpinnerModel sm2 = new SpinnerNumberModel(1, 1, 10, 1);
        MySpinner spinner1 = new MySpinner(sm1, 70, 180);
        MySpinner spinner2 = new MySpinner(sm2, 230, 180);
        frame.add(spinner1);
        frame.add(spinner2);

        // Threads
        MyThread thread1 = new MyThread(slider, +1);
        MyThread thread2 = new MyThread(slider, -1);

        // Button start
        btn.addActionListener(e -> {
            thread1.start();
            thread2.start();
            btn.setEnabled(false);
        });

        // Thread Priority changes
        spinner1.changeEvent(thread1);
        spinner2.changeEvent(thread2);

        frame.setVisible(true);
    }
}