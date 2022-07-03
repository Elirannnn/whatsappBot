import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 500;

    public static void main(String[] args) {
        //app
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dguetta\\Downloads\\chromedriver_win32\\chromedriver.exe");
        new Main();
    }


    public Main() {
        this.getContentPane().setBackground(Color.white);
        this.setTitle("whatsapp (BOT)");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //Welcome to WhatsApp bot
        JLabel title1 = new JLabel("⭐ Welcome to WhatsApp bot ⭐");
        title1.setBounds(300, 50, 250, 30);
        title1.setForeground(new Color(0x000001));
        title1.setBackground(Color.white);
        title1.setOpaque(true);
        this.add(title1);

        //You are successfully logged in
        JLabel title2 = new JLabel("✅ - You are successfully logged in - ✅");
        title2.setBounds(300, 50, 250, 30);
        title2.setForeground(new Color(0x000001));
        title2.setBackground(Color.white);
        title2.setVisible(false);
        this.add(title2);

        //Go to whatsapp click
        JButton goToWhatsApp = new JButton("Open WhatsApp");
        goToWhatsApp.setBounds(320, title1.getY() + 30, 140, 30);
        this.add(goToWhatsApp);
        repaint();
        String app = "https://web.whatsapp.com/";

        //Send message click
        JButton sendMessage = new JButton("Click here to send the message");
        sendMessage.setBounds(450, 100, 250, 60);
        sendMessage.setVisible(false);
        this.add(sendMessage);
        repaint();
        //phone text
        JTextField phone = new JTextField();
        phone.setBounds(sendMessage.getX() / 3, 100, sendMessage.getWidth(), sendMessage.getHeight());
        phone.setVisible(false);
        this.add(phone);

        //text message
        JTextField text = new JTextField();
        text.setBounds(phone.getX(), phone.getY() + 100, sendMessage.getWidth(), sendMessage.getHeight());
        text.setVisible(false);
        this.add(text);

        JLabel titleText = new JLabel("Type below the text you want to send");
        titleText.setBounds(phone.getX(), text.getY() - 40, text.getWidth(), text.getHeight());
        titleText.setForeground(new Color(0xd13131));
        titleText.setBackground(Color.white);
        this.add(titleText);

        JLabel titlePhone = new JLabel("Type below the number of the person");
        titlePhone.setBounds(phone.getX(), phone.getY() - 40, phone.getWidth(), phone.getHeight());
        titlePhone.setForeground(new Color(0xd13131));
        titlePhone.setBackground(Color.white);
        this.add(titlePhone);

        goToWhatsApp.addActionListener((first_event) -> {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            ChromeDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(app);
            while (true) if (driver.getPageSource().contains("_1KDb8")) break;
            driver.manage().window().minimize();
            goToWhatsApp.setVisible(false);
            title1.setVisible(false);
            title2.setVisible(true);
            sendMessage.setVisible(true);
            text.setVisible(true);
            phone.setVisible(true);
            JLabel invalidText = new JLabel("INVALID TEXT");
            JLabel invalidPhone = new JLabel("INVALID PHONE NUMBER");
            JOptionPane.showMessageDialog(null, "✅ - You are successfully connected - ✅", "CONNECTED", JOptionPane.PLAIN_MESSAGE);
            //second action
            sendMessage.addActionListener((second_event) -> {
                if (phone.getText().length() < 10 && text.getText().length() < 1 || text.getText().length() > 10 && text.getText().length() < 1) {
                    JOptionPane.showMessageDialog(null, "Please type a correct number & A text that contains at least 1 character ", "⛔ INVALID PHONE NUMBER & TEXT MESSAGE ⛔", JOptionPane.PLAIN_MESSAGE);
                    phone.setVisible(true);
                    text.setVisible(true);

                } else if (phone.getText().length() < 10 || text.getText().length() > 10) {
                    JOptionPane.showMessageDialog(null, "Please type a correct number", "ERROR PHONE NUMBER", JOptionPane.PLAIN_MESSAGE);
                    phone.setVisible(true);
                } else if (text.getText().length() < 1) {
                    JOptionPane.showMessageDialog(null, "Please send a text that contains at least 1 character", "ERROR MESSAGE", JOptionPane.PLAIN_MESSAGE);
                    text.setVisible(true);
                } else {
                    title2.setVisible(false);
                    driver.manage().window().maximize();
                    driver.get("https://web.whatsapp.com/send?phone=972" + phone.getText());
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    while (true) {
                        if (driver.getPageSource().contains("_1KDb8")) {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            WebElement input = driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div.p3_M1 > div > div._13NKt.copyable-text.selectable-text"));
                            input.click();
                            for (int i = 0; i < 100; i++) {
                                input.sendKeys(text.getText());
                                input.sendKeys(Keys.ENTER);
                                new Thread(() -> {
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            }
                            driver.manage().window().minimize();
                            JOptionPane.showMessageDialog(null, "Message has been sent successfully, thank you for using me.", "Whatsapp bot", JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                    }
                }
            });
        });
    }
}