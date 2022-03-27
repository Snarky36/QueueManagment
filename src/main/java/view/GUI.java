package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.event.ChangeListener;
import java.awt.TextArea;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private JPanel contentPane;
    private JTextField numberQueueText;
    private JTextField numberClientsText;
    private JTextField minArrivalText;
    private JTextField maxArrivalText;
    private JTextField minServiceText;
    private JTextField maxServiceText;
    private JTextField simulationTimeText;
    private JLabel numberQueueLabel;
    private JSlider numberQueueSlider;
    private JLabel numberClientsLabel;
    private JSlider numberClientsSlider;
    private JLabel minArrivalLabel;
    private JSeparator separator;
    private JLabel maxArrivalLabel;
    private JLabel QueueManager;
    private JLabel imageLabel;
    private JLabel timerLabel;
    private JLabel timeLeftLabel;
    private JLabel minServiceLabel;
    private JLabel maxServiceLabel;
    private JLabel simulationTimeLabel;
    private JSlider simulationTimeSlider;
    private JButton startSimulationButton;
    private TextArea textArea;
    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 717, 717);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        numberQueueText = new JTextField();
        numberQueueText.setBounds(338, 78, 86, 18);
        contentPane.add(numberQueueText);
        numberQueueText.setColumns(10);

        numberQueueLabel = new JLabel("Select number of queues:");
        numberQueueLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        numberQueueLabel.setBounds(10, 78, 181, 14);
        contentPane.add(numberQueueLabel);

        numberQueueSlider = new JSlider();
        numberQueueSlider.setValue(2);
        numberQueueSlider.setMinorTickSpacing(1);
        numberQueueSlider.setMinimum(1);
        numberQueueSlider.setBounds(179, 78, 149, 18);
        contentPane.add(numberQueueSlider);

        numberClientsLabel = new JLabel("Select number of clients:");
        numberClientsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        numberClientsLabel.setBounds(10, 118, 181, 14);
        contentPane.add(numberClientsLabel);

        numberClientsSlider = new JSlider();
        numberClientsSlider.setValue(4);
        numberClientsSlider.setMinimum(1);
        numberClientsSlider.setBounds(179, 118, 149, 18);
        contentPane.add(numberClientsSlider);

        numberClientsText = new JTextField();
        numberClientsText.setColumns(10);
        numberClientsText.setBounds(338, 116, 86, 18);
        contentPane.add(numberClientsText);

        minArrivalLabel = new JLabel("Min Arrival:");
        minArrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        minArrivalLabel.setBounds(443, 75, 69, 24);
        contentPane.add(minArrivalLabel);

        separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(436, 57, 11, 176);
        contentPane.add(separator);

        maxArrivalLabel = new JLabel("Max Arrival:");
        maxArrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        maxArrivalLabel.setBounds(443, 114, 69, 24);
        contentPane.add(maxArrivalLabel);

        minArrivalText = new JTextField();
        minArrivalText.setBounds(522, 77, 86, 20);
        contentPane.add(minArrivalText);
        minArrivalText.setColumns(10);

        maxArrivalText = new JTextField();
        maxArrivalText.setColumns(10);
        maxArrivalText.setBounds(522, 116, 86, 20);
        contentPane.add(maxArrivalText);

        QueueManager = new JLabel("Queue Manager");
        QueueManager.setFont(new Font("Times New Roman", Font.BOLD, 24));
        QueueManager.setBounds(252, 21, 181, 24);
        contentPane.add(QueueManager);

        imageLabel = new JLabel("Image");
        imageLabel.setIcon(new ImageIcon("D:\\Facultate\\Tehnici PROGRAMARE\\PT2022_30229_Zaharie_Andrei_Assignment_1\\src\\main\\resources\\Image_10.png"));
        imageLabel.setBounds(10, 0, 133, 46);
        contentPane.add(imageLabel);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 57, 684, 2);
        contentPane.add(separator_1);

        timerLabel = new JLabel("Timer:");
        timerLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        timerLabel.setBounds(551, 12, 57, 18);
        contentPane.add(timerLabel);

        timeLeftLabel = new JLabel("00:15");
        timeLeftLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
        timeLeftLabel.setBounds(608, 14, 46, 14);
        contentPane.add(timeLeftLabel);

        minServiceLabel = new JLabel("Min Service:");
        minServiceLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        minServiceLabel.setBounds(443, 159, 69, 24);
        contentPane.add(minServiceLabel);

        minServiceText = new JTextField();
        minServiceText.setColumns(10);
        minServiceText.setBounds(522, 161, 86, 20);
        contentPane.add(minServiceText);

        maxServiceLabel = new JLabel("Max Service:");
        maxServiceLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        maxServiceLabel.setBounds(443, 191, 78, 24);
        contentPane.add(maxServiceLabel);

        maxServiceText = new JTextField();
        maxServiceText.setColumns(10);
        maxServiceText.setBounds(522, 193, 86, 20);
        contentPane.add(maxServiceText);

        simulationTimeLabel = new JLabel("Select simulation Time:");
        simulationTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        simulationTimeLabel.setBounds(10, 159, 159, 24);
        contentPane.add(simulationTimeLabel);

        simulationTimeSlider = new JSlider();
        simulationTimeSlider.setValue(10);
        simulationTimeSlider.setMinimum(1);
        simulationTimeSlider.setMaximum(60);
        simulationTimeSlider.setBounds(179, 165, 149, 18);
        contentPane.add(simulationTimeSlider);

        simulationTimeText = new JTextField();
        simulationTimeText.setColumns(10);
        simulationTimeText.setBounds(338, 163, 86, 18);
        contentPane.add(simulationTimeText);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(10, 231, 684, 2);
        contentPane.add(separator_2);

        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        startSimulationButton.setBounds(86, 192, 265, 23);
        contentPane.add(startSimulationButton);

        textArea = new TextArea();
        textArea.setBounds(10, 239, 684, 429);
        contentPane.add(textArea);
    }
    public int getNumberClientsText(){
        int nrClients = Integer.parseInt(this.numberClientsText.getText());
        return nrClients;
    }
    public int getNumberQueueText(){
        int nrQueues = Integer.parseInt(this.numberQueueText.getText());
        return nrQueues;
    }

    public void getQueueSlider(){
        this.numberQueueText.setText(""+this.numberQueueSlider.getValue());
    }
    public void getTimeSlider(){
        this.simulationTimeText.setText(""+this.simulationTimeSlider.getValue());
    }
    public void getClientsSlider(){
        this.numberClientsText.setText(""+ this.numberClientsSlider.getValue());
    }
    public void setTimerLabel(String time){
        this.timeLeftLabel.setText(time);
    }
    public int getMinArrivalText(){
        return Integer.parseInt(this.minArrivalText.getText());
    }
    public int getMaxArrivalText(){
        return Integer.parseInt(this.maxArrivalText.getText());
    }
    public int getMinServiceText(){
        return Integer.parseInt(this.minServiceText.getText());
    }
    public int getMaxServiceText(){
        return Integer.parseInt(this.maxServiceText.getText());
    }
    public void setTimeLeftLabel(String timeLeft){
        this.timeLeftLabel.setText(timeLeft);
    };
    public void setTextArea(String text){
        this.textArea.setText(text);
    }
    public void addToTextArea(String text){
        this.textArea.setText(this.textArea.getText() + text);
    }

    public int getSimulationTimeText() {
        return Integer.parseInt(simulationTimeText.getText());
    }

    public void startSimulationButtonListener(ActionListener actionListener){
    this.startSimulationButton.addActionListener(actionListener);
    }
    public void numberQueueSliderListener(ChangeListener changeListener){
        this.numberQueueSlider.addChangeListener(changeListener);
    }
    public void NumberClientsSliderListener(ChangeListener changeListener){
        this.numberClientsSlider.addChangeListener(changeListener);
    }

    public void simulationTimeSliderListener(ChangeListener changeListener){
        this.simulationTimeSlider.addChangeListener(changeListener);
    }


}
