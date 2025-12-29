import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class MoneyBudApp {
    private JFrame frame;
    private JPanel splashPanel, mainPanel, transPanel, addIncome, addOutcome, 
                   historyPanel, planPanel, addPlan, removePlan, viewPlan, aboutPanel;
    private JTextField incomeField, outcomeField, titleField, targetField;
    private double totalIncome = 0.0;
    private double totalOutcome = 0.0;
    private ArrayList<String> incomeHistory = new ArrayList<>();
    private ArrayList<String> outcomeHistory = new ArrayList<>();
    private ArrayList<String[]> plans = new ArrayList<>();

    public MoneyBudApp() {
        frame = new JFrame("MoneyBud");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        splashPanel = createSplashPanel();
        mainPanel = createMainPanel();
        transPanel = createTransPanel();
        addIncome = createIncomePanel();
        addOutcome = createOutcomePanel();
        historyPanel = createHistoryPanel();
        planPanel = createPlanPanel();
        addPlan = createAddPlan();
        removePlan = createRemovePlan();
        viewPlan = createViewPlan();
        aboutPanel = createAboutPanel();

        frame.add(splashPanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private JPanel createSplashPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(20,30,60));

        ImageIcon logo = new ImageIcon("assets/logo.png");

        JLabel splash = new JLabel(logo);
        splash.setHorizontalAlignment(SwingConstants.CENTER);
        splash.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(splash,BorderLayout.CENTER);

        Timer timer = new Timer(3000, e -> switchPanel(mainPanel));
        timer.setRepeats(false);
        timer.start();

        return panel;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 10));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel wallet = createTransactionCard("CURRENT MONEY", "Rp" + String.valueOf(totalIncome - totalOutcome), new Color(222, 222, 222), new Color(0, 0, 0));

        info.add(wallet);

        JPanel trans = new JPanel(new GridLayout(1, 2, 10, 10));
        trans.setBackground(Color.WHITE);
        trans.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel incomePanel = createTransactionCard("TOTAL INCOME", "Rp" + String.valueOf(totalIncome), new Color(235, 249, 235), new Color(77, 178, 77));
        JPanel outcomePanel = createTransactionCard("TOTAL OUTCOME", "Rp" + String.valueOf(totalOutcome), new Color(255, 240, 240), new Color(255, 94, 94));

        trans.add(incomePanel);
        trans.add(outcomePanel);

        content.add(info);
        content.add(trans);
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.setBackground(new Color(20, 30, 60));
        home.setForeground(Color.WHITE);

        JButton transaction = new JButton("Transaction");
        transaction.addActionListener(e -> switchPanel(transPanel));

        JButton planning = new JButton("Planning");
        planning.addActionListener(e -> switchPanel(planPanel));

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTransPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 5));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel page = new JLabel("TRANSACTION");
        page.setHorizontalAlignment(SwingConstants.LEFT);

        info.add(page);

        JPanel trans = new JPanel(new GridLayout(1, 2, 10, 5));
        trans.setBackground(Color.WHITE);
        trans.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel incomePanel = createTransactionCard("TOTAL INCOME", "Rp" + String.valueOf(totalIncome), new Color(235, 249, 235), new Color(77, 178, 77));
        JPanel outcomePanel = createTransactionCard("TOTAL OUTCOME", "Rp" + String.valueOf(totalOutcome), new Color(255, 240, 240), new Color(255, 94, 94));

        trans.add(incomePanel);
        trans.add(outcomePanel);

        JPanel interact = new JPanel(new GridLayout(1, 2, 10, 5));
        interact.setBackground(Color.WHITE);
        interact.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JButton income = new JButton("ADD INCOME");
        income.setBackground(new Color(77, 178, 77));
        income.setForeground(Color.WHITE);
        income.addActionListener(e -> switchPanel(addIncome));
        JButton outcome = new JButton("ADD OUTCOME");
        outcome.setBackground(new Color(255, 94, 94));
        outcome.setForeground(Color.WHITE);
        outcome.addActionListener(e -> switchPanel(addOutcome));

        interact.add(income);
        interact.add(outcome);

        JPanel desc = new JPanel(new GridLayout(1, 2, 10, 5));
        desc.setBackground(Color.WHITE);
        desc.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JButton view = new JButton("TRANSACTION HISTORY");
        view.setBackground(new Color(222, 222, 222));
        view.setForeground(Color.BLACK);
        view.addActionListener(e -> switchPanel(historyPanel));

        desc.add(view);

        content.add(info);
        content.add(trans);
        content.add(interact);
        content.add(desc);
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.setBackground(new Color(20, 30, 60));
        transaction.setForeground(Color.WHITE);

        JButton planning = new JButton("Planning");
        planning.addActionListener(e -> switchPanel(planPanel));

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createIncomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 10));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel page = new JLabel("ADD INCOME");
        page.setHorizontalAlignment(SwingConstants.LEFT);

        JButton close = new JButton("X");
        close.setBackground(new Color(0, 0, 0, 0));
        close.setBorder(BorderFactory.createEmptyBorder());
        close.setOpaque(false);
        close.setHorizontalAlignment(SwingConstants.RIGHT);
        close.addActionListener(e -> switchPanel(transPanel));

        info.add(page);
        info.add(close);

        JPanel interact = new JPanel(new GridLayout(1, 2, 10, 10));
        interact.setBackground(Color.WHITE);
        interact.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<String> category = new JComboBox<>(new String[]{"PAYCHECK", "INCOME"});
        interact.add(category);

        JPanel trans = new JPanel(new GridLayout(1, 2, 10, 10));
        trans.setBackground(Color.WHITE);
        trans.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        incomeField = new JTextField("Amount (Rp)");
        ((AbstractDocument) incomeField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        incomeField.setColumns(10);
        incomeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (incomeField.getText().equals("Amount (Rp)")) {
                    incomeField.setText("");
                    incomeField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (incomeField.getText().isEmpty()) {
                    incomeField.setText("Amount (Rp)");
                    incomeField.setForeground(Color.GRAY);
                }
            }
        });
        trans.add(incomeField);

        JPanel submit = new JPanel(new GridLayout(1, 2, 10, 10));
        submit.setBackground(Color.WHITE);
        submit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addButton = new JButton("Add Income");
        addButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(incomeField.getText());
                totalIncome += amount;
                incomeHistory.add(category.getSelectedItem() + " : Rp" + amount);
                incomeField.setText("");
                updatePage();
                switchPanel(transPanel);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        submit.add(addButton);

        content.add(info);
        content.add(interact);
        content.add(trans);
        content.add(submit);
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.setBackground(new Color(20, 30, 60));
        transaction.setForeground(Color.WHITE);

        JButton planning = new JButton("Planning");
        planning.addActionListener(e -> switchPanel(planPanel));

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createOutcomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 10));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel page = new JLabel("ADD OUTCOME");
        page.setHorizontalAlignment(SwingConstants.LEFT);

        JButton close = new JButton("X");
        close.setBackground(new Color(0, 0, 0, 0));
        close.setBorder(BorderFactory.createEmptyBorder());
        close.setOpaque(false);
        close.setHorizontalAlignment(SwingConstants.RIGHT);
        close.addActionListener(e -> switchPanel(transPanel));

        info.add(page);
        info.add(close);

        JPanel interact = new JPanel(new GridLayout(1, 2, 10, 10));
        interact.setBackground(Color.WHITE);
        interact.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox<String> category = new JComboBox<>(new String[]{"SHOPPING", "BILLS", "OTHERS"});
        interact.add(category);

        JPanel trans = new JPanel(new GridLayout(1, 2, 10, 10));
        trans.setBackground(Color.WHITE);
        trans.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        outcomeField = new JTextField("Amount (Rp)");
        ((AbstractDocument) outcomeField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        outcomeField.setColumns(10);
        outcomeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (outcomeField.getText().equals("Amount (Rp)")) {
                    outcomeField.setText("");
                    outcomeField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (outcomeField.getText().isEmpty()) {
                    outcomeField.setText("Amount (Rp)");
                    outcomeField.setForeground(Color.GRAY);
                }
            }
        });
        trans.add(outcomeField);

        JPanel submit = new JPanel(new GridLayout(1, 2, 10, 10));
        submit.setBackground(Color.WHITE);
        submit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addButton = new JButton("Add Outcome");
        addButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(outcomeField.getText());
                if (totalOutcome + amount > totalIncome) {
                    JOptionPane.showMessageDialog(frame, "Your current money is not enough for this transaction.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    totalOutcome += amount;
                    outcomeHistory.add(category.getSelectedItem() + " : Rp" + amount);
                    outcomeField.setText("");
                    updatePage();
                    switchPanel(transPanel);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        submit.add(addButton);

        content.add(info);
        content.add(interact);
        content.add(trans);
        content.add(submit);
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.setBackground(new Color(20, 30, 60));
        transaction.setForeground(Color.WHITE);

        JButton planning = new JButton("Planning");
        planning.addActionListener(e -> switchPanel(planPanel));

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 10));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel page = new JLabel("TRANSACTION HISTORY");
        page.setHorizontalAlignment(SwingConstants.LEFT);

        JButton close = new JButton("X");
        close.setBackground(new Color(0, 0, 0, 0));
        close.setBorder(BorderFactory.createEmptyBorder());
        close.setOpaque(false);
        close.setHorizontalAlignment(SwingConstants.RIGHT);
        close.addActionListener(e -> switchPanel(transPanel));

        info.add(page);
        info.add(close);

        JTextArea transactionHistory = new JTextArea();
        transactionHistory.setEditable(false);
        transactionHistory.setText("Income :\n" + String.join("\n", incomeHistory) + "\n\nOutcome :\n" + String.join("\n", outcomeHistory));

        JScrollPane trans = new JScrollPane(transactionHistory);
        trans.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        content.add(info);
        content.add(trans, BorderLayout.CENTER);
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.setBackground(new Color(20, 30, 60));
        transaction.setForeground(Color.WHITE);

        JButton planning = new JButton("Planning");
        planning.addActionListener(e -> switchPanel(planPanel));

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPlanPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 10));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel page = new JLabel("PLANNING");
        page.setHorizontalAlignment(SwingConstants.LEFT);

        info.add(page);

        JPanel interact = new JPanel(new GridLayout(1, 2, 10, 10));
        interact.setBackground(Color.WHITE);
        interact.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton add = new JButton("ADD PLANS");
        add.setBackground(new Color(77, 178, 77));
        add.setForeground(Color.WHITE);
        add.addActionListener(e -> switchPanel(addPlan));
        JButton remove = new JButton("REMOVE PLANS");
        remove.setBackground(new Color(255, 94, 94));
        remove.setForeground(Color.WHITE);
        remove.addActionListener(e -> switchPanel(removePlan));

        interact.add(add);
        interact.add(remove);

        JPanel desc = new JPanel(new GridLayout(1, 2, 10, 10));
        desc.setBackground(Color.WHITE);
        desc.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton view = new JButton("VIEW PLANS");
        view.setBackground(new Color(222, 222, 222));
        view.setForeground(Color.BLACK);
        view.addActionListener(e -> switchPanel(viewPlan));

        desc.add(view);

        content.add(info);
        content.add(interact);
        content.add(desc);
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.addActionListener(e -> switchPanel(transPanel));

        JButton planning = new JButton("Planning");
        planning.setBackground(new Color(20, 30, 60));
        planning.setForeground(Color.WHITE);

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAddPlan() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 10));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel page = new JLabel("ADD PLAN");
        page.setHorizontalAlignment(SwingConstants.LEFT);

        JButton close = new JButton("X");
        close.setBackground(new Color(0, 0, 0, 0));
        close.setBorder(BorderFactory.createEmptyBorder());
        close.setOpaque(false);
        close.setHorizontalAlignment(SwingConstants.RIGHT);
        close.addActionListener(e -> switchPanel(planPanel));

        info.add(page);
        info.add(close);

        JPanel desc = new JPanel(new GridLayout(1, 2, 10, 10));
        desc.setBackground(Color.WHITE);
        desc.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleField = new JTextField("Plan Name or Title");
        titleField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (titleField.getText().equals("Plan Name or Title")) {
                    titleField.setText("");
                    titleField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (titleField.getText().isEmpty()) {
                    titleField.setText("Plan Name or Title");
                    titleField.setForeground(Color.GRAY);
                }
            }
        });
        desc.add(titleField);

        JPanel trans = new JPanel(new GridLayout(1, 2, 10, 10));
        trans.setBackground(Color.WHITE);
        trans.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        targetField = new JTextField("Target (Rp)");
        ((AbstractDocument) targetField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        targetField.setColumns(10);
        targetField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (targetField.getText().equals("Target (Rp)")) {
                    targetField.setText("");
                    targetField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (targetField.getText().isEmpty()) {
                    targetField.setText("Target (Rp)");
                    targetField.setForeground(Color.GRAY);
                }
            }
        });
        trans.add(targetField);

        JPanel submit = new JPanel(new GridLayout(1, 2, 10, 10));
        submit.setBackground(Color.WHITE);
        submit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addButton = new JButton("Add Plan");
        addButton.setBackground(new Color(77, 178, 77));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> {
            try {
                plans.add(new String[] {(titleField.getText() + "\nProgress : Rp"), (" / Rp" + targetField.getText())});
                titleField.setText("");
                targetField.setText("");
                updatePage();
                switchPanel(planPanel);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        submit.add(addButton);

        content.add(info);
        content.add(desc);
        content.add(trans);
        content.add(submit);
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.addActionListener(e -> switchPanel(transPanel));

        JButton planning = new JButton("Planning");
        planning.setBackground(new Color(20, 30, 60));
        planning.setForeground(Color.WHITE);

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createRemovePlan() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");
        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 10));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel page = new JLabel("REMOVE PLAN");
        page.setHorizontalAlignment(SwingConstants.LEFT);

        JButton close = new JButton("X");
        close.setBackground(new Color(0, 0, 0, 0));
        close.setBorder(BorderFactory.createEmptyBorder());
        close.setOpaque(false);
        close.setHorizontalAlignment(SwingConstants.RIGHT);
        close.addActionListener(e -> switchPanel(planPanel));

        info.add(page);
        info.add(close);

        JPanel planListPanel = new JPanel();
        planListPanel.setLayout(new BoxLayout(planListPanel, BoxLayout.Y_AXIS));
        planListPanel.setBackground(Color.WHITE);

        for (int i = 0; i < plans.size(); i++) {
            String[] plan = plans.get(i);
            String first = plan[0];
            String last = plan[1];

            JPanel planItemPanel = new JPanel(new BorderLayout());
            JLabel planLabel = new JLabel(first + (totalIncome - totalOutcome) + last);

            JButton removeButton = new JButton("Remove");
            removeButton.setBackground(new Color(255, 94, 94));
            removeButton.setForeground(Color.WHITE);
            int index = i;
            removeButton.addActionListener(e -> {
                plans.remove(index);
                updatePlanList(planListPanel);
                updatePage();
            });

            planItemPanel.add(planLabel, BorderLayout.CENTER);
            planItemPanel.add(removeButton, BorderLayout.EAST);
            planItemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            planListPanel.add(planItemPanel);
        }

        JScrollPane planScrollPane = new JScrollPane(planListPanel);
        planScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        content.add(info);
        content.add(planScrollPane, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.addActionListener(e -> switchPanel(transPanel));

        JButton planning = new JButton("Planning");
        planning.setBackground(new Color(20, 30, 60));
        planning.setForeground(Color.WHITE);

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        panel.add(content, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createViewPlan() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);

        JPanel info = new JPanel(new GridLayout(1, 2, 10, 10));
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel page = new JLabel("PLAN DETAILS");
        page.setHorizontalAlignment(SwingConstants.LEFT);

        JButton close = new JButton("X");
        close.setBackground(new Color(0, 0, 0, 0));
        close.setBorder(BorderFactory.createEmptyBorder());
        close.setOpaque(false);
        close.setHorizontalAlignment(SwingConstants.RIGHT);
        close.addActionListener(e -> switchPanel(planPanel));

        info.add(page);
        info.add(close);

        JTextArea planDetails = new JTextArea();
        planDetails.setEditable(false);

        StringBuilder details = new StringBuilder();
        for (String[] plan : plans) {
            String first = plan[0];
            String last = plan[1];
            details.append(first).append(totalIncome - totalOutcome).append(last).append("\n\n");
        }
        planDetails.setText(details.toString());

        JScrollPane plan = new JScrollPane(planDetails);
        plan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        content.add(info);
        content.add(plan, BorderLayout.CENTER);
        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.addActionListener(e -> switchPanel(transPanel));

        JButton planning = new JButton("Planning");
        planning.setBackground(new Color(20, 30, 60));
        planning.setForeground(Color.WHITE);

        JButton about = new JButton("About Us");
        about.addActionListener(e -> switchPanel(aboutPanel));

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAboutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel header = new JPanel(new GridLayout(1, 1));
        header.setBackground(new Color(20, 30, 60));
        header.setPreferredSize(new Dimension(600, 100));

        ImageIcon title = new ImageIcon("assets/title.png");

        JLabel logoLabel = new JLabel(title);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        header.add(logoLabel);
        panel.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.WHITE);

        ImageIcon photo = new ImageIcon("assets/member.png");
        Image resized = photo.getImage().getScaledInstance(263, 195, Image.SCALE_SMOOTH);
        ImageIcon member = new ImageIcon(resized);

        JLabel team = new JLabel(member);
        team.setHorizontalAlignment(SwingConstants.CENTER);
        team.setVerticalAlignment(SwingConstants.CENTER);

        content.add(team, BorderLayout.CENTER);

        panel.add(content, BorderLayout.CENTER);

        JPanel footer = new JPanel(new GridLayout(1, 4));
        footer.setBackground(new Color(245, 245, 245));
        footer.setPreferredSize(new Dimension(600, 60));

        JButton home = new JButton("Home");
        home.addActionListener(e -> switchPanel(mainPanel));

        JButton transaction = new JButton("Transaction");
        transaction.addActionListener(e -> switchPanel(transPanel));

        JButton planning = new JButton("Planning");
        planning.addActionListener(e -> switchPanel(planPanel));

        JButton about = new JButton("About Us");
        about.setBackground(new Color(20, 30, 60));
        about.setForeground(Color.WHITE);

        footer.add(home);
        footer.add(transaction);
        footer.add(planning);
        footer.add(about);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private void updatePlanList(JPanel planListPanel) {
        planListPanel.removeAll();

        for (int i = 0; i < plans.size(); i++) {
            String[] plan = plans.get(i);
            String first = plan[0];
            String last = plan[1];

            JPanel planItemPanel = new JPanel(new BorderLayout());
            JLabel planLabel = new JLabel(first + (totalIncome - totalOutcome) + last);
            JButton removeButton = new JButton("Remove");

            int index = i;
            removeButton.addActionListener(e -> {
                plans.remove(index);
                updatePlanList(planListPanel);
            });

            planItemPanel.add(planLabel, BorderLayout.CENTER);
            planItemPanel.add(removeButton, BorderLayout.EAST);
            planItemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            planListPanel.add(planItemPanel);
        }

        planListPanel.revalidate();
        planListPanel.repaint();
    }

    private void updatePage() {
        frame.remove(mainPanel);
        frame.remove(transPanel);
        frame.remove(historyPanel);
        frame.remove(viewPlan);
        frame.remove(removePlan);
        mainPanel = createMainPanel();
        transPanel = createTransPanel();
        historyPanel = createHistoryPanel();
        viewPlan = createViewPlan();
        removePlan = createRemovePlan();
        frame.add(mainPanel);
        frame.add(transPanel);
        frame.add(historyPanel);
        frame.add(viewPlan);
        frame.add(removePlan);
        frame.revalidate();
        frame.repaint();
    }

    private static JPanel createTransactionCard(String title, String amount, Color bgColor, Color textColor) {
        JPanel card = new JPanel();
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel amountLabel = new JLabel(amount);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        amountLabel.setForeground(textColor);
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(titleLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(amountLabel);
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        return card;
    }

    private void switchPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}



