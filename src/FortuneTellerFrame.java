import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {

    // Fortunes List
    private final String[] fortunes = {
            "You will soon find what you were looking for... in the wrong folder.",
            "A surprise awaits you. It is probably an update.",
            "Your code will compile on the first try. (The fortune teller is laughing.)",
            "You will conquer a bug... and create two more.",
            "Great success is near. So is a suspicious warning.",
            "Today you will be productive. Tomorrow you will write documentation.",
            "A mysterious variable name will haunt you: tmp2_final_FINAL.",
            "Your future is bright—mostly because your screen is at max brightness.",
            "You will receive good news, right after you hit refresh 14 times.",
            "A new opportunity will appear… it starts with 'Can you hop on a quick call?'",
            "Trust your instincts. And also the compiler errors.",
            "You will master Java… right after Java stops changing.",
            "Your next snack will be delicious. Your next merge conflict will not."
    };

    private final Random rng = new Random();
    private int lastFortuneIndex = -1;

    private final Font titleFont = new Font("SansSerif", Font.BOLD, 48);
    private final Font fortuneFont = new Font("Monospaced", Font.PLAIN, 16);
    private final Font buttonFont = new Font("SansSerif", Font.BOLD, 18);

    private final JTextArea fortuneTextArea = new JTextArea();

    private final JPanel topPanel = new JPanel();
    private final JPanel middlePanel = new JPanel();
    private final JPanel bottomPanel = new JPanel();

    private final JButton readButton = new JButton("Read My Fortune!");
    private final JButton quitButton = new JButton("Quit");

    public FortuneTellerFrame() {
        setTitle("Fortune Teller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        topPanel();
        middlePanel();
        bottomPanel();

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();

        int frameWidth = (int) (screen.width * 0.75);
        int frameHeight = (int) (screen.height * 0.60);

        setSize(frameWidth, frameHeight);

        int x = (screen.width - frameWidth) / 2;
        int y = (screen.height - frameHeight) / 2;
        setLocation(x, y);

        setVisible(true);
    }

    private void topPanel() {
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/fortune-teller.jpg"));

        JLabel titleLabel = new JLabel("Fortune Teller", icon, SwingConstants.CENTER);
        titleLabel.setFont(titleFont);

        titleLabel.setVerticalTextPosition(SwingConstants.TOP);
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        titleLabel.setIconTextGap(10);

        topPanel.add(titleLabel);
    }

    private void middlePanel() {
        middlePanel.setLayout(new BorderLayout());

        fortuneTextArea.setFont(fortuneFont);
        fortuneTextArea.setEditable(false);
        fortuneTextArea.setLineWrap(true);
        fortuneTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(fortuneTextArea);
        middlePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void bottomPanel() {
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        readButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);

        readButton.addActionListener(e -> showFortune());
        quitButton.addActionListener(e -> System.exit(0));

        bottomPanel.add(readButton);
        bottomPanel.add(quitButton);
    }

    private void showFortune() {
        int index = rng.nextInt(fortunes.length);

        while (index == lastFortuneIndex && fortunes.length > 1) {
            index = rng.nextInt(fortunes.length);
        }

        lastFortuneIndex = index;
        fortuneTextArea.append(fortunes[index] + "\n");
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FortuneTellerFrame());
    }
}
