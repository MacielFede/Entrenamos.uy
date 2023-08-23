package ui.panels;

import dataTypes.DtUser;
import interfaces.ControllerFactory;
import interfaces.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ModifyUserDataPanel extends JPanel {
    private final UserInterface uc = ControllerFactory.getInstance().getUserInterface();
    private int activeUserYearBorn;
    private final JLabel title = new JLabel("Cambiar información basica del usuario");
    private final JLabel selectUser = new JLabel("Seleccione el usuario >");
    private JComboBox<String> selectUserComboBox;
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
    public ModifyUserDataPanel(){
        fetchUserList();
        initialize();
        this.setVisible(true);
    }

    private void fetchUserList(){
        selectUserComboBox = new JComboBox<>(uc.listUsersByNickname());
        selectUserComboBox.setSelectedItem("<Nicknames>");
    }

    private void fetchSelectedUserData(String nickname){
        DtUser userChosen = uc.chooseUser(nickname);
        // Sets the inputs with the user information
        emailTextField.setText(userChosen.getEmail());
        nameTextField.setText(userChosen.getName());
        nameTextField.setEnabled(true);
        lastnameTextField.setText(userChosen.getLastName());
        lastnameTextField.setEnabled(true);
        // Here I put the day -1 because I need the index of the item to be selected in the array
        dayComboBox.setSelectedIndex(userChosen.getBornDate().getDay() -1);
        dayComboBox.setEnabled(true);
        monthComboBox.setSelectedIndex(userChosen.getBornDate().getMonth() -1);
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
        int year = Integer.parseInt((String) yearComboBox.getSelectedItem());
        int month = Integer.parseInt((String) monthComboBox.getSelectedItem());
        int day = Integer.parseInt((String) dayComboBox.getSelectedItem());
        Date bornDate = new Date(year, month, day);
        uc.updateUserInfo(new DtUser(nickname, name, lastname, email, bornDate));
        restartUseCase();
    }

    private void restartUseCase(){
        // Restarts the use case
        selectUserComboBox.setSelectedItem("<Nicknames>");
        nameTextField.setText("");
        nameTextField.setEditable(false);
        lastnameTextField.setText("");
        lastnameTextField.setEditable(false);
        emailTextField.setText("");
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        yearComboBox.setSelectedItem("");
        yearComboBox.setEnabled(false);
        monthComboBox.setSelectedItem("");
        monthComboBox.setEnabled(false);
        dayComboBox.setSelectedItem("");
        dayComboBox.setEnabled(false);
    }

    private void changeYearComboBox(String operation){
        // Changes the array in the year combobox
        java.util.List<String> yearArray = new ArrayList<>();
        yearArray.add("-10");
        if(operation.equals("ADD")){
            activeUserYearBorn-= 10;
        }else if(operation.equals("SUBTRACT")){
            activeUserYearBorn+= 10;
        }
        // If it enters not any of the above if statement, it means is the start of the panel, where we need to set the JComboBox
        // activeUserYearBorn should already be initialized and set with the user info
        for(int i = activeUserYearBorn - 5; i <= activeUserYearBorn + 5; i++){
            yearArray.add(String.valueOf(i));
        }
        yearArray.add("+10");
        yearComboBox.setModel(new DefaultComboBoxModel<>(yearArray.toArray(new String[0])));
    }

    private void initialize(){
        setLayout(null);
        title.setBounds(50, 0, 358, 30);
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        selectUser.setBounds(20, 39, 171, 20);
        selectUser.setFont(new Font("Arial", Font.PLAIN, 14));
        selectUser.setLabelFor(selectUserComboBox);

        selectUserComboBox.setBounds(191, 40, 217, 21);
        selectUserComboBox.setToolTipText("Nicknames de los usuarios");
        selectUserComboBox.setSelectedItem("<Nicknames>");
        selectUserComboBox.addItemListener(e -> {
            String nick = String.valueOf(selectUserComboBox.getSelectedItem());
            if(nick.equals("<Nicknames>")) { return; }
            fetchSelectedUserData(nick);
        });

        separator.setBounds(20, 70, 391, 2);

        saveButton.setBounds(106, 269, 85, 21);
        saveButton.setEnabled(false);
        saveButton.addActionListener(e -> {
            updateUserInfo();
        });

        cancelButton.setBounds(246, 269, 85, 21);
        cancelButton.setEnabled(false);
        cancelButton.addActionListener(e -> {
            restartUseCase();
        });

        nameTextField.setBounds(20, 116, 119, 19);
        nameTextField.setColumns(10);
        nameTextField.setEditable(false);

        emailTextField.setBounds(20, 187, 119, 19);
        emailTextField.setEditable(false);
        emailTextField.setColumns(10);

        lastnameTextField.setBounds(222, 116, 119, 19);
        lastnameTextField.setColumns(10);
        lastnameTextField.setEditable(false);

        nameLabel.setBounds(20, 93, 45, 13);
        nameLabel.setLabelFor(nameTextField);

        lastnameLabel.setBounds(222, 93, 94, 13);
        lastnameLabel.setLabelFor(lastnameTextField);

        emailLabel.setBounds(20, 164, 45, 13);
        emailLabel.setLabelFor(emailTextField);

        dayComboBox.setBounds(222, 210, 29, 21);
        dayComboBox.setEnabled(false);

        monthComboBox.setBounds(262, 210, 29, 21);
        monthComboBox.setEnabled(false);

        yearComboBox.setBounds(302, 210, 29, 21);
        yearComboBox.setEnabled(false);
        yearComboBox.addItemListener(e -> {
            if(Objects.equals(String.valueOf(yearComboBox.getSelectedItem()), "-10")) {
                changeYearComboBox("SUBTRACT");
            } else if (Objects.equals(String.valueOf(yearComboBox.getSelectedItem()), "+10")){
                changeYearComboBox("ADD");
            }
        });

        bornDateDetailLabel.setBounds(222, 183, 94, 13);
        bornDateDetailLabel.setLabelFor(dayComboBox);

        bornDateLabel.setBounds(222, 164, 94, 13);
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
    }
}
