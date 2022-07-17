import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;


public class WriterGUI extends JFrame implements WindowListener,ActionListener {
    private JButton[][] buttons = new JButton[48][54];
    private JButton currButton;
    private static ArrayList<Color> colors = new ArrayList<>(Arrays.asList(
            Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE
    ));
    private TextField field = new TextField(70);
    public static void main(String[] args){
        JFrame frame= new WriterGUI();
        frame.setSize(900, 900);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public WriterGUI() {
        addWindowListener(this);
        JPanel textPanel = new JPanel(new FlowLayout());
        textPanel.add(field);
        JPanel gridPanel = new JPanel(new GridLayout(48, 54));
        JPanel saveButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton save = new JButton("Save");
        save.addActionListener(this);
        saveButton.add(save);
        CreateButtons();
        for (int i = 0; i < 48; i++) {
            for (int j = 0; j < 54; j++) {
                buttons[i][j].addActionListener(this);
                gridPanel.add(buttons[i][j]);
            }
        }
        JPanel panelbrdLayout = new JPanel(new BorderLayout());
        panelbrdLayout.add(textPanel, BorderLayout.NORTH);
        panelbrdLayout.add(gridPanel, BorderLayout.CENTER);
        panelbrdLayout.add(saveButton, BorderLayout.SOUTH);
        add(panelbrdLayout);
    }

    public void CreateButtons(){
        for (int i = 0; i < 48; i++){
            for (int j = 0; j < 54; j++){
                this.currButton = new JButton("");
                currButton.setBackground(Color.BLACK);
                buttons[i][j] = currButton;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currButton = (JButton) e.getSource();
        if (currButton.getBackground() == Color.BLACK){
            currButton.setBackground(Color.WHITE);
        }
        else if (currButton.getBackground() == Color.WHITE){
            currButton.setBackground(Color.RED);

        }
        else if (currButton.getBackground() == Color.RED) {
            currButton.setBackground(Color.BLUE);
        }
        else if (currButton.getBackground() == Color.BLUE) {
            currButton.setBackground(Color.GREEN);
        }
        else if (currButton.getBackground() == Color.GREEN) {
            currButton.setBackground(Color.BLACK);
        }
        else if (currButton.getText().equals("Save")) {
            try {
                Save();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        
    }
    public void Save() throws IOException {
        CreateFile();
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Avan Peltier\\Documents\\GitHub\\Liberty\\L.I.B.E.R.T.Y\\LightFiles\\" + this.field.getText() + ".txt");
            for (int i = 0; i < 48; i++){
                if (i % 2 == 0){
                    for (int j = 0; j < 54; j++){
                        Color color = buttons[i][j].getBackground();
                        if (color.equals(Color.BLACK)){
                            writer.write("0 ");
                        }
                        else if (color.equals(Color.BLUE)){
                            writer.write("B ");
                        }
                        else if (color.equals(Color.RED)){
                            writer.write("R ");
                        }
                        else if (color.equals(Color.GREEN)){
                            writer.write("G ");
                        }
                        else if (color.equals(Color.WHITE)){
                            writer.write("W ");
                        }
                    }
                }
                else{
                    for (int j = 53; j >= 0; j--){
                        Color color = buttons[i][j].getBackground();
                        if (color.equals(Color.BLACK)){
                            writer.write("0 ");
                        }
                        else if (color.equals(Color.BLUE)){
                            writer.write("B ");
                        }
                        else if (color.equals(Color.RED)){
                            writer.write("R ");
                        }
                        else if (color.equals(Color.GREEN)){
                            writer.write("G ");
                        }
                        else if (color.equals(Color.WHITE)){
                            writer.write("W ");
                        }
                    }
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An unknown error has occurred");
            e.printStackTrace();
        }

    }

    private void CreateFile() throws IOException {
        try {
            File currFile = new File("C:\\Users\\Avan Peltier\\Documents\\GitHub\\Liberty\\L.I.B.E.R.T.Y\\LightFiles\\" + this.field.getText() + ".txt");
            if (currFile.createNewFile()){
                System.out.println("File Created: " + currFile.getName());
            }
            else {
                System.out.println("File Already Exists");
            }
        }
        catch (IOException e){
            System.out.println("An unknown error has occurred");
            e.printStackTrace();
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {
    }
    @Override
    public void windowClosing(WindowEvent e) {
    }
    @Override
    public void windowClosed(WindowEvent e) {

    }
    @Override
    public void windowIconified(WindowEvent e) {
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    @Override
    public void windowActivated(WindowEvent e) {
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
=======
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;


public class WriterGUI extends JFrame implements WindowListener,ActionListener {
    private JButton[][] buttons = new JButton[48][54];
    private JButton currButton;
    private static ArrayList<Color> colors = new ArrayList<>(Arrays.asList(
            Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE
    ));
    private TextField field = new TextField(70);
    public static void main(String[] args){
        JFrame frame= new WriterGUI();
        frame.setSize(900, 900);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public WriterGUI() {
        addWindowListener(this);
        JPanel textPanel = new JPanel(new FlowLayout());
        textPanel.add(field);
        JPanel gridPanel = new JPanel(new GridLayout(48, 54));
        JPanel saveButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton save = new JButton("Save");
        save.addActionListener(this);
        saveButton.add(save);
        CreateButtons();
        for (int i = 0; i < 48; i++) {
            for (int j = 0; j < 54; j++) {
                buttons[i][j].addActionListener(this);
                gridPanel.add(buttons[i][j]);
            }
        }
        JPanel panelbrdLayout = new JPanel(new BorderLayout());
        panelbrdLayout.add(textPanel, BorderLayout.NORTH);
        panelbrdLayout.add(gridPanel, BorderLayout.CENTER);
        panelbrdLayout.add(saveButton, BorderLayout.SOUTH);
        add(panelbrdLayout);
    }

    public void CreateButtons(){
        for (int i = 0; i < 48; i++){
            for (int j = 0; j < 54; j++){
                this.currButton = new JButton("");
                currButton.setBackground(Color.BLACK);
                buttons[i][j] = currButton;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currButton = (JButton) e.getSource();
        if (currButton.getBackground() == Color.BLACK){
            currButton.setBackground(Color.WHITE);
        }
        else if (currButton.getBackground() == Color.WHITE){
            currButton.setBackground(Color.RED);

        }
        else if (currButton.getBackground() == Color.RED) {
            currButton.setBackground(Color.BLUE);
        }
        else if (currButton.getBackground() == Color.BLUE) {
            currButton.setBackground(Color.GREEN);
        }
        else if (currButton.getBackground() == Color.GREEN) {
            currButton.setBackground(Color.BLACK);
        }
        else if (currButton.getText().equals("Save")) {
            try {
                Save();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        
    }
    public void Save() throws IOException {
        CreateFile();
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Avan Peltier\\Documents\\GitHub\\L.I.B.E.R.T.Y\\LightFiles\\" + this.field.getText() + ".txt");
            for (int i = 0; i < 48; i++){
                for (int j = 0; j < 54; j++){
                    Color color = buttons[i][j].getBackground();
                    if (color.equals(Color.BLACK)){
                        writer.write("0 ");
                    }
                    else if (color.equals(Color.BLUE)){
                        writer.write("B ");
                    }
                    else if (color.equals(Color.RED)){
                        writer.write("R ");
                    }
                    else if (color.equals(Color.GREEN)){
                        writer.write("G ");
                    }
                    else if (color.equals(Color.WHITE)){
                        writer.write("W ");
                    }
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An unknown error has occurred");
            e.printStackTrace();
        }

    }

    private void CreateFile() throws IOException {
        try {
            File currFile = new File("C:\\Users\\Avan Peltier\\Documents\\GitHub\\L.I.B.E.R.T.Y\\LightFiles\\" + this.field.getText() + ".txt");
            if (currFile.createNewFile()){
                System.out.println("File Created: " + currFile.getName());
            }
            else {
                System.out.println("File Already Exists");
            }
        }
        catch (IOException e){
            System.out.println("An unknown error has occurred");
            e.printStackTrace();
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {
    }
    @Override
    public void windowClosing(WindowEvent e) {
    }
    @Override
    public void windowClosed(WindowEvent e) {

    }
    @Override
    public void windowIconified(WindowEvent e) {
    }
    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    @Override
    public void windowActivated(WindowEvent e) {
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}

