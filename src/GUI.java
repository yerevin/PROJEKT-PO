
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateusz Skulski
 */
public class GUI extends javax.swing.JFrame {

    Polaczenie connection = new Polaczenie();
    Statement stm;
    ResultSet Rs;
    DefaultTableModel model = new DefaultTableModel();

    public GUI() {
        initComponents();

        model.addColumn("ID");
        model.addColumn("Producent");
        model.addColumn("Nazwa");
        model.addColumn("Kategoria");
        try {
            stm = connection.getConnection().createStatement();
            ResultSet Rs = stm.executeQuery("SELECT p.product_id, k.name, r.name, p.name "
                    + "FROM categories k, producents r, products p "
                    + "WHERE k.category_id = p.category_id "
                    + "AND r.producent_id = p.producent_id ORDER BY 1");
            while (Rs.next()) {
                model.addRow(new Object[]{Rs.getString("product_id"), Rs.getString("r.name"), Rs.getString("p.name"),
                    Rs.getString("k.name")});

            }
        } catch (Exception e) {
            System.err.println(e);
        }

        table.setModel(model);

    }

    private void initComponents() {

        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        textSearchInput = new javax.swing.JTextField();
        scrollPanel = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        idLabel = new javax.swing.JLabel();
        producentLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        categorySelect = new javax.swing.JComboBox();
        nameInput = new javax.swing.JTextField();
        idInput = new javax.swing.JTextField();
        producentSelect = new javax.swing.JComboBox();
        logoLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        refreshMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sklep z telefonami - Mateusz Skulski");
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(800, 380));
        getContentPane().setLayout(null);

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteButton.setLabel("Usuń");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteElement(evt);
            }
        });
        getContentPane().add(deleteButton);
        deleteButton.setBounds(480, 230, 110, 40);

        addButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addButton.setLabel("Dodaj");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewElement(evt);
            }
        });
        getContentPane().add(addButton);
        addButton.setBounds(20, 230, 130, 40);

        searchButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchButton.setText("Szukaj");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByButton(evt);
            }
        });
        getContentPane().add(searchButton);
        searchButton.setBounds(600, 10, 150, 40);

        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        refreshButton.setText("Odśwież");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getCurrentData(evt);
            }
        });
        getContentPane().add(refreshButton);
        refreshButton.setBounds(320, 230, 140, 40);

        editButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editButton.setText("Edytuj");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteElementById(evt);
            }
        });
        getContentPane().add(editButton);
        editButton.setBounds(170, 230, 130, 40);

        textSearchInput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textSearchInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtszuActionPerformed(evt);
            }
        });
        textSearchInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchByEnterKey(evt);
            }
        });
        getContentPane().add(textSearchInput);
        textSearchInput.setBounds(600, 60, 150, 30);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Producent", "Nazwa", "Kategoria"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRowClicked(evt);
            }
        });
        scrollPanel.setViewportView(table);

        getContentPane().add(scrollPanel);
        scrollPanel.setBounds(210, 10, 374, 190);

        idLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        idLabel.setText("ID:");
        getContentPane().add(idLabel);
        idLabel.setBounds(60, 10, 42, 20);

        producentLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        producentLabel.setText("Producent:");
        getContentPane().add(producentLabel);
        producentLabel.setBounds(10, 40, 70, 20);

        nameLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        nameLabel.setText("Nazwa:");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(40, 70, 60, 20);

        categoryLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        categoryLabel.setText("Kategoria:");
        getContentPane().add(categoryLabel);
        categoryLabel.setBounds(20, 100, 80, 20);

        categorySelect.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"-", "Telefon", "Tablet", "SmartWatch"}));
        getContentPane().add(categorySelect);
        categorySelect.setBounds(90, 100, 100, 22);

        nameInput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(nameInput);
        nameInput.setBounds(90, 70, 100, 23);

        idInput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        getContentPane().add(idInput);
        idInput.setBounds(90, 10, 100, 23);

        producentSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"-", "Sony", "Xiaomi", "Apple"}));
        getContentPane().add(producentSelect);
        producentSelect.setBounds(90, 40, 100, 22);

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baza.png"))); // NOI18N
        getContentPane().add(logoLabel);
        logoLabel.setBounds(600, 90, 150, 130);

        menu.setText("Plik");

        refreshMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        refreshMenuItem.setText("Odśwież");
        refreshMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshDataAction(evt);
            }
        });
        menu.add(refreshMenuItem);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        pack();
    }

    // METODA POBIERA INFORMACJE O DANYM ELEMENCIE Z TABELI.
    private void getElementData(int i) {

        try {
            idInput.setText(model.getValueAt(i, 0).toString());
            producentSelect.setSelectedItem(model.getValueAt(i, 1).toString());
            nameInput.setText(model.getValueAt(i, 2).toString());
            categorySelect.setSelectedItem(model.getValueAt(i, 3).toString());
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Błąd!" + e.getLocalizedMessage());
        }

    }

    // METODA WYŚWIETLA AKTUALNY STAN TABELI.
    private void getCurrentProductsData() {
        try {
            model.setRowCount(0);
            stm = connection.getConnection().createStatement();
            ResultSet Rs = stm.executeQuery("SELECT p.product_id, r.name, p.name, k.name "
                    + "FROM categories k, producents r, products p "
                    + "WHERE k.category_id = p.category_id "
                    + "AND r.producent_id = p.producent_id ORDER BY 1");
            while (Rs.next()) {
                model.addRow(new Object[]{Rs.getString("product_id"), Rs.getString("r.name"), Rs.getString("p.name"),
                    Rs.getString("k.name")});

            }
        } catch (Exception e) {
            System.err.println(e);
        }

        table.setModel(model);

    }

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void txtszuActionPerformed(java.awt.event.ActionEvent evt) {

    }

    // KLIKNIĘCIE NA WYBRANY ELEMENT W TABELI POBIERA DO PÓL INFORMACJE O DANYM ELEMENCIE.
    private void tableRowClicked(java.awt.event.MouseEvent evt) {

        try {
            int i = table.getSelectedRow();
            getElementData(i);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd " + e.getLocalizedMessage());
        }

    }

    // KLIKNIĘCIE PRZYCISKU POWODUJE DODANIE NOWEGO ELEMENTU TABELI PO WPISANIU NIEISTNIEJĄCEGO ID, WYBRANIU KATEGORII I PRODUCENTA, ORAZ PODANIU NAZWY.
    private void addNewElement(java.awt.event.ActionEvent evt) {

        String id = idInput.getText();
        int pro = producentSelect.getSelectedIndex();
        String naz = nameInput.getText();
        int kat = categorySelect.getSelectedIndex();

        if ((pro == 0 && kat == 0) || pro == 0 || kat == 0) {
            JOptionPane.showMessageDialog(null, "Nie wybrano producenta i/lub kategorii.");
        } else {
            String zapytanie = "INSERT INTO products(product_id,producent_id,name,category_id)VALUES('"
                    + id + "','" + pro + "','" + naz + "','" + kat + "')";

            try {
                stm.executeUpdate(zapytanie);
                JOptionPane.showMessageDialog(null, "Produkt został dodany.");
                idInput.setText("");
                producentSelect.setSelectedIndex(0);
                nameInput.setText("");
                categorySelect.setSelectedItem(0);
                getCurrentProductsData();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

    }

    // PO KLIKNIĘCIU PRZYCISKU WYŚWIETLA AKTUALNY STAN TABELI.
    private void getCurrentData(java.awt.event.ActionEvent evt) {
        getCurrentProductsData();

    }

    // EDYCJA ELEMENTU O PODANYM ID, LUB WYBRANYM Z TABELI PO KLIKNIĘCIU PRZYCISKU.
    private void deleteElementById(java.awt.event.ActionEvent evt) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Potwierdzić edycję", "Edycja",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                stm.executeUpdate("UPDATE products SET producent_id='" + producentSelect.getSelectedIndex() + "',name='" + nameInput.getText()
                        + "',category_id='" + categorySelect.getSelectedIndex() + "' WHERE product_id= " + idInput.getText());
                getCurrentProductsData();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd edycji! " + e.getMessage());
            System.err.println(e);
        }

    }

    // USUWANIE ELEMENTU O PODANYM ID, LUB WYBRANYM Z TABELI PO KLIKNIĘCIU PRZYCISKU.
    private void deleteElement(java.awt.event.ActionEvent evt) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Czy jesteś pewien?", "Usuwanie productu.", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                if (idInput.getText().length() != 0) {
                    stm.executeUpdate("DELETE FROM products where product_id = " + idInput.getText());
                    getCurrentProductsData();
                } else {
                    JOptionPane.showMessageDialog(null, "Podaj ID!");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Błąd podczas usuwania \n" + e.getMessage());
        }

    }

    // WYSZUKIWARKA AKTYWOWANA PRZYCISKIEM, PRZESZUKUJĄCA BAZĘ DANYCH PO FRAGMENCIE NAZWY PODANEJ PRZEZ UŻYTKOWNIKA.
    // GDY BRAK WYNIKÓW WYŚWIETLA "Brak wyników".
    private void searchByButton(java.awt.event.ActionEvent evt) {
        try {
            model.setRowCount(0);        // Wyczyszczenie tabeli by wyświetlała tylko szukane wyniki.
            {
                Rs = stm.executeQuery("SELECT p.product_id, r.name, p.name, k.name "
                        + "FROM categories k, producents r, products p "
                        + "WHERE k.category_id = p.category_id "
                        + "AND r.producent_id = p.producent_id "
                        + "AND p.name LIKE \"%" + textSearchInput.getText() + "%\"");
            }
            while (Rs.next()) {

                Object[] product = {Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getString(4)};
                model.addRow(product);
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Brak wyników.");

            } else {
                int i = 0;
                getElementData(i);
            }

        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    // WYŚWIETLENIE AKTUALNYCH DANYCH TABELI PO WCIŚNIĘCIU KLAWISZA "F5" LUB "PLIK -> ODŚWIEŻ"
    private void refreshDataAction(java.awt.event.ActionEvent evt) {
        getCurrentProductsData();
    }

    // WYSZUKIWARKA AKTYWOWANA KLAWISZEM "ENTER", GDY AKTYWNE JEST POLE TEKSTOWE WYSZUKIWARKI, PRZESZUKUJĄCA BAZĘ DANYCH PO FRAGMENCIE NAZWY PODANEJ PRZEZ UŻYTKOWNIKA.
    // GDY BRAK WYNIKÓW WYŚWIETLA "Brak wyników".
    private void searchByEnterKey(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) // Operacja wykonuje się po wciśnięciu "ENTER"
        {
            try {
                model.setRowCount(0);      // Wyczyszczenie tabeli by wyświetlała tylko szukane wyniki.
                {
                    Rs = stm.executeQuery("SELECT p.product_id, r.name, p.name, k.name "
                            + "FROM categories k, producents r, products p "
                            + "WHERE k.category_id = p.category_id "
                            + "AND r.producent_id = p.producent_id "
                            + "AND p.name LIKE \"%" + textSearchInput.getText() + "%\"");
                }
                while (Rs.next()) {

                    Object[] product = {Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getString(4)};
                    model.addRow(product);
                }
                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Brak wyników.");

                } else {
                    int i = 0;
                    getElementData(i);
                }

            } catch (Exception e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    private javax.swing.JButton deleteButton;
    private javax.swing.JButton addButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel producentLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem refreshMenuItem;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JTable table;
    private javax.swing.JTextField idInput;
    private javax.swing.JComboBox categorySelect;
    private javax.swing.JTextField nameInput;
    private javax.swing.JComboBox producentSelect;
    private javax.swing.JTextField textSearchInput;
}
