package ui.Panels;

import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class ModifyUserDataPanel extends JPanel {
    private final UserInterface uc = ControllerFactory.getInstance().getUserInterface();
    private int activeUserYearBorn;
    private final JLabel title = new JLabel("Cambiar información basica del usuario");
    private final JLabel selectUser = new JLabel("Seleccione el usuario >");
    private JComboBox<String> selectUserComboBox = new JComboBox<>();
    private final JSeparator separator = new JSeparator();
    private final JButton saveButton = new JButton("Guardar");
    private final JButton cancelButton = new JButton("Cancelar");
    private JTextField nameTextField = new JTextField();
    private JTextField emailTextField = new JTextField();
    private JTextField lastnameTextField = new JTextField();
    private final JLabel nameLabel = new JLabel("Nombre");
    private final JLabel lastnameLabel = new JLabel("Apellido");
    private final JLabel emailLabel = new JLabel("Email");
    private JComboBox<Integer> dayComboBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31});
    private JComboBox<Integer> monthComboBox = new JComboBox<>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
    private JComboBox<String> yearComboBox = new JComboBox<>(new String[]{});
    private final JLabel bornDateDetailLabel = new JLabel("dd / mm / aa");
    private final JLabel bornDateLabel = new JLabel("Fecha de nacimiento");
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    public ModifyUserDataPanel(){
        addItemsToComboBox(selectUserComboBox, Set.of(uc.listUsersByNickname()));
        initialize();
        this.setVisible(true);
    }

    private void addItemsToComboBox(JComboBox<String> comboBox, Set<String> values) {
        comboBox.addItem("Sin seleccionar");
        for(String value : values) {
            comboBox.addItem(value);
        }
    }

    private void fetchSelectedUserData(String nickname){
        DtUser userChosen = uc.chooseUser(nickname);
        if(Objects.isNull(userChosen)){
            return;
        }
        // Sets the inputs with the user information
        emailTextField.setText(userChosen.getEmail());
        nameTextField.setText(userChosen.getName());
        nameTextField.setEnabled(true);
        nameTextField.setEditable(true);
        lastnameTextField.setText(userChosen.getLastName());
        lastnameTextField.setEnabled(true);
        lastnameTextField.setEditable(true);
        // Here I put the day -1 because I need the index of the item to be selected in the array
        dayComboBox.setSelectedIndex(Integer.parseInt(userChosen.getBornDate().toString().substring(8,10)) -1);
        dayComboBox.setEnabled(true);
        monthComboBox.setSelectedIndex(userChosen.getBornDate().getMonth());
        monthComboBox.setEnabled(true);
        activeUserYearBorn = userChosen.getBornDate().getYear();
        changeYearComboBox("START");
        yearComboBox.setEnabled(true);
        saveButton.setEnabled(true);
        cancelButton.setEnabled(true);
    }

    private void updateUserInfo(){
        // saves the use case (if no exception is thrown starts the use case again)
        String nickname = (String) selectUserComboBox.getSelectedItem();
        String name = nameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String password = (String) passwordField.getPassword().toString();
        String confirmPass = (String) confirmPasswordField.getPassword().toString();
        int year = Integer.parseInt(String.valueOf(yearComboBox.getSelectedItem()));
        int month = Integer.parseInt(String.valueOf(monthComboBox.getSelectedItem()));
        int day = Integer.parseInt(String.valueOf(dayComboBox.getSelectedItem()));
        Date bornDate = new Date(year, month, day);
        if(!confirmPass.equals(password)){
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario",
                    "Password y confirmacion no coinciden", JOptionPane.ERROR_MESSAGE);
        }
        try{
            uc.updateUserInfo(new DtUser(nickname, name, lastname, email, bornDate, password));
            JOptionPane.showMessageDialog(this, "El usuario fue actualizado con éxito!",
                    "Modificar información de usuario", JOptionPane.INFORMATION_MESSAGE);
            restartUseCase();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Modificar información de usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void restartUseCase(){
        // Restarts the use case
        nameTextField.setText("");
        nameTextField.setEditable(false);
        lastnameTextField.setText("");
        lastnameTextField.setEditable(false);
        emailTextField.setText("");
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        yearComboBox.setEnabled(false);
        monthComboBox.setEnabled(false);
        dayComboBox.setEnabled(false);
        selectUserComboBox.setSelectedIndex(0);
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    private void changeYearComboBox(String operation){
        // Changes the array in the year combobox
        java.util.List<String> yearArray = new ArrayList<>();
        yearArray.add("-10");
        if(operation.equals("ADD")){
            activeUserYearBorn+= 10;
        }else if(operation.equals("SUBTRACT")){
            activeUserYearBorn-= 10;
        }
        // If it enters not any of the above if statement, it means is the start of the panel, where we need to set the JComboBox
        // activeUserYearBorn should already be initialized and set with the user info
        for(int i = activeUserYearBorn - 5; i <= activeUserYearBorn + 5; i++){
            yearArray.add(String.valueOf(i));
        }
        yearArray.add("+10");
        yearComboBox.setModel(new DefaultComboBoxModel<>(yearArray.toArray(new String[0])));
        yearComboBox.setSelectedIndex(6);
    }

    private void initialize(){
        setLayout(null);
        title.setBounds(206, 10, 358, 30);
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        selectUser.setBounds(140, 71, 171, 20);
        selectUser.setFont(new Font("Arial", Font.PLAIN, 14));
        selectUser.setLabelFor(selectUserComboBox);

        selectUserComboBox.setBounds(350, 72, 217, 21);
        selectUserComboBox.setToolTipText("Nicknames de los usuarios");
        selectUserComboBox.addItemListener(e -> {
            String nick = String.valueOf(selectUserComboBox.getSelectedItem());
            if(nick.equals("Sin seleccionar") && !emailTextField.getText().isEmpty()) {
                restartUseCase();
                return;
            }
            fetchSelectedUserData(nick);
        });

        separator.setBounds(50, 104, 626, 2);

        saveButton.setBounds(228, 413, 85, 21);
        saveButton.setEnabled(false);
        saveButton.addActionListener(e -> {
            updateUserInfo();
        });

        cancelButton.setBounds(349, 413, 85, 21);
        cancelButton.setEnabled(false);
        cancelButton.addActionListener(e -> {
            restartUseCase();
            selectUserComboBox.setSelectedIndex(0);
        });

        nameTextField.setBounds(89, 139, 141, 19);
        nameTextField.setColumns(10);
        nameTextField.setEditable(false);

        emailTextField.setBounds(89, 224, 141, 19);
        emailTextField.setEditable(false);
        emailTextField.setColumns(10);

        lastnameTextField.setBounds(431, 139, 140, 19);
        lastnameTextField.setColumns(10);
        lastnameTextField.setEditable(false);

        nameLabel.setBounds(89, 116, 141, 13);
        nameLabel.setLabelFor(nameTextField);

        lastnameLabel.setBounds(431, 116, 140, 13);
        lastnameLabel.setLabelFor(lastnameTextField);

        emailLabel.setBounds(89, 199, 141, 13);
        emailLabel.setLabelFor(emailTextField);

        dayComboBox.setBounds(431, 223, 70, 21);
        dayComboBox.setEnabled(false);

        monthComboBox.setBounds(513, 223, 70, 21);
        monthComboBox.setEnabled(false);

        yearComboBox.setBounds(595, 223, 70, 21);
        yearComboBox.setEnabled(false);
        yearComboBox.addItemListener(e -> {
            if(Objects.equals(String.valueOf(yearComboBox.getSelectedItem()), "-10")) {
                changeYearComboBox("SUBTRACT");
            } else if (Objects.equals(String.valueOf(yearComboBox.getSelectedItem()), "+10")){
                changeYearComboBox("ADD");
            }
        });

        bornDateDetailLabel.setBounds(431, 199, 94, 13);
        bornDateDetailLabel.setLabelFor(dayComboBox);

        bornDateLabel.setBounds(435, 170, 183, 13);
        bornDateLabel.setLabelFor(bornDateDetailLabel);

        add(separator);
        add(saveButton);
        add(cancelButton);
        add(nameTextField);
        add(emailTextField);
        add(lastnameTextField);
        add(title);
        add(selectUser);
        add(selectUserComboBox);
        add(lastnameLabel);
        add(emailLabel);
        add(bornDateLabel);
        add(dayComboBox);
        add(monthComboBox);
        add(yearComboBox);
        add(nameLabel);
        add(bornDateDetailLabel);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(89, 294, 70, 15);
        add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setText("password");
        passwordField.setBounds(89, 321, 166, 19);
        add(passwordField);

        JLabel lblConfirmarPassword = new JLabel("Confirmar password");
        lblConfirmarPassword.setBounds(431, 294, 174, 15);
        add(lblConfirmarPassword);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setText("password");
        confirmPasswordField.setBounds(435, 321, 166, 19);
        add(confirmPasswordField);
    }
}